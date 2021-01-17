package Controller.Commands;

import Model.IBirdSoundModel;
import View.IBirdSoundView;

import java.time.ZoneId;

public class AutomaticallySetSunrise implements ICommand {
    @Override
    public void go(IBirdSoundView view, IBirdSoundModel model) {
        double latitude = 0;
        double longitude = 0;

        while (true) {
            latitude = view.getDoubleInput("Enter your latitude:");
            if (latitude >= -90 && latitude <= 90) {
                break;
            } else {
                view.displayPopUpMessage("Latitude value not valid. Try again.");
            }
        }

        while (true) {
            longitude = view.getDoubleInput("Enter your longitude:");
            if (longitude >= -180 && longitude <= 180) {
                break;
            } else {
                view.displayPopUpMessage("Longitude value not valid. Try again.");
            }
        }
        try {
            model.automaticallySetSunrise(latitude, longitude);
            view.displayPopUpMessage("Sunrise set to " + model.returnLocalTime(ZoneId.systemDefault()) +
                    "\n\nIf this isn't correct, restart this process and confirm your latitude and longitude data.");
            view.enableStartOffsetButton();
        } catch (IllegalStateException ISE) {
            view.displayPopUpMessage("Error getting sunrise data. Try again or manually input sunrise.");
        }
    }
}
