package Controller;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Interface that outlines all operations of the BirdSoundController.
 */
public interface IBirdSoundController {


    void go() throws UnsupportedAudioFileException, IOException, LineUnavailableException, URISyntaxException, InterruptedException;

//    void Play();
//
//    void Pause();
//
//    void Stop();
//
//    void Restart();
}
