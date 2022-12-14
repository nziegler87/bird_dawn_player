package Model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BirdSoundModelImplTest {

    @Test
    public void testGetSunrise() {
        IBirdSoundModel model = new BirdSoundModelImpl();
        Assertions.assertThrows(IllegalStateException.class, () ->{model.automaticallySetSunrise(0,0);});
    }

    @Test
    public void testCheckDuration() {
        IBirdSoundModel model = new BirdSoundModelImpl();
        model.setSoundDuration(2, 62);

    }

}