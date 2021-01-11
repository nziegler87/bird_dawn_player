package Controller.Commands;

import Model.IBirdSoundModel;
import View.IBirdSoundView;

public interface ICommand {

    void go(IBirdSoundView view, IBirdSoundModel model);
}
