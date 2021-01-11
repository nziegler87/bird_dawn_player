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

    public String selectAudioFile() {
        while (true) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.setFileFilter(new FileNameExtensionFilter("AIFC, AIFF, AU, SND, or WAVE files only",
                    "AIFC", "AIFF", "AU", "SND", "WAV"));
            int returnValue = fileChooser.showOpenDialog(popUpWindow);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                return fileChooser.getSelectedFile().getAbsolutePath();
            }
            if (returnValue == JFileChooser.CANCEL_OPTION) {
                return null;
            }
            JOptionPane.showMessageDialog(popUpWindow, "Not a valid file type.");

            }
        }

    @Override
    public void updateStatusMessage(String message, Color bgColor, Color fgColor) {
        this.statusBar.setStatus(message, bgColor, fgColor);
    }

    @Override
    public Double getDoubleInput(String message) {
        String userInput = JOptionPane.showInputDialog(popUpWindow, message, "", JOptionPane.PLAIN_MESSAGE);
        double userDouble = Double.parseDouble(userInput);
        if (userDouble == JOptionPane.CANCEL_OPTION) {
            return null;
        } else {
            return userDouble;
        }
    }

    @Override
    public Integer getIntegerInput(String message) {
        String userInput = JOptionPane.showInputDialog(popUpWindow, message, "", JOptionPane.PLAIN_MESSAGE);
        int userInteger = Integer.parseInt(userInput);
        if (userInteger == JOptionPane.CANCEL_OPTION) {
            return null;
        } else {
            return userInteger;
        }
    }

    public void displayPopUpMessage(String message) {
        JOptionPane.showMessageDialog(popUpWindow, message, "", JOptionPane.PLAIN_MESSAGE);
    }

    public void toggleGoButton() {
        this.controlButtons.enableAudioControlButtons();
    }

    @Override
    public void enablePlayControls() {
        this.controlButtons.enablePlayButton();
        this.controlButtons.enablePauseButton();
        this.controlButtons.enableStopButton();
    }

    @Override
    public void disablePlayControls() {
        this.controlButtons.disableStopButton();
        this.controlButtons.disablePauseButton();
        this.controlButtons.disablePlayButton();
    }

    @Override
    public void setListener(ActionListener listener) {
        this.controlButtons.setListener(listener);
    }
}
