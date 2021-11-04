package Controller.Commands;

import java.awt.*;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.TimerTask;

import Model.IBirdSoundModel;
import View.IBirdSoundView;

public class StopSoundTask extends TimerTask {
    private IBirdSoundModel model;
    private IBirdSoundView view;

    StopSoundTask(IBirdSoundModel model, IBirdSoundView view) {
        this.model = model;
        this.view = view;
    }

    /**
     * The action to be performed by this timer task.
     */
    @Override
    public void run() {
        String DATE_FORMAT = "hh:mm a";
        DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_FORMAT);
        model.stop();
        if (model.isAutoSunrise()) {
            model.automaticallySetSunrise(model.getLatitude(), model.getLongitude());
            model.scheduleAudioPlaying();
            view.updateStatusMessage("Bird sound has finished playing for the day. Sunrise was be auto updated and sound will begin playing tomorrow at " +
                    format.format(model.getStartTime()), new Color(30, 7, 139), new Color(0, 0, 0));
        } else {
            view.updateStatusMessage("Bird sound has finished playing for the day. Sound will begin playing tomorrow at " +
                    model.returnLocalTimeOfSunrise(ZoneId.systemDefault()) + ". Don't forget to update the sunrise data.", new Color(30, 7, 139), new Color(0, 0, 0));
        }
        model.setSoundDone(true);
    }
}

