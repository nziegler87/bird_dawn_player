package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ControlButtons extends JPanel {
    JButton loadFile, automaticallySetSunrise, manuallySetSunrise, setSoundDuration, playButton, pauseButton,
            stopButton, goButton, powerButton;
    Color backgroundColor = new Color(223, 216, 255);

    public ControlButtons(int width, int height) {
        super();
        this.setBackground(this.backgroundColor);


        // load file button
        this.loadFile = new JButton("Load file");
        this.add(loadFile);

        // auto get sunrise button
        this.automaticallySetSunrise = new JButton("Automatically set sunrise");
        this.add(automaticallySetSunrise);

        // manually set sunrise button
        this.manuallySetSunrise = new JButton("Manually set sunrise");
        this.add(manuallySetSunrise);

        // sound duration button
        this.setSoundDuration = new JButton("Set sound duration");
        this.add(setSoundDuration);

        // play button
        this.playButton = this.setUpButton("./images/play_resized.png", "play");
        this.add(this.playButton);

        // pause button
        this.pauseButton = this.setUpButton("./images/pause_resized.png", "pause");
        this.add(this.pauseButton);

        // stop button
        this.stopButton = this.setUpButton("./images/stop_resized.png", "stop");
        this.add(this.stopButton);

        // go button
        this.goButton = this.setUpButton("./images/go_resized.png", "go");
        this.add(this.goButton);

        // power button
        this.powerButton = this.setUpButton("./images/power_resized.png", "powerOff");
        this.add(this.powerButton);

        // start with most buttons disabled
        this.playButton.setEnabled(false);
        this.pauseButton.setEnabled(false);
        this.stopButton.setEnabled(false);
        this.goButton.setEnabled(false);
        this.powerButton.setEnabled(false);

        this.setVisible(true);
    }

    /**
     * Method to set up icon buttons as deactivated using given image string and command.
     *
     * @param imageString location of the icon file, a string
     * @param command action command for the button, a string
     * @return a new JButton
     */
    private JButton setUpButton(String imageString, String command){
        ImageIcon icon = new ImageIcon(imageString);
        JButton button = new JButton(icon);
        button.setActionCommand(command);
        button.setBorderPainted(false);
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


}
