package Model;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.ZonedDateTime;


public interface IBirdSoundModel {

    /**
     * Loads an audio file (must be .WAV) to be played.
     *
     * @param filePath the audio filepath, a string.
     *
     * @throws IllegalStateException //TODO: Fill in!
     * @throws IOException
     * @throws UnsupportedAudioFileException
     * @throws LineUnavailableException
     */
    void loadFile(String filePath) throws IllegalStateException, IOException, UnsupportedAudioFileException, LineUnavailableException;


    /**
     * Manually set the start time, always AM.
     *
     * @param hour the hour it is to start
     * @param minute the hour it is to end
     */
    void manuallySetSunrise(int hour, int minute);

    /**
     * Automatically sets sunrise when passed a given longitude and latitude.
     * @param latitude
     * @param longitude
     */
    void automaticallySetSunrise(double latitude, double longitude);

    /**
     * Set length of time that sound should play after the start time.
     *
     * @param hour the number of hours the sound should play, a double.
     * @param minute the number of minutes the sound should play, a double.
     */
    void setSoundDuration(int hour, int minute);

    /**
     * Plays audio sound.
     */
    void play();

    void pause();

    void resume() throws UnsupportedAudioFileException, IOException, LineUnavailableException;

    void restart() throws IOException, LineUnavailableException, UnsupportedAudioFileException;

    void stop() throws UnsupportedAudioFileException, IOException, LineUnavailableException;

    void resetAudioStream() throws UnsupportedAudioFileException, IOException, LineUnavailableException;

    /**
     * Get's sunrise data from https://sunrise-sunset.org/api, which is parsed using the SunriseSunsetParser. It doesn't
     * return anything but instead updates the sunrise field in the model.
     * @throws IOException //TODO: Fill this out.
     * @throws URISyntaxException
     * @throws InterruptedException
     */
    ZonedDateTime getSunrise() throws IOException, URISyntaxException, InterruptedException;

    String printLocalTime(String timeZoneId);
}
