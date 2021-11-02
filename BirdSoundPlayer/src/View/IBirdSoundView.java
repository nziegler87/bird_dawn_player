package View;

import java.awt.*;
import java.awt.event.ActionListener;

public interface IBirdSoundView {

    /**
     * Method to set action listener, used to set the controller as the listener.
     *
     * @param listener an action listener.
     */
    void setListener(ActionListener listener);

    /**
     * Method to enable buttons that control audio (play, pause, stop).
     */
    void enablePlayControls();

    /**
     * Method to disable buttons that control audio (play, pause, stop).
     */
    void disablePlayControls();

    /**
     * Method to allow user to select an audio file (AIFC, AIFF, AU SND, or WAVE only) and returns the filepath of the
     * file.
     *
     * @return the filepath of the selected audio file or null if the user canceled
     */
    String selectAudioFile();

    /**
     * Method to update the status bar of the view.
     *
     * @param message the message to be displayed, a string.
     * @param bgColor the background color, a color object.
     * @param fgColor the font color, a color object.
     */
    void updateStatusMessage(String message, Color bgColor, Color fgColor);

    /**
     * Method to ask user for a double and return the value.
     *
     * @param message the message to display to the user.
     *
     * @return a double, if entered, or null if user canceled.
     */
    Double getDoubleInput(String message);

    /**
     * Method to ask user for a integer and return the value.
     *
     * @param message the message to display to the user.
     *
     * @return an integer, if entered, or null if user canceled.
     */
    Integer getIntegerInput(String message);

    /**
     * Method to display a message to the user.
     *
     * @param message the message to be displayed, a string.
     */
    void displayPopUpMessage(String message);

    /**
     * Method to enable the go button.
     */
    void enableGoButton();

    /**
     * Method to disable the go button.
     */
    void disableGoButton();

    /**
     * Method to enable the stop button.
     */
    void enableStopButton();

    /**
     * Method to disable the stop button.
     */
    void disableStopButton();

    /**
     * Method to enable the sound duration button.
     */
    void enableSoundDuration();

    /**
     * Method to enable the set offset button.
     */
    void enableStartOffsetButton();

    /**
     * Method to disable the set offset button.
     */
    void disableStartOffsetButton();

    /**
     * Method to enable the sunrise buttons.
     */
    void enableSunriseButtons();

    /**
     * Method to update the sunrise text on the status panel.
     *
     * @param sunriseText text to add to the screen
     */
    void updateSunriseText(String sunriseText);

    /**
     * Method to update the latitude text on the status panel.
     * @param latitudeText
     */
    void updateLatitudeText(String latitudeText);

    /**
     * Method to update the longitude text on the status panel.
     *
     * @param longitudeText
     */
    void updateLongitudeText(String longitudeText);

    /**
     * Method to update the duration text on the status panel.
     *
     * @param durationText
     */
    void updateDurationText(String durationText);

    /**
     * Method to update the offset text on the status panel.
     *
     * @param offsetText
     */
    void updateOffsetText(String offsetText);

    void updateAutoText(String offsetText);
}
