package View;

import javax.swing.JFrame;
import java.awt.*;
import java.util.concurrent.TimeUnit;


public class BirdSoundViewImpl extends JFrame implements IBirdSoundView {
    private final int windowWidth = 800;
    private final int windowHeight = 600;
    private final int verticalGap= 5;
    private final int horizontalGap = 5;
    private final int actionBarHeight= 25;

    public BirdSoundViewImpl() {
        super();

        this.setTitle("Dawn Song PLayer");
        this.setSize(this.windowWidth,this.windowHeight);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout(this.verticalGap, this.horizontalGap));

        // add bird image
        BirdImage birdImage = new BirdImage();
        this.add(birdImage, BorderLayout.CENTER);

        // add status bar
        StatusBar statusBar = new StatusBar(this.windowWidth, this.actionBarHeight);
        this.add(statusBar, BorderLayout.PAGE_START);



//        this.pack();
        this.setVisible(true);

    }
}
