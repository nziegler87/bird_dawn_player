package Controller.Commands;

import Model.IBirdSoundModel;
import View.IBirdSoundView;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
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

        ZonedDateTime sunrise = model.getSunrise();
        long offset = model.getStartOffset();
        ZonedDateTime startTime = sunrise.minusMinutes(offset);
        ZonedDateTime endTime = startTime.plusMinutes(model.getDuration());

        Timer playSoundTimer = new Timer();
        TimerTask startSoundTask = new TimerTask() {
            @Override
            public void run() {
                model.play();
            }
        };
        playSoundTimer.schedule(startSoundTask, Date.from(startTime.toInstant()));

        Timer stopSoundTimer = new Timer();
        TimerTask stopSoundTask = new TimerTask() {
            @Override
            public void run() {
                playSoundTimer.cancel();
                playSoundTimer.purge();
                model.stop();
                stopSoundTimer.cancel();
                stopSoundTimer.purge();
            }
        };
        
        stopSoundTimer.schedule(stopSoundTask, Date.from(endTime.toInstant()));
        System.out.println("Did we get here?");
    }
}
