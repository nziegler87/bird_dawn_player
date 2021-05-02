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
        view.updateStatusMessage("Sound will begin playing at " + model.returnLocalTimeOfSunrise(ZoneId.systemDefault()), new Color(30, 7, 139), new Color(0,0,0));


        ZonedDateTime sunrise = model.getSunrise();
        long offset = model.getStartOffset();
        ZonedDateTime startTime = sunrise.minusMinutes(offset);
        ZonedDateTime endTime = startTime.plusMinutes(model.getDuration());
        view.disablePlayControls();

        Timer playSoundTimer = new Timer();
        ZonedDateTime finalEndTime = endTime;
        TimerTask startSoundTask = new TimerTask() {
            @Override
            public void run() {
                model.play();
                String DATE_FORMAT = "hh:mm a";
                DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_FORMAT);
                view.updateStatusMessage("Bird sound is playing! It will stop playing at " + format.format(finalEndTime), new Color(39, 154, 0), new Color(0,0,0));
            }
        };

        playSoundTimer.schedule(startSoundTask, Date.from(startTime.toInstant()));



        Timer stopSoundTimer = new Timer();
        TimerTask stopSoundTask = new TimerTask() {
            @Override
            public void run() {
                model.stop();
                if ( model.isAutoSunrise() ){
                    model.automaticallySetSunrise(model.getLatitude(), model.getLongitude());
                    view.updateStatusMessage("Bird sound has finished playing for the day. Sunrise was be auto updated and sound will begin playing tomorrow at " +
                            model.returnLocalTimeOfSunrise(ZoneId.systemDefault()), new Color(30, 7, 139), new Color(0,0,0));
                } else {
                    view.updateStatusMessage("Bird sound has finished playing for the day. Sound will begin playing tomorrow at " +
                            model.returnLocalTimeOfSunrise(ZoneId.systemDefault()) + ". Don't forget to update the sunrise data.", new Color(30, 7, 139), new Color(0,0,0));
                }
            }
        };
        
        stopSoundTimer.schedule(stopSoundTask, Date.from(endTime.toInstant()));


        System.out.println("Did we get here?");
    }
}
