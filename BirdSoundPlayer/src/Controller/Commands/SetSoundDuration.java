package Controller.Commands;

import Model.IBirdSoundModel;
import View.IBirdSoundView;

import java.awt.*;

public class SetSoundDuration implements ICommand {
    @Override
    public void go(IBirdSoundView view, IBirdSoundModel model) {
        int hour = 0;
        int min = 0;
        while (true) {
            hour = view.getIntegerInput("Enter how many hours the sound should play:");
            if (hour >= 0) {
                break;
            } else {
                view.displayPopUpMessage("Hour value must be greater or equal to 0. Try again.");
            }
        }
        while (true) {
            min = view.getIntegerInput("Enter how many minutes the sound should play:");
            if (min >= 0) {
                break;
            } else {
                view.displayPopUpMessage("Minute value must be greater or equal to 0. Try again.");
            }
        }
        model.setSoundDuration(hour, min);
        view.displayPopUpMessage("Your audio will play for " + hour + " hour(s) and " + min +
                " minute(s).\nThat is equivalent to " + model.getDuration() + " minute(s).");
        view.updateDurationText("" + hour + " hours " + min + " mins ");
        view.updateStatusMessage("Sound duration set. Now hit go!", new Color(118, 7, 139), new Color(0,0,0));

        view.enableGoButton();
    }
}
