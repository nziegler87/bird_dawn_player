package Controller.Commands;

import Model.IBirdSoundModel;
import View.IBirdSoundView;

import java.awt.*;

public class LoadFile implements ICommand {
    
    @Override
    public void go(IBirdSoundView view, IBirdSoundModel model) {
        // get file name using JLoadFile
        String audioFileName = view.selectAudioFile();

        // as long as the user provided a valid file type, load the file
        if (audioFileName != null) {
            view.updateStatusMessage("Audio file loaded. Test your file using the audio controls. " +
                            "Then, automatically set sunrise or manually set sunrise.", new Color(34, 72, 163),
                    new Color(255, 255, 255));
            model.loadFile(audioFileName);
            view.enablePlayControls();
            view.enableSunriseButtons();
        }
//        model.checkReady();  //TODO: Uncomment out?
    }
}
