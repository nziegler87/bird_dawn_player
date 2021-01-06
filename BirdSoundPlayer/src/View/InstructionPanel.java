package View;

import javax.swing.*;

public class InstructionPanel extends JPanel {

    public InstructionPanel(int width, int height) {
        super();
        this.setSize(width, height);
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(boxLayout);

        JLabel stepOne = new JLabel("\t\t1. Load the desire audio file.");
        JLabel stepTwo = new JLabel("\t\t2. Set the sunrise time, either manually or automatically.");
        JLabel stepThree = new JLabel("\t\t3. Set how long the sound will play.");
        JLabel stepFour = new JLabel("\t\t4. Hit start!");

        this.add(stepOne);
        this.add(stepTwo);
        this.add(stepThree);
        this.add(stepFour);

        this.setVisible(true);

    }
}
