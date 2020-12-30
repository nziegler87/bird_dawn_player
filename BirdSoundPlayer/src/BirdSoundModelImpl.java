import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class BirdSoundModelImpl implements IBirdSoundModel {

    // to store current position
    private long currentFrame;
    private Clip clip;

    // current status of the clip
    private String status;

    AudioInputStream audioInputStream;
//    String filePath;

    public void BirdSoundModelImpl() throws IllegalStateException {

        public void loadFile(String filePath){
            this.filePath = userFilePath;
            // create AudioInputStream object from file
            File file = new File(filePath).getAbsoluteFile();
            try {
                this.audioInputStream = AudioSystem.getAudioInputStream(file);
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
                throw new IllegalStateException("Unsupported audio file.");
            } catch (IOException e) {
                e.printStackTrace();
                throw new IllegalStateException("Problem loading audio file.");
            }

            // create clip reference
            try {
                this.clip = AudioSystem.getClip();
            } catch (LineUnavailableException LUE){
                throw new IllegalStateException("Problem generating audio clip from file.");
            }

            // open audioInputStream to the clip
            try {
                this.clip.open(audioInputStream);
            } catch (LineUnavailableException | IOException e) {
                e.printStackTrace();
                throw new IllegalStateException("Problem generating audio input stream");
            }
        }



//        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }






    @Override
    public void setStartTime(double time) {

    }

    @Override
    public void setEndTime(double time) {

    }
}
