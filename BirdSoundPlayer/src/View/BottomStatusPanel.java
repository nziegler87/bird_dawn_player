package View;

import javax.swing.*;
import java.awt.*;

public class BottomStatusPanel extends JPanel {
    JLabel latitudeLabel, latitude, longitudeLabel, longitude, sunriseLabel, sunrise, offsetLabel, offset,
            durationLabel, duration,  autoSunsetLabel, autoSunset;
    String na = "N/A";

    public BottomStatusPanel(){
        super();
        this.setLayout(new FlowLayout());

        // font for labels
        Font labelFont = new Font("Calibri", Font.BOLD,12);

        // font for text
        Font textFont = new Font("Calibri", Font.PLAIN,12);

        // add latitude
        this.latitudeLabel = new JLabel("Latitude: ");
        this.latitudeLabel.setFont(labelFont);
        this.add(this.latitudeLabel);
        this.latitude = new JLabel(na);
        this.latitude.setFont(textFont);
        this.add(this.latitude);

        // add longitude
        this.longitudeLabel = new JLabel("Longitude: ");
        this.longitudeLabel.setFont(labelFont);
        this.add(this.longitudeLabel);
        this.longitude = new JLabel(na);
        this.longitude.setFont(textFont);
        this.add(this.longitude);

        // add divider
        JLabel divider = new JLabel("|");
        divider.setFont(labelFont);
        this.add(divider);

        // add sunrise
        this.sunriseLabel = new JLabel("Sunrise: ");
        this.sunriseLabel.setFont(labelFont);
        this.add(this.sunriseLabel);
        this.sunrise = new JLabel(na);
        this.sunrise.setFont(textFont);
        this.add(this.sunrise);

        // add divider
        JLabel divider2 = new JLabel("|");
        divider2.setFont(labelFont);
        this.add(divider2);

        // add auto sunset
        this.autoSunsetLabel = new JLabel("Auto Sunset Update: ");
        this.autoSunsetLabel.setFont(labelFont);
        this.add(this.autoSunsetLabel);

        this.autoSunset = new JLabel("False");
        this.autoSunset.setFont(textFont);
        this.add(this.autoSunset);

        // add divider
        JLabel divider3 = new JLabel("|");
        divider3.setFont(labelFont);
        this.add(divider3);

        // add sound offset and duration
        this.offsetLabel = new JLabel("Sound Offset: ");
        this.offsetLabel.setFont(labelFont);
        this.add(this.offsetLabel);
        this.offset = new JLabel(na);
        this.offset.setFont(textFont);
        this.add(this.offset);

        this.durationLabel = new JLabel("Sound Duration: ");
        this.durationLabel.setFont(labelFont);
        this.add(this.durationLabel);
        this.duration = new JLabel(na);
        this.duration.setFont(textFont);
        this.add(this.duration);
    }

}
