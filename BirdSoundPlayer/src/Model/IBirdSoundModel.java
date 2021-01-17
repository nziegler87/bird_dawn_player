package Model;

import java.time.ZoneId;
import java.time.ZonedDateTime;

// audio file/clip handling based on code found here: https://www.geeksforgeeks.org/play-audio-file-using-java/

public interface IBirdSoundModel {

    /**
     * Loads an audio file (only AIFC, AIFF, AU, SND and WAVE formats are supported) to be played.
     *
     * @param filePath filepath to an AIFC, AIFF, AU, SND and WAVE file, a string.
     *
     * @throws IllegalStateException if there is a problem opening the audio file.
     */
    void loadFile(String filePath) throws IllegalStateException;

    /**
     * Manually set the start time. Hour should be in 24hr time.
     *
     * @param hour the sunrise hour, an int, that is greater than or equal to 0 and less than 24.
     * @param minute the sunrise minute, an int, that is greater than or equal to 0 and less than 60.
     *
     * @throws IllegalArgumentException if hour or minute values are less than zero, if hour value is greater than 24,
     *                                  and if minute value is greater than 60.
     */
    void manuallySetSunrise(int hour, int minute);

    /**
     * Automatically sets sunrise when passed a given longitude and latitude. Sunrise data is retrieved from
     * https://sunrise-sunset.org/api, which is parsed using the SunriseSunsetParser.
     *
     * @param latitude latitude of the location, a double
     * @param longitude longitude of the location, a double.
     */
    void automaticallySetSunrise(double latitude, double longitude);

    /**
     * Set length of time that sound should play after the start time.
     *
     * @param hour the number of hours the sound should play, a double.
     * @param minute the number of minutes the sound should play, a double.
     *
     * @throws IllegalArgumentException if hour or minute values are less than zero
     */
    void setSoundDuration(int hour, int minute) throws IllegalArgumentException;

    /**
     * Returns sunrise date time object.
     *
     * @return the sunrise object, a ZonedDateTime
     *
     * @throws IllegalStateException if sunrise object is still null.
     */
    ZonedDateTime getSunrise() throws IllegalArgumentException;

    /**
     * Returns a formatted version of the sunrise object as HH:MM:SS AM/PM based on a given time zone ID.
     *
     * @param timeZoneId a time zone ID, a string: https://en.wikipedia.org/wiki/List_of_tz_database_time_zones
     * @return a formatted version of the sunrise object as HH:MM:SS AM/PM based on the given time zone ID
     */
    String returnLocalTime(ZoneId timeZoneId);

    /**
     * Plays audio clip.
     */
    void play();

    /**
     * Pauses audio clip.
     *
     * @throws IllegalStateException if audio file is already playing.
     */
    void pause();

    /**
     * Resumes audio clip.
     *
     * @throws IllegalStateException if audio clip is already being played.
     */
    void resume() throws IllegalStateException;


    /**
     * Stops audio clip.
     *
     * @throws IllegalStateException if there is a problem stopping the audio clip.
     */
    void stop() throws IllegalStateException;

    /**
     * Returns true if audio file, sunrise, and sound duration are all set, otherwise false.
     *
     * @return Returns true if audio file, sunrise, and sound duration are all set, otherwise false.
     */
    boolean readyForStart();

    /**
     * Returns how long the sound should play, an int, in minutes.
     *
     * @return an int of how long the sound should play, in minutes.
     */
    int getDuration();

    /**
     * Method to set how early the song should start playing before the given sunrise.
     *
     * @param hour the number of hours before sunrise that the sound should start playing.
     * @param minute the number of minutes before sunrise that the sound should start playing.
     *
     * @throws IllegalArgumentException if hour or minute values are less than zero
     */
    void setStartOffset(int hour, int minute) throws IllegalArgumentException;

}
