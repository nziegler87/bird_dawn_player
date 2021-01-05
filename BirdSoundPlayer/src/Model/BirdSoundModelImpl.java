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

// many methods copied from https://www.geeksforgeeks.org/play-audio-file-using-java/

public class BirdSoundModelImpl implements IBirdSoundModel {
    private Clip clip;
    private String filePath;
    private File audioFile;
    private AudioInputStream audioInputStream;
    private AudioControls status;
    private long currentFrame;
    private ZonedDateTime sunrise;
    private int soundDurationHour;
    private int soundDurationMinute;

    public BirdSoundModelImpl() {
    }

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
        } catch (LineUnavailableException | IOException IOE) {
            throw new IllegalStateException("Error opening line.");
        }

            this.clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    @Override
    public void manuallySetSunrise(int hour, int minute) throws IllegalArgumentException {
        if (hour < 0 || minute < 0) {
            throw new IllegalArgumentException("Hour and minute values must be greater than 0.");
        }

        LocalDateTime currentDateTime = LocalDateTime.now();
        this.sunrise = ZonedDateTime.of(currentDateTime.getYear(), currentDateTime.getMonthValue(),
                currentDateTime.getDayOfMonth(), hour, minute, 0, 0, ZoneId.systemDefault());
    }


    @Override
    public void setSoundDuration(int hour, int minute) throws IllegalArgumentException {
        if (hour < 0 || minute < 0) {
            throw new IllegalArgumentException("Hour and minute values must be greater than 0.");
        }

        this.soundDurationHour = hour;
        this.soundDurationMinute = minute;
    }

    @Override
    public void play() {
        this.clip.start();
        this.status = AudioControls.PLAY;
        while (true) {}
    }

    @Override
    public void pause() {
        if (this.status == AudioControls.PAUSE) {
            System.out.println("audio is already paused");
            return;
        } this.currentFrame = this.clip.getMicrosecondPosition();
        this.clip.stop();
        this.status = AudioControls.PAUSE;
    }

    @Override
    public void resume() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        if (this.status == AudioControls.PLAY) {
            System.out.println("Audio is already "+ "being played");
            return;
        }
        this.clip.close();
        resetAudioStream();
        this.clip.setMicrosecondPosition(this.currentFrame);
        this.play();
    }

    @Override
    public void restart() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        this.clip.stop();
        this.clip.close();
        this.resetAudioStream();
        this.currentFrame = 0L;
        this.clip.setMicrosecondPosition(0);
        this.play();
    }

    @Override
    public void stop() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        this.currentFrame = 0L;
        this.clip.stop();
        this.clip.close();
    }

    @Override
    public void resetAudioStream() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        this.audioInputStream = AudioSystem.getAudioInputStream(new File(this.filePath).getAbsoluteFile());
        this.clip.open(this.audioInputStream);
        this.clip.loop(Clip.LOOP_CONTINUOUSLY);
    }


    @Override
    public void automaticallySetSunrise(double latitude, double longitude) throws IllegalStateException {

        String url = "https://api.sunrise-sunset.org/json?lat=" + latitude + "&lng=" + longitude + "8&formatted=0";
        // create a client
        var client = HttpClient.newHttpClient();

        // create a request
        var request = HttpRequest.newBuilder(URI.create(url)).build();

        // use the client to send the request
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            this.sunrise = new SunriseSunsetParser(response.body()).getSunrise();
        } catch (IOException | InterruptedException e) {
            throw new IllegalStateException("Error sending request to sunrise-sunset api.");
        }
    }

    public ZonedDateTime getSunrise() {
        if (this.sunrise == null) {
            throw new IllegalStateException("Sunrise data needs to be automatically set or manually entered.");
        }

        return this.sunrise;
    }

    @Override
    public String printLocalTime(String timeZoneID) {
        try {
            ZoneId timeZone = ZoneId.of(timeZoneID);
            ZonedDateTime newDate = this.sunrise.withZoneSameInstant(timeZone);
            String DATE_FORMAT = "hh:mm:ss a";
            DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_FORMAT);
            return format.format(newDate);

        } catch (ZoneRulesException e) {
            throw new IllegalStateException("Unknown time-zone ID.");
        }
    }
}
