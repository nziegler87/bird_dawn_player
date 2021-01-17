package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ControlButtons extends JPanel {
    JButton loadFile, automaticallySetSunrise, manuallySetSunrise, setSoundDuration, startOffsetButton,
            playButton, pauseButton, stopButton, goButton, powerButton;
    Color backgroundColor = new Color(223, 216, 255);

    public ControlButtons(int width, int height) {
        super();
        this.setBackground(this.backgroundColor);


        // load file button
        this.loadFile = new JButton("Load file");
        this.loadFile.setActionCommand("load file");
        this.add(this.loadFile);

        // auto get sunrise button
        this.automaticallySetSunrise = new JButton("Automatically set sunrise");
        this.add(this.automaticallySetSunrise);
        this.automaticallySetSunrise.setEnabled(false);

        // manually set sunrise button
        this.manuallySetSunrise = new JButton("Manually set sunrise");
        this.add(this.manuallySetSunrise);
        this.manuallySetSunrise.setEnabled(false);

        // sound duration button
        this.setSoundDuration = new JButton("Set sound duration");
        this.add(this.setSoundDuration);
        this.setSoundDuration.setEnabled(false);

        // start offset button
        this.startOffsetButton = new JButton("Set start offset");
        this.add(this.startOffsetButton);
        this.startOffsetButton.setEnabled(false);

        // play button
        this.playButton = this.setUpImageButton("./images/play_resized.png", "play sound");
        this.add(this.playButton);

        // pause button
        this.pauseButton = this.setUpImageButton("./images/pause_resized.png", "pause sound");
        this.add(this.pauseButton);

        // stop button
        this.stopButton = this.setUpImageButton("./images/stop_resized.png", "stop sound");
        this.add(this.stopButton);

        // go button
        this.goButton = this.setUpImageButton("./images/go_resized.png", "start");
        this.add(this.goButton);

        // power button
        this.powerButton = this.setUpImageButton("./images/power_resized.png", "stop");
        this.add(this.powerButton);

        this.setVisible(true);
    }

    /**
     * Method to set up icon buttons as deactivated using given image string and command.
     *
     * @param imageString location of the icon file, a string
     * @param command action command for the button, a string
     * @return a new JButton
     */
    private JButton setUpImageButton(String imageString, String command){
        ImageIcon icon = new ImageIcon(imageString);
        JButton button = new JButton(icon);
        button.setActionCommand(command);
        button.setBorderPainted(false);
        button.setEnabled(false);
        return button;
    }

    /**
     * Method to set up text buttons as deactivated.
     *
     * @param command action command for the button, a string
     * @return a new JButton
     */
    private JButton setUpTextButton(String command){
        JButton button = new JButton(command);

        return button;
    }

    /**
     * Method used in setListener method of the view to ultimately set the controller as the listener.
     *
     * @param listener an action listener
     */
    public void setListener(ActionListener listener) {
        this.loadFile.addActionListener(listener);
        this.automaticallySetSunrise.addActionListener(listener);
        this.manuallySetSunrise.addActionListener(listener);
        this.setSoundDuration.addActionListener(listener);
        this.playButton.addActionListener(listener);
        this.pauseButton.addActionListener(listener);
        this.stopButton.addActionListener(listener);
        this.goButton.addActionListener(listener);
        this.powerButton.addActionListener(listener);
        this.startOffsetButton.addActionListener(listener);
    }

    /**
     * Disables the go button.
     */
    public void disableGoButton() {
        this.goButton.setEnabled(false);
    }

    /**
     * Enables the go button.
     */
    public void enableGoButton() {
        this.goButton.setEnabled(true);
    }

    /**
     * Enables the stop button.
     */
    public void enableStopButton() {
        this.stopButton.setEnabled(true);
    }

    /**
     * Disables the stop button.
     */
    public void disableStopButton() {
        this.stopButton.setEnabled(false);
    }

    /**
     * Enables the play button.
     */
    public void enablePlayButton() {
        this.playButton.setEnabled(true);
    }

    /**
     * Disables the play button.
     */
    public void disablePlayButton() {
        this.playButton.setEnabled(false);
    }

    /**
     * Enables the pause button.
     */
    public void enablePauseButton() {
        this.pauseButton.setEnabled(true);
    }

    /**
     * Disables the pause button.
     */
    public void disablePauseButton() {
        this.pauseButton.setEnabled(false);
    }

    public void enableSoundDurationButton() {
        this.setSoundDuration.setEnabled(true);
    }

    /**
     * Enables the audio control buttons as a group
     */
    public void enableAudioControlButtons() {
        this.playButton.setEnabled(true);
        this.pauseButton.setEnabled(true);
        this.stopButton.setEnabled(true);
    }

    /**
     * Disables the audio control buttons as a group
     */
    public void disableAudioControlButtons() {
        this.playButton.setEnabled(false);
        this.pauseButton.setEnabled(false);
        this.stopButton.setEnabled(false);
    }

    /**
     * Method to enable the set offset button.
     */
    public void enableStartOffsetButton() {
        this.startOffsetButton.setEnabled(true);
    }

    /**
     * Method to disable the set offset button.
     */
    public void disableStartOffsetButton() {
        this.startOffsetButton.setEnabled(false);
    }


}
