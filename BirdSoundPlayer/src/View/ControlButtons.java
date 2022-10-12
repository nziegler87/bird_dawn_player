package View;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.concurrent.Flow;

public class ControlButtons extends JPanel {
    JButton loadFile, automaticallySetSunrise, manuallySetSunrise, setSoundDuration, startOffsetButton,
            playAudioButton, pauseAudioButton, stopAudioButton, goButton, stopButton;
    JPanel loadFilePanel, sunrisePanel, soundPanel, audioControlsPanel, masterControlPanel;
    Color backgroundColor = new Color(223, 216, 255);

    public ControlButtons(int width, int height) {
        super();
        this.setBackground(this.backgroundColor);
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        // load file Panel and Button
        this.loadFilePanel = new JPanel();
        this.loadFilePanel.setLayout(new FlowLayout());
        this.loadFilePanel.setBackground(new Color(245, 243, 255));


        this.loadFile = new JButton("Load file");
        this.loadFile.setActionCommand("load file");
        this.loadFilePanel.add(this.loadFile);

        this.add(this.loadFilePanel);

        // add sound control panel
        this.audioControlsPanel = new JPanel();
        this.audioControlsPanel.setLayout(new FlowLayout());
        this.audioControlsPanel.setBackground(new Color(223, 216, 255));

        // play button
        this.playAudioButton = this.setUpImageButton("BirdSoundPlayer/Images/play_resized.png", "play sound");
        this.audioControlsPanel.add(this.playAudioButton);

        // pause button
        this.pauseAudioButton = this.setUpImageButton("BirdSoundPlayer/Images/pause_resized.png", "pause sound");
        this.audioControlsPanel.add(this.pauseAudioButton);

        // stop button
        this.stopAudioButton = this.setUpImageButton("BirdSoundPlayer/Images/stop_resized.png", "stop sound");
        this.audioControlsPanel.add(this.stopAudioButton);

        this.add(this.audioControlsPanel);

        // add sunrise info buttons to panel
        this.sunrisePanel = new JPanel();
        this.sunrisePanel.setLayout(new FlowLayout());
        this.sunrisePanel.setBackground(new Color(245, 243, 255));


        // auto get sunrise button
        this.automaticallySetSunrise = new JButton("Automatically set sunrise");
        this.automaticallySetSunrise.setEnabled(false);
        this.sunrisePanel.add(this.automaticallySetSunrise);


        // manually set sunrise button
        this.manuallySetSunrise = new JButton("Manually set sunrise");
        this.manuallySetSunrise.setEnabled(false);
        this.sunrisePanel.add(this.manuallySetSunrise);

        this.add(this.sunrisePanel);

        // add sound info buttons
        this.soundPanel = new JPanel();
        this.soundPanel.setLayout(new FlowLayout());
        this.soundPanel.setBackground(new Color(223, 216, 255));

        // start offset button
        this.startOffsetButton = new JButton("Set start offset");
        this.startOffsetButton.setEnabled(false);
        this.soundPanel.add(this.startOffsetButton);

        // sound duration button
        this.setSoundDuration = new JButton("Set sound duration");
        this.setSoundDuration.setEnabled(false);
        this.soundPanel.add(this.setSoundDuration);

        this.add(this.soundPanel);

        // add control button panel
        this.masterControlPanel = new JPanel();
        this.masterControlPanel.setLayout(new FlowLayout());
        this.masterControlPanel.setBackground(new Color(245, 243, 255));


        // go button
        this.goButton = this.setUpImageButton("BirdSoundPlayer/Images/go_resized.png", "start");
        this.masterControlPanel.add(this.goButton);

        // power button
        this.stopButton = this.setUpImageButton("BirdSoundPlayer/Images/power_resized.png", "stop");
        this.masterControlPanel.add(this.stopButton);

        this.add(this.masterControlPanel);

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
        this.playAudioButton.addActionListener(listener);
        this.pauseAudioButton.addActionListener(listener);
        this.stopAudioButton.addActionListener(listener);
        this.goButton.addActionListener(listener);
        this.stopButton.addActionListener(listener);
        this.startOffsetButton.addActionListener(listener);
    }

    //TODO: If commenting these out has no impact, just delete
//    /**
//     * Disables the go button.
//     */
//    public void disableGoButton() {
//        this.goButton.setEnabled(false);
//    }
//
//    /**
//     * Enables the go button.
//     */
//    public void enableGoButton() {
//        this.goButton.setEnabled(true);
//    }
//
//    /**
//     * Enables the stop button.
//     */
//    public void enableStopButton() {
//        this.stopButton.setEnabled(true);
//    }
//
//    /**
//     * Disables the stop button.
//     */
//    public void disableStopButton() {
//        this.stopButton.setEnabled(false);
//    }
//
//    /**
//     * Enables the play button.
//     */
//    public void enablePlayButton() {
//        this.playButton.setEnabled(true);
//    }
//
//    /**
//     * Disables the play button.
//     */
//    public void disablePlayButton() {
//        this.playButton.setEnabled(false);
//    }
//
//    /**
//     * Enables the pause button.
//     */
//    public void enablePauseButton() {
//        this.pauseButton.setEnabled(true);
//    }
//
//    /**
//     * Disables the pause button.
//     */
//    public void disablePauseButton() {
//        this.pauseButton.setEnabled(false);
//    }
//
//    public void enableSoundDurationButton() {
//        this.setSoundDuration.setEnabled(true);
//    }
//
//    /**
//     * Enables the audio control buttons as a group
//     */
//    public void enableAudioControlButtons() {
//        this.playButton.setEnabled(true);
//        this.pauseButton.setEnabled(true);
//        this.stopButton.setEnabled(true);
//    }
//
//    /**
//     * Disables the audio control buttons as a group
//     */
//    public void disableAudioControlButtons() {
//        this.playButton.setEnabled(false);
//        this.pauseButton.setEnabled(false);
//        this.stopButton.setEnabled(false);
//    }
//
//    /**
//     * Method to enable the set offset button.
//     */
//    public void enableStartOffsetButton() {
//        this.startOffsetButton.setEnabled(true);
//    }
//
//    /**
//     * Method to disable the set offset button.
//     */
//    public void disableStartOffsetButton() {
//        this.startOffsetButton.setEnabled(false);
//    }


}
