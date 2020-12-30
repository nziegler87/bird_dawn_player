import java.util.Scanner;

public class BirdSoundControllerImpl implements IBirdSoundController {
    private final IBirdSoundModel birdSoundModel;

    public void BirdSoundControllerImpl(IBirdSoundModel birdSoundModel) {
        birdSoundModel = birdSoundModel;

        Scanner sc = new Scanner(System.in);
        soundFile = System.out.println("Enter the path of your file: ");

    }
}
