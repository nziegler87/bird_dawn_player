package View;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionListener;


public class BirdSoundViewImpl extends JFrame implements IBirdSoundView {
    private final int windowWidth = 1000;
    private final int windowHeight = 600;
    private final int verticalGap= 5;
    private final int horizontalGap = 5;
    private final int actionBarHeight= 25;
    private final JFileChooser fileChooser = new JFileChooser();
    private final JFrame popUpWindow = new JFrame();

    private final TopStatusBar topStatusBar;
    private final ControlButtons controlButtons;
    private final BottomStatusPanel bottomStatusPanel;

    public BirdSoundViewImpl() {

        // basic settings
        super();
        this.setTitle("Dawn Song PLayer");
        this.setSize(this.windowWidth, this.windowHeight);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout(this.verticalGap, this.horizontalGap));

        // add status bar
        this.topStatusBar = new TopStatusBar(this.windowWidth, this.actionBarHeight);
        this.add(this.topStatusBar, BorderLayout.PAGE_START);

        // add instructions
        InstructionPanel instructionPanel = new InstructionPanel(this.windowWidth / 3, this.windowHeight);
        this.add(instructionPanel, BorderLayout.LINE_START);

        // add control buttons
        this.controlButtons = new ControlButtons(this.windowWidth / 3, this.windowHeight);
        this.add(this.controlButtons, BorderLayout.CENTER);

        // add status panel
        this.bottomStatusPanel = new BottomStatusPanel();
        this.add(this.bottomStatusPanel, BorderLayout.PAGE_END);

        this.setVisible(true);
    }

    /**
     * Method to allow user to select an audio file (AIFC, AIFF, AU SND, or WAVE only) and returns the filepath of the
     * file.
     *
     * @return the filepath of the selected audio file or null if the user canceled
     */
    @Override
    public String selectAudioFile() {
        while (true) {

            // open file window
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.setFileFilter(new FileNameExtensionFilter("AIFC, AIFF, AU, SND, or WAVE files only",
                    "AIFC", "AIFF", "AU", "SND", "WAV"));
            int returnValue = fileChooser.showOpenDialog(popUpWindow);

            // return either file path or null if user canceled
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                return fileChooser.getSelectedFile().getAbsolutePath();
            }
            if (returnValue == JFileChooser.CANCEL_OPTION) {
                return null;
            }

            JOptionPane.showMessageDialog(popUpWindow, "Not a valid file type.");

            }
        }

    /**
     * Method to update the status bar of the view.
     *
     * @param message the message to be displayed, a string.
     * @param bgColor the background color, a color object.
     * @param fgColor the font color, a color object.
     */
    @Override
    public void updateStatusMessage(String message, Color bgColor, Color fgColor) {
        this.topStatusBar.setStatus(message, bgColor, fgColor);
    }

    /**
     * Method to ask user for a double and return the value.
     *
     * @param message the message to display to the user.
     *
     * @return a double, if entered, or null if user canceled.
     */
    @Override
    public Double getDoubleInput(String message) {
        while (true) {
            String userInput = JOptionPane.showInputDialog(popUpWindow, message, "", JOptionPane.PLAIN_MESSAGE);
            if (userInput == null) {
                System.out.println("User canceled");
                return 0.0;
            } else {
                try {
                    return Double.parseDouble(userInput);
                } catch (NumberFormatException NFE2) {
                    this.displayPopUpMessage("Input must be a number.");
                }
            }
        }
    }

    /**
     * Method to ask user for a integer and return the value.
     *
     * @param message the message to display to the user.
     *
     * @return an integer, if entered, or null if user canceled.
     */
    @Override
    public Integer getIntegerInput(String message) {
        while (true) {
            String userInput = JOptionPane.showInputDialog(popUpWindow, message, "", JOptionPane.PLAIN_MESSAGE);
            if (userInput == null) {
                return 0;
            } else {
                try {
                    return Integer.parseInt(userInput);
                } catch (NumberFormatException NFE) {
                    this.displayPopUpMessage("Input must be a number.");
                }
            }
        }
    }

    /**
     * Method to display a message to the user.
     *
     * @param message the message to be displayed, a string.
     */
    public void displayPopUpMessage(String message) {
        JOptionPane.showMessageDialog(popUpWindow, message, "", JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Method to enable the go button.
     */
    @Override
    public void enableGoButton() {
        this.controlButtons.goButton.setEnabled(true);
    }

    /**
     * Method to disable the go button.
     */
    @Override
    public void disableGoButton() {
        this.controlButtons.goButton.setEnabled(false);
    }

    /**
     * Method to enable the stop button.
     */
    @Override
    public void enableStopButton() {
        this.controlButtons.stopButton.setEnabled(true);
    }

    /**
     * Method to disable the stop button.
     */
    @Override
    public void disableStopButton() {
        this.controlButtons.stopButton.setEnabled(false);
    }

    /**
     * Method to enable the sound duration button.
     */
    @Override
    public void enableSoundDuration() {
        this.controlButtons.setSoundDuration.setEnabled(true);
    }

    /**
     * Method to enable the set offset button.
     */
    @Override
    public void enableStartOffsetButton() {
        this.controlButtons.startOffsetButton.setEnabled(true);
    }

    /**
     * Method to disable the set offset button.
     */
    @Override
    public void disableStartOffsetButton() {
        this.controlButtons.startOffsetButton.setEnabled(false);
    }

    /**
     * Method to enable the sunrise buttons.
     */
    @Override
    public void enableSunriseButtons() {
        this.controlButtons.manuallySetSunrise.setEnabled(true);
        this.controlButtons.automaticallySetSunrise.setEnabled(true);
    }


    /**
     * Method to enable buttons that control audio (play, pause, stop).
     */
    @Override
    public void enablePlayControls() {
        this.controlButtons.playAudioButton.setEnabled(true);
        this.controlButtons.pauseAudioButton.setEnabled(true);
        this.controlButtons.stopAudioButton.setEnabled(true);
    }

    /**
     * Method to disable buttons that control audio (play, pause, stop).
     */
    @Override
    public void disablePlayControls() {
        this.controlButtons.playAudioButton.setEnabled(false);
        this.controlButtons.pauseAudioButton.setEnabled(false);
        this.controlButtons.stopAudioButton.setEnabled(false);
    }

    /**
     * Method to set action listener, used to set the controller as the listener.
     *
     * @param listener an action listener.
     */
    @Override
    public void setListener(ActionListener listener) {
        this.controlButtons.setListener(listener);
    }

    @Override
    public void updateSunriseText(String sunriseText){
        this.bottomStatusPanel.sunrise.setText(sunriseText);
    }

    /**
     * Method to update the latitude text on the status panel.
     *
     * @param latitudeText
     */
    @Override
    public void updateLatitudeText(String latitudeText) {
        this.bottomStatusPanel.latitude.setText(latitudeText);
    }

    /**
     * Method to update the longitude text on the status panel.
     *
     * @param longitudeText
     */
    @Override
    public void updateLongitudeText(String longitudeText) {
        this.bottomStatusPanel.longitude.setText(longitudeText);
    }

    /**
     * Method to update the duration text on the status panel.
     *
     * @param durationText
     */
    @Override
    public void updateDurationText(String durationText) {
        this.bottomStatusPanel.duration.setText(durationText);
    }

    /**
     * Method to update the offset text on the status panel.
     *
     * @param offsetText
     */
    @Override
    public void updateOffsetText(String offsetText) {
        this.bottomStatusPanel.offset.setText(offsetText);
    }
}
