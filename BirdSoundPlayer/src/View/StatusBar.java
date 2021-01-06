package View;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class StatusBar extends JPanel {
    JLabel statusLabel;
    String status = "This is the default status";

    public StatusBar(int width, int height) {
        super();

        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(new Color(173, 134, 255));
        this.setVisible(true);

        this.statusLabel = new JLabel(this.status);
        this.statusLabel.setPreferredSize(new Dimension(width, height));
        this.add(this.statusLabel, BorderLayout.CENTER);

    }

    public void setStatus(String string, Color color) {
        this.statusLabel.setText(string);
        this.setBackground(color);

    }

}
