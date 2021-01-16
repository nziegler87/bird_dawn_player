package View;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;


public class BirdSoundViewImpl extends JFrame implements IBirdSoundView {
    private final int windowWidth = 800;
    private final int windowHeight = 600;
    private final int verticalGap= 5;
    private final int horizontalGap = 5;
    private final int actionBarHeight= 25;
    private final JFileChooser fileChooser = new JFileChooser();
    private JFrame popUpWindow = new JFrame();

    private final StatusBar statusBar;
    private final ControlButtons controlButtons;

    public BirdSoundViewImpl() {

        // basic settings
        super();
        this.setTitle("Dawn Song PLayer");
        this.setSize(this.windowWidth, this.windowHeight);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout(this.verticalGap, this.horizontalGap));

        // add status bar
        this.statusBar = new StatusBar(this.windowWidth, this.actionBarHeight);
        this.add(statusBar, BorderLayout.PAGE_START);

        // add instructions
        InstructionPanel instructionPanel = new InstructionPanel(this.windowWidth / 3, this.windowHeight);
        this.add(instructionPanel, BorderLayout.LINE_START);

        // add control buttons
        this.controlButtons = new ControlButtons(this.windowWidth / 3, this.windowHeight);
        this.add(controlButtons, BorderLayout.CENTER);

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
        this.statusBar.setStatus(message, bgColor, fgColor);
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
        String userInput = JOptionPane.showInputDialog(popUpWindow, message, "", JOptionPane.PLAIN_MESSAGE);
        if (userInput == null) {
            System.out.println("User canceled");
            return 0.0;
        } else {
            return Double.parseDouble(userInput);
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
        String userInput = JOptionPane.showInputDialog(popUpWindow, message, "", JOptionPane.PLAIN_MESSAGE);
        int userInteger = Integer.parseInt(userInput);
        if (userInput.equals("")) {
            return 0;
        } else {
            return userInteger;
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
        this.controlButtons.enableGoButton();
    }

    /**
     * Method to disable the go button.
     */
    @Override
    public void disableGoButton() {
        this.controlButtons.disableGoButton();
    }

    /**
     * Method to enable the stop button.
     */
    @Override
    public void enableStopButton() {
        this.controlButtons.enableStopButton();
    }

    /**
     * Method to disable the stop button.
     */
    @Override
    public void disableStopButton() {
        this.controlButtons.disableStopButton();
    }

    /**
     * Method to enable the sound duration button.
     */
    @Override
    public void enableSoundDuration() {
        this.controlButtons.enableSoundDurationButton();
    }


    /**
     * Method to enable buttons that control audio (play, pause, stop).
     */
    @Override
    public void enablePlayControls() {
        this.controlButtons.enablePlayButton();
        this.controlButtons.enablePauseButton();
        this.controlButtons.enableStopButton();
    }

    /**
     * Method to disable buttons that control audio (play, pause, stop).
     */
    @Override
    public void disablePlayControls() {
        this.controlButtons.disableStopButton();
        this.controlButtons.disablePauseButton();
        this.controlButtons.disablePlayButton();
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
}
