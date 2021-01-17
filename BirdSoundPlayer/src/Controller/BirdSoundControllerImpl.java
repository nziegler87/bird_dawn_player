package Controller;


import Model.IBirdSoundModel;
import View.IBirdSoundView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.ZoneId;

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

    private void checkReady() {
        if (this.model.readyForStart()) {
            this.view.enableGoButton();
        }
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
                    this.view.enableSunriseButtons();
                }
                this.checkReady();
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
                double latitude = 0;
                double longitude = 0;

                while (true) {
                    latitude = this.view.getDoubleInput("Enter your latitude:");
                    if (latitude >= -90 && latitude <= 90) {
                        break;
                    } else {
                        this.view.displayPopUpMessage("Latitude value not valid. Try again.");
                    }
                }

                while (true) {
                    longitude = this.view.getDoubleInput("Enter your longitude:");
                    if (longitude >= -180 && longitude <= 180) {
                        break;
                    } else {
                        this.view.displayPopUpMessage("Longitude value not valid. Try again.");
                    }
                }
                try {
                    this.model.automaticallySetSunrise(latitude, longitude);
                    this.view.displayPopUpMessage("Sunrise set to " + this.model.returnLocalTime(ZoneId.systemDefault()) +
                            "\n\nIf this isn't correct, restart this process and confirm your latitude and longitude data.");
                    this.view.enableSoundDuration();
                    break;
                } catch (IllegalStateException ISE) {
                    this.view.displayPopUpMessage("Error getting sunrise data. Try again or manually input sunrise.");
                }
                break;

            case "Manually set sunrise":
                int hour = 0;
                int min = 0;

                while (true) {
                    hour = this.view.getIntegerInput("Enter the hour of your sunrise:");
                    if (hour >= 0 && hour <= 24) {
                        break;
                    } else {
                        this.view.displayPopUpMessage("Invalid hour entry. It must be greater or equal to 0 and less " +
                                "than or equal to 24");
                    }
                }
                while (true) {
                    min = this.view.getIntegerInput("Enter the minute of your sunrise:");
                    if (min >= 0 && min <= 60) {
                        break;
                    } else {
                        this.view.displayPopUpMessage("Invalid minute entry. It must be great or equal to 0 and less" +
                                " or equal to 60.");
                    }
                }
                this.model.manuallySetSunrise(hour, min);
                this.view.displayPopUpMessage("Sunrise set to " + this.model.returnLocalTime(ZoneId.systemDefault()) +
                        "\n\nIf this isn't correct, re-enter your sunrise information.");
                this.view.enableSoundDuration();
                break;

            case "Set sound duration":
                while (true) {
                    hour = this.view.getIntegerInput("Enter how many hours the sound should play:");
                    if (hour >= 0) {
                        break;
                    } else {
                        this.view.displayPopUpMessage("Hour value must be greater or equal to 0. Try again.");
                    }
                }
                while (true) {
                    min = this.view.getIntegerInput("Enter how many minutes the sound should play:");
                    if (min >= 0) {
                        break;
                    } else {
                        this.view.displayPopUpMessage("Minute value must be greater or equal to 0. Try again.");
                    }
                }
                this.model.setSoundDuration(hour, min);
                this.view.displayPopUpMessage("Your audio will play for " + hour + " hour(s) and " + min +
                        " minute(s).\nThat is equivalent to " + this.model.getDuration() + " minute(s).");
                this.view.enableStartOffsetButton();
                break;
            case "Set start offset":
                while (true) {
                    hour = this.view.getIntegerInput("Enter how many hours before sunrise the sound should play:");
                    if (hour >= 0) {
                        break;
                    } else {
                        this.view.displayPopUpMessage("Hour value must be greater or equal to 0. Try again.");
                    }
                }
                while (true) {
                    min = this.view.getIntegerInput("Enter how many minutes before sunrise the sound should play:");
                    if (min >= 0) {
                        break;
                    } else {
                        this.view.displayPopUpMessage("Minute value must be greater or equal to 0. Try again.");
                    }
                }
                this.model.setSoundDuration(hour, min);
                this.view.displayPopUpMessage("Your audio will play " + hour + " hour(s) and " + min +
                        " minute(s) before sunrise.\nThat is equivalent to " + this.model.getDuration() + " minute(s).");
                this.view.enableGoButton();
                break;
            case "go":
                //http://tutorials.jenkov.com/java-util-concurrent/scheduledexecutorservice.html
                this.view.enableStopButton();
                break;
        }
    }
}

