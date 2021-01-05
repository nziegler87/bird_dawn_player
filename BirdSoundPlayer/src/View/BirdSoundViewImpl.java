package View;

import javax.swing.JFrame;
import java.awt.*;


public class BirdSoundViewImpl extends JFrame implements IBirdSoundView {

    public BirdSoundViewImpl() {
        super();

        this.setTitle("Dawn Song PLayer");
        this.setSize(800,800);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        BirdImage birdImage = new BirdImage();
        this.add(birdImage, BorderLayout.CENTER);



        this.pack();
        this.setVisible(true);

    }
}
