package Controller.Commands;

import Model.IBirdSoundModel;
import View.IBirdSoundView;

public class SetStartOffset implements ICommand {
    @Override
    public void go(IBirdSoundView view, IBirdSoundModel model) {
        int hour = 0;
        int min = 0;
        
        while (true) {
            hour = view.getIntegerInput("Enter how many hours before sunrise the sound should play:");
            if (hour >= 0) {
                break;
            } else {
                view.displayPopUpMessage("Hour value must be greater or equal to 0. Try again.");
            }
        }
        while (true) {
            min = view.getIntegerInput("Enter how many minutes before sunrise the sound should play:");
            if (min >= 0) {
                break;
            } else {
                view.displayPopUpMessage("Minute value must be greater or equal to 0. Try again.");
            }
        }
        model.setSoundDuration(hour, min);
        view.displayPopUpMessage("Your audio will play " + hour + " hour(s) and " + min +
                " minute(s) before sunrise.\nThat is equivalent to " + model.getDuration() + " minute(s).");
        view.enableSoundDuration();
    }
}
