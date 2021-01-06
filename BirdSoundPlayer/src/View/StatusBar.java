package View;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class StatusBar extends JPanel {
    JLabel statusLabel;
    String status = "All looks good.";

    public StatusBar(int width, int height) {
        super();

        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(new Color(42, 128, 0));
        this.setVisible(true);

        this.statusLabel = new JLabel(this.status);
        this.statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.statusLabel.setVerticalAlignment(SwingConstants.VERTICAL);
        this.statusLabel.setPreferredSize(new Dimension(width, height));
        this.add(this.statusLabel, BorderLayout.CENTER);

    }

    public void setStatus(String string, Color color) {
        this.statusLabel.setText(string);
        this.setBackground(color);

    }

}
