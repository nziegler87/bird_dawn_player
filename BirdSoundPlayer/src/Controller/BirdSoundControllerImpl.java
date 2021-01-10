package Controller;


import Model.IBirdSoundModel;
import View.IBirdSoundView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BirdSoundControllerImpl implements IBirdSoundController, ActionListener {
    private IBirdSoundModel model;
    private IBirdSoundView view;

    public BirdSoundControllerImpl(IBirdSoundModel model, IBirdSoundView view) {
        this.model = model;
        this.view = view;
        this.view.setListener(this);

    }

    @Override
    public void go() throws IllegalStateException {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        if (e.getActionCommand().equals("Load file")){
            this.view.togglePlayControls();
        }
    }
}
