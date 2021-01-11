package Controller.Commands;

import Model.IBirdSoundModel;

public class PlayCommand implements Runnable {
    IBirdSoundModel model;

    PlayCommand(IBirdSoundModel model) {
        this.model = model;
    }

    @Override
    public void run() {
        model.play();
        }
    }

