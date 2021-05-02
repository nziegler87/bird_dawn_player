package Controller.Commands;

import Model.IBirdSoundModel;
import View.IBirdSoundView;

import java.awt.*;
import java.time.ZoneId;

public class ManuallySetSunrise implements ICommand {
    @Override
    public void go(IBirdSoundView view, IBirdSoundModel model) {
        int hour = 0;
        int min = 0;

        while (true) {
            hour = view.getIntegerInput("Enter the hour of your sunrise:");
            if (hour >= 0 && hour <= 24) {
                break;
            } else {
                view.displayPopUpMessage("Invalid hour entry. It must be greater or equal to 0 and less " +
                        "than or equal to 24");
            }
        }
        while (true) {
            min = view.getIntegerInput("Enter the minute of your sunrise:");
            if (min >= 0 && min <= 60) {
                break;
            } else {
                view.displayPopUpMessage("Invalid minute entry. It must be great or equal to 0 and less" +
                        " or equal to 60.");
            }
        }
        model.manuallySetSunrise(hour, min);
        model.setAutoSunrise(false);
        view.displayPopUpMessage("Sunrise set to " + model.returnLocalTimeOfSunrise(ZoneId.systemDefault()) +
                "\n\nIf this isn't correct, re-enter your sunrise information.");
        view.updateStatusMessage("Sunrise time set manually. Don't forget to update this periodically. Now set how early before dawn the sound should start.", new Color(0, 227, 176), new Color(0,0,0));
        view.updateSunriseText(model.returnLocalTimeOfSunrise(ZoneId.systemDefault()));
        view.enableStartOffsetButton();
    }
}
