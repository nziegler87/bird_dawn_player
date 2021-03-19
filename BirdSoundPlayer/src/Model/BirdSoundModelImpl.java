package Model;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.zone.ZoneRulesException;

public class BirdSoundModelImpl implements IBirdSoundModel {
    private Clip clip;
    private String filePath;
    private File audioFile;
    private Double longitude;
    private Double latitude;
    private AudioInputStream audioInputStream;
    private AudioControls status;
    private long currentFrame;
    private ZonedDateTime sunrise;
    private int soundDurationMinute;
    private int startOffset;
    private boolean sunriseSet = false;
    private boolean audioSet = false;
    private boolean durationSet = false;

    public BirdSoundModelImpl() {
        // at this point, constructor is empty
    }

    /**
     * Loads an audio file (only AIFC, AIFF, AU, SND and WAVE formats are supported) to be played.
     *
     * @param filePath filepath to an AIFC, AIFF, AU, SND and WAVE file, a string.
     *
     * @throws IllegalStateException if there is a problem opening the audio file.
     */
    @Override
    public void loadFile(String filePath) throws IllegalStateException {

        this.filePath = filePath;
        this.audioFile = new File(this.filePath).getAbsoluteFile();

        try {
            this.audioInputStream = AudioSystem.getAudioInputStream(this.audioFile);
        } catch (UnsupportedAudioFileException | IOException IOE) {
            throw new IllegalStateException("Audio file is not valid.");
        }

        try {
            this.clip = AudioSystem.getClip();
        } catch (LineUnavailableException LUE) {
            throw new IllegalStateException("Error opening clip line.");
        }

        try {
            this.clip.open(this.audioInputStream);
            this.audioSet = true;
        } catch (LineUnavailableException | IOException IOE) {
            throw new IllegalStateException("Error opening line.");
        }
    }

    /**
     * Manually set the start time. Hour should be in 24hr time.
     *
     * @param hour the sunrise hour, an int, that is greater than or equal to 0 and less than 24.
     * @param minute the sunrise minute, an int, that is greater than or equal to 0 and less than 60.
     *
     * @throws IllegalArgumentException if hour or minute values are less than zero, if hour value is greater than 24,
     *                                  and if minute value is greater than 60.
     */
    @Override
    public void manuallySetSunrise(int hour, int minute) throws IllegalArgumentException {
        this.checkValidHourTime(hour, minute);

        LocalDateTime currentDateTime = LocalDateTime.now();
        this.sunrise = ZonedDateTime.of(currentDateTime.getYear(), currentDateTime.getMonthValue(),
                currentDateTime.getDayOfMonth(), hour, minute, 0, 0, ZoneId.systemDefault());
        this.sunriseSet = true;
    }

    /**
     * Set length of time that sound should play after the start time.
     *
     * @param hour the number of hours the sound should play, a double.
     * @param minute the number of minutes the sound should play, a double.
     *
     * @throws IllegalArgumentException if hour or minute values are less than zero
     */
    @Override
    public void setSoundDuration(int hour, int minute) throws IllegalArgumentException {
        if (hour < 0 || minute < 0) {
            throw new IllegalArgumentException("Hour and minute values must be greater than 0");
        }

        this.soundDurationMinute = hour * 60 + minute;
    }

    /**
     * Checks to make sure that hour and int params are valid.
     *
     * @param hour an hour, an int, that is greater than or equal to 0 and less than 24.
     * @param minute a minute, an int, that is greater than or equal to 0 and less than 60.
     *
     * @throws IllegalArgumentException if hour or minute values are less than zero, if hour value is greater than 24,
     *                                  and if minute value is greater than 60.
     */
    private void checkValidHourTime(int hour, int minute) throws IllegalStateException {
        if (hour < 0 || minute < 0) {
            throw new IllegalArgumentException("Hour and minute values must be greater than 0.");
        }

        if (hour > 24 || minute > 60) {
            throw new IllegalArgumentException("Hour and minute values must be less than 24 or 60.");
        }
    }

