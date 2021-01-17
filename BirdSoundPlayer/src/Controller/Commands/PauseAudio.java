package Controller.Commands;

import Model.IBirdSoundModel;
import View.IBirdSoundView;

public class PauseAudio implements ICommand {
    @Override
    public void go(IBirdSoundView view, IBirdSoundModel model) {
        model.pause();
    }
}
