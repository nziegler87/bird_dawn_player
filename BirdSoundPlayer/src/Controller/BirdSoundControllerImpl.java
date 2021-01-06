package Controller;



import Model.IBirdSoundModel;

public class BirdSoundControllerImpl implements IBirdSoundController {
    private IBirdSoundModel birdSoundModel;

    public BirdSoundControllerImpl(IBirdSoundModel birdSoundModel) {
        this.birdSoundModel = birdSoundModel;
    }

    @Override
    public void go() throws IllegalStateException {

        this.birdSoundModel.loadFile("./AudioClips/dawn_song.wav");
        this.birdSoundModel.automaticallySetSunrise(40.5334, -77.3855);
        System.out.println(this.birdSoundModel.printLocalTime("America/New_York"));
        this.birdSoundModel.play();
    }
}