    /**
     * Automatically sets sunrise when passed a given longitude and latitude.
     *
     * @param latitude latitude of the location, a double
     * @param longitude longitude of the location, a double.
     */
    @Override
    public void automaticallySetSunrise(double latitude, double longitude) throws IllegalStateException {
        String url = "https://api.sunrise-sunset.org/json?lat=" + latitude + "&lng=" + longitude + "&formatted=0";
        // create a client
        HttpClient client = HttpClient.newHttpClient();

        // create a request
        HttpRequest request = HttpRequest.newBuilder(URI.create(url)).build();

        // use the client to send the request
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            this.sunrise = new SunriseSunsetParser(response.body()).getSunrise();
            this.sunriseSet = true;
        } catch (IOException | InterruptedException e) {
            throw new IllegalStateException("Error setting sunrise.");
        }
    }

    public ZonedDateTime getSunrise() throws IllegalStateException {
        if (this.sunrise == null) {
            throw new IllegalStateException("Sunrise data needs to be automatically set or manually entered.");
        }

        return this.sunrise;
    }

    /**
     * Returns a formatted version of the sunrise object as HH:MM:SS AM/PM based on a given time zone ID.
     *
     * @param timeZoneID a time zone ID, a string: https://en.wikipedia.org/wiki/List_of_tz_database_time_zones
     * @return a formatted version of the sunrise object as HH:MM:SS AM/PM based on the given time zone ID
     */
    @Override
    public String returnLocalTime(ZoneId timeZoneID) {
        try {
            ZonedDateTime newDate = this.sunrise.withZoneSameInstant(timeZoneID);
            String DATE_FORMAT = "hh:mm:ss a";
            DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_FORMAT);
            return format.format(newDate);

        } catch (ZoneRulesException e) {
            throw new IllegalStateException("Unknown time-zone ID.");
        }
    }

    /**
     * Plays audio clip.
     */
    @Override
    public void play() {

        if (this.status == AudioControls.PAUSE) {
            this.resume();
        }
        if (this.status == AudioControls.STOP) {
            this.status = AudioControls.PLAY;
            this.clip.loop(Clip.LOOP_CONTINUOUSLY);
            this.restart();
        } else {
            this.status = AudioControls.PLAY;
            this.clip.loop(Clip.LOOP_CONTINUOUSLY);
            this.clip.start();
        }
    }

    /**
     * Pauses audio clip.
     *
     * @throws IllegalStateException if audio file is already playing.
     */
    @Override
    public void pause() throws IllegalStateException {
        if (this.status == AudioControls.PAUSE || this.status == AudioControls.STOP) {
            // do nothing
        } this.currentFrame = this.clip.getMicrosecondPosition();
        this.clip.stop();
        this.status = AudioControls.PAUSE;
    }

    /**
     * Resumes audio clip.
     *
     * @throws IllegalStateException if audio clip is already being played.
     */
    @Override
    public void resume() throws IllegalStateException {
        if (this.status == AudioControls.PLAY) {
            // do nothing;
        }
        this.clip.close();

        try {
            this.resetAudioStream();
        } catch (IllegalStateException e) {
            throw new IllegalStateException("Problem resuming audio file.");
        }

        this.clip.setMicrosecondPosition(this.currentFrame);
        this.status = AudioControls.PLAY;
        this.clip.loop(Clip.LOOP_CONTINUOUSLY);
        this.clip.start();
    }

    /**
     * Restarts audio clip.
     *
     * @throws IllegalStateException if there is a problem restarting the audio clip.
     */
    private void restart() throws IllegalStateException {
        this.clip.stop();
        this.clip.close();
        try {
            this.resetAudioStream();
        } catch (IllegalStateException e) {
            throw new IllegalStateException("Problem restarting audio file.");
        }
        this.currentFrame = 0L;
        this.clip.setMicrosecondPosition(0);
        this.status = AudioControls.RESTART;
        this.play();
    }

    /**
     * Stops audio clip.
     *
     * @throws IllegalStateException if there is a problem stopping the audio clip.
     */
    @Override
    public void stop() throws IllegalStateException{
        this.currentFrame = 0L;
        this.clip.stop();
        this.clip.close();
        this.status = AudioControls.STOP;
    }

    /**
     * Returns true if audio file, sunrise, and sound duration are all set, otherwise false.
     *
     * @return Returns true if audio file, sunrise, and sound duration are all set, otherwise false.
     */
    @Override
    public boolean readyForStart() {
        return this.sunriseSet && this.durationSet && this.audioSet;
    }

    /**
     * Returns how long the sound should play, an int, in minutes.
     *
     * @return an int of how long the sound should play, in minutes.
     */
    @Override
    public int getDuration() {
        return this.soundDurationMinute;
    }

    /**
     * Method to set how early the song should start playing before the given sunrise.
     *
     * @param hour   the number of hours before sunrise that the sound should start playing.
     * @param minute the number of minutes before sunrise that the sound should start playing.
     *
     * @throws IllegalArgumentException if hour or minute values are less than zero
     */
    @Override
    public void setStartOffset(int hour, int minute)  throws IllegalArgumentException {
        if (hour < 0 || minute < 0) {
            throw new IllegalArgumentException("Hour and minute values must be greater than 0");
        }

        this.startOffset = hour * 60 + minute;
    }

    /**
     * Method to set how early the song should start playing before the given sunrise.
     */
    @Override
    public int getStartOffset() {
        return this.startOffset;
    }

    /**
     * Method to set latitude of user.
     *
     * @param latitude latitude of user, a double.
     * @throws IllegalArgumentException if latitude value is not valid
     */
    @Override
    public void setLatitude(double latitude) throws IllegalArgumentException {
        if (!(latitude >= -90 && latitude <= 90)) {
            throw new IllegalArgumentException("Invalid latitude entry.");
        }

        this.latitude = latitude;

    }

    /**
     * Method to set longitude of user.
     *
     * @param longitude longitude of user, a double.
     * @throws IllegalArgumentException if longitude value is not valid
     */
    @Override
    public void setLongitude(double longitude) throws IllegalArgumentException {
        if (!(longitude >= -180 && longitude <= 180)) {
            throw new IllegalArgumentException("Invalid longitude entry.");
        }

        this.longitude = longitude;
    }

    /**
     * Method to get latitude of user.
     *
     * @return latitude of user, a double.
     * @throws IllegalStateException if latitude has not been set.
     */
    @Override
    public double getLatitude() throws IllegalStateException {
        if (this.latitude == null) {
            throw new IllegalStateException("Latitude has not been set.");
        };

        return this.latitude;
    }

    /**
     * Method to get longitude of user.
     *
     * @return longitude of user, a double.
     * @throws IllegalStateException if longitude has not been set.
     */
    @Override
    public double getLongitude() throws IllegalStateException {
        if (this.longitude == null) {
            throw new IllegalStateException("Longitude has not been set.");
        };

        return this.longitude;
    }

    /**
     * Resets audio stream.
     *
     * @throws IllegalStateException if there is an error reseting the audio stream.
     */
    private void resetAudioStream() throws IllegalStateException {
        try {
            this.audioInputStream = AudioSystem.getAudioInputStream(new File(this.filePath).getAbsoluteFile());
        } catch (UnsupportedAudioFileException | IOException e) {
            throw new IllegalStateException("Problem restarting audio stream.");
        }

        try {
            this.clip.open(this.audioInputStream);
        } catch ( IOException | LineUnavailableException e) {
            throw new IllegalStateException("Problem restarting audio stream.");
        }

        this.clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
}