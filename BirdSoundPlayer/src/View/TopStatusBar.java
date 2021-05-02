package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

public class TopStatusBar extends JPanel {
    JLabel statusLabel;
    String status = "Hello! To start, select an audio file. Must be AIFC, AIFF, AU, SND or WAV format. ";

    public TopStatusBar(int width, int height) {
        super();

        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(new Color(42, 128, 0));
        this.setVisible(true);

        this.statusLabel = new JLabel(this.status);
        this.statusLabel.setForeground(new Color(255, 255, 255));
        this.statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.statusLabel.setVerticalAlignment(SwingConstants.VERTICAL);
        this.statusLabel.setPreferredSize(new Dimension(width, height));
        this.add(this.statusLabel, BorderLayout.CENTER);

    }

    public void setStatus(String string, Color bgColor, Color txtColor) {
        this.statusLabel.setText(string);
        this.setBackground(bgColor);

    }



}
