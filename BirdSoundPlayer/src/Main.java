import Controller.BirdSoundControllerImpl;
import Controller.IBirdSoundController;
import Model.BirdSoundModelImpl;
import Model.IBirdSoundModel;
import View.IBirdSoundView;
import View.BirdSoundViewImpl;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URISyntaxException;



public class Main {

    public static void main(String [] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException, URISyntaxException, InterruptedException {
//        IBirdSoundView view = new BirdSoundViewImpl();
        IBirdSoundModel model = new BirdSoundModelImpl();
        IBirdSoundController controller = new BirdSoundControllerImpl(model);
        controller.go();
    }
}
