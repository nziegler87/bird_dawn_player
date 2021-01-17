package Controller.Commands;

import Model.IBirdSoundModel;
import View.IBirdSoundView;

public class Start implements ICommand {
    @Override
    public void go(IBirdSoundView view, IBirdSoundModel model) {
        //http://tutorials.jenkov.com/java-util-concurrent/scheduledexecutorservice.html
        view.disableGoButton();
        view.enableStopButton();
    }
}
