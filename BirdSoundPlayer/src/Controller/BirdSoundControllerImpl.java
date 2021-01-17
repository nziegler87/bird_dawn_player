package Controller;

import Controller.Commands.*;
import Model.IBirdSoundModel;
import View.IBirdSoundView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;

public class BirdSoundControllerImpl implements IBirdSoundController, ActionListener {
    private IBirdSoundModel model;
    private IBirdSoundView view;
    private Map<String, Function<ActionEvent, ICommand>> knownCommands;

    public BirdSoundControllerImpl(IBirdSoundModel model, IBirdSoundView view) {

        // set up commands
        knownCommands = new HashMap<>();
        knownCommands.put("load file", eventEvent -> new LoadFile());
        knownCommands.put("automatically set sunrise", eventEvent -> new AutomaticallySetSunrise());
        knownCommands.put("manually set sunrise", eventEvent -> new ManuallySetSunrise());
        knownCommands.put("set sound duration", actionEvent -> new SetSoundDuration());
        knownCommands.put("set start offset",   actionEvent -> new SetStartOffset());
        knownCommands.put("play sound", actionEvent -> new PlayAudio());
        knownCommands.put("pause sound", actionEvent -> new PauseAudio());
        knownCommands.put("stop sound", actionEvent -> new StopAudio());
        knownCommands.put("start", actionEvent -> new Start());
        knownCommands.put("stop", actionEvent -> new Stop());

        this.model = model;
        this.view = view;
        this.view.setListener(this);

    }

    @Override
    public void go() throws IllegalStateException {
    }

    private void checkReady() {
        if (this.model.readyForStart()) {
            this.view.enableGoButton();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        Function<ActionEvent, ICommand> cmd = knownCommands.getOrDefault(e.getActionCommand().toLowerCase(Locale.ROOT),null);

        if (cmd == null) {
            this.view.displayPopUpMessage("Unsupported Operation.");
        } else {
            ICommand c = cmd.apply(e);
            c.go(this.view, this.model);
        }
    }
}

