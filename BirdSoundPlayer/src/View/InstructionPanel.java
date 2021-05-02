package View;

import javax.swing.*;
import java.awt.*;

public class InstructionPanel extends JPanel {

    public InstructionPanel(int width, int height) {
        super();
        this.setSize(width, height);
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(boxLayout);

        JLabel stepOne = new JLabel("\t\t1. Load the desired audio file.");
        JLabel stepOneNote = new JLabel("\t\t\t\tTest the audio file using the play, pause, and stop buttons.");
        stepOneNote.setFont(new Font("Calibri", Font.ITALIC, 12));
        JLabel stepTwo = new JLabel("\t\t2. Set the audio file using the control buttons.");
        JLabel stepThree= new JLabel("\t\t3. Set the sunrise time, either manually or automatically.");
        JLabel stepThreeNote = new JLabel("\t\t\t\tNote that if you manually set the sunrise, it will not automatically update each day.");
        stepThreeNote.setFont(new Font("Calibri", Font.ITALIC, 12));
        JLabel stepFour = new JLabel("\t\t4. Set how early before don the sound should play.");
        JLabel stepFive = new JLabel("\t\t5. Set how long the sound will play.");
        JLabel stepSix = new JLabel("\t\t6. Hit start!");
        JSeparator separator = new JSeparator();

        JLabel portRoyalInfo = new JLabel("Port Royal Info:\nLatitude: 40.53 | Longitude: -77.39");
        JLabel bostonInfo = new JLabel("Boston Info:\nLatitude: 42.36 | Longitude: -71.06");

        this.add(stepOne);
        this.add(stepOneNote);
        this.add(stepTwo);
        this.add(stepThree);
        this.add(stepThreeNote);
        this.add(stepFour);
        this.add(stepThree);
        this.add(stepFour);
        this.add(stepFive);
        this.add(stepSix);
        this.add(separator);
        this.add(portRoyalInfo);
        this.add(bostonInfo);

        this.setVisible(true);

    }
}
