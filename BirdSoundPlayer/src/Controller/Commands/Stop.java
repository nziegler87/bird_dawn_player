package Controller.Commands;

import Model.IBirdSoundModel;
import View.IBirdSoundView;

public class Stop implements ICommand {
    @Override
    public void go(IBirdSoundView view, IBirdSoundModel model) {
        model.stop();
        view.disableStopButton();
        view.enableGoButton();
        view.enablePlayControls();
    }
}
