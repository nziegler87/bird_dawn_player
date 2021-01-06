package View;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;


public class BirdSoundViewImpl extends JFrame implements IBirdSoundView {
    private final int windowWidth = 800;
    private final int windowHeight = 600;
    private final int verticalGap= 5;
    private final int horizontalGap = 5;
    private final int actionBarHeight= 25;

    public BirdSoundViewImpl() {

        // basic settings
        super();
        this.setTitle("Dawn Song PLayer");
        this.setSize(this.windowWidth,this.windowHeight);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout(this.verticalGap, this.horizontalGap));

        // add status bar
        StatusBar statusBar = new StatusBar(this.windowWidth, this.actionBarHeight);
        this.add(statusBar, BorderLayout.PAGE_START);

        // add instructions
        InstructionPanel instructionPanel = new InstructionPanel(this.windowWidth / 3, this.windowHeight);
        this.add(instructionPanel, BorderLayout.LINE_START);

        // add control buttons
        ControlButtons controlButtons = new ControlButtons(this.windowWidth / 3, this.windowHeight);
        this.add(controlButtons, BorderLayout.CENTER);





        // add bird image
//        BirdImage birdImage = new BirdImage();
//        this.add(birdImage, BorderLayout.CENTER);




        // last steps
//        this.pack();
        this.setVisible(true);
    }
}
