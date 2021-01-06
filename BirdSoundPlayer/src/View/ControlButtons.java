package View;

import javax.swing.*;

public class ControlButtons extends JPanel {

    public ControlButtons(int width, int height) {


        JButton loadFile = new JButton("Load file");
        JButton automaticallySetSunrise = new JButton("Automatically set sunrise");
        JButton manuallySetSunrise = new JButton("Manually set sunrise");
        JButton setSoundDuration = new JButton("Set sound duration");

        this.add(loadFile);
        this.add(automaticallySetSunrise);
        this.add(manuallySetSunrise);
        this.add(setSoundDuration);

        this.setVisible(true);
    }
}
