package Controller.Commands;

import Model.IBirdSoundModel;
import View.IBirdSoundView;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Start implements ICommand {
    @Override
    public void go(IBirdSoundView view, IBirdSoundModel model) {
        //http://tutorials.jenkov.com/java-util-concurrent/scheduledexecutorservice.html
        //https://www.tutorialspoint.com/schedule-a-task-for-execution-in-java

        view.disableGoButton();
        view.enableStopButton();
        model.stop();
        model.scheduleAudioPlaying();
        view.disablePlayControls();
        String DATE_FORMAT = "hh:mm a";
        DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_FORMAT);
        view.updateStatusMessage("Sound will begin playing at " + format.format(model.getStartTime()), new Color(30, 7, 139), new Color(0,0,0));


        Timer playSoundTimer = new Timer();
        TimerTask startSoundTask = new TimerTask() {
            @Override
            public void run() {
                model.play();
                view.updateStatusMessage("Bird sound is playing! It will stop playing at " + format.format(model.getEndTime()), new Color(39, 154, 0), new Color(0,0,0));
            }
        };


        Timer stopSoundTimer = new Timer();
        TimerTask stopSoundTask = new TimerTask() {
            @Override
            public void run() {
                model.stop();
                if ( model.isAutoSunrise() ){
                    model.automaticallySetSunrise(model.getLatitude(), model.getLongitude());
                    model.scheduleAudioPlaying();
                    playSoundTimer.schedule(startSoundTask, Date.from(model.getStartTime().toInstant()));
                    stopSoundTimer.schedule(this, Date.from(model.getEndTime().toInstant()));
                    view.updateStatusMessage("Bird sound has finished playing for the day. Sunrise was be auto updated and sound will begin playing tomorrow at " +
                            format.format(model.getStartTime()), new Color(30, 7, 139), new Color(0,0,0));
                } else {
                    view.updateStatusMessage("Bird sound has finished playing for the day. Sound will begin playing tomorrow at " +
                            model.returnLocalTimeOfSunrise(ZoneId.systemDefault()) + ". Don't forget to update the sunrise data.", new Color(30, 7, 139), new Color(0,0,0));
                }
            }
        };

        playSoundTimer.schedule(startSoundTask, Date.from(model.getStartTime().toInstant()));
        stopSoundTimer.schedule(stopSoundTask, Date.from(model.getEndTime().toInstant()));

//
//
//        System.out.println("Did we get here?");
    }
}
