package Controller;


import Model.IBirdSoundModel;
import View.IBirdSoundView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.ZoneId;
import java.util.Objects;

public class BirdSoundControllerImpl implements IBirdSoundController, ActionListener {
    private IBirdSoundModel model;
    private IBirdSoundView view;

    public BirdSoundControllerImpl(IBirdSoundModel model, IBirdSoundView view) {
        this.model = model;
        this.view = view;
        this.view.setListener(this);

    }

    @Override
    public void go() throws IllegalStateException {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        switch (e.getActionCommand()) {
            case "Load file":

                // get file name using JLoadFile
                String audioFileName = this.view.selectAudioFile();

                // as long as the user provided a valid file type, load the file
                if (audioFileName != null) {
                    this.view.updateStatusMessage("Audio file loaded. Test your file using the audio controls. " +
                                    "Then, automatically set sunrise or manually set sunrise.", new Color(34, 72, 163),
                            new Color(255, 255, 255));
                    this.model.loadFile(audioFileName);
                    this.view.enablePlayControls();
                }
                break;
            case "play":
                this.model.play();
                break;

            case "pause":
                this.model.pause();
                break;

            case "stop":
                this.model.stop();
                break;

            case "Automatically set sunrise":
                double lat = this.view.getDoubleInput("Enter your latitude:");
                double longitude = this.view.getDoubleInput("Enter your longitude:");
                this.model.automaticallySetSunrise(lat, longitude);
                this.view.displayPopUpMessage(this.model.printLocalTime(ZoneId.systemDefault()));
                break;

            case "Manually set sunrise":
                int hour = this.view.getIntegerInput("Enter the hour of your sunrise:");
                int min = this.view.getIntegerInput("Enter the minute of your sunrise:");
                this.model.manuallySetSunrise(hour, min);
                break;

            case "Set sound duration":
                hour = this.view.getIntegerInput("Enter how many hours the sound should play:");
                min = this.view.getIntegerInput("Enter how many minutes the sound should play:");
                this.model.setSoundDuration(hour, min);
                break;
        }
    }
}
