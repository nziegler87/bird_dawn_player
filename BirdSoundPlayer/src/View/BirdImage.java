package View;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.Image;


public class BirdImage extends Canvas {

    public void paint(Graphics g) {
        super.paint(g);
        Toolkit t = Toolkit.getDefaultToolkit();
        Image i = t.getImage("/Users/nathanialziegler/Documents/GitHub/Personal-Projects/BirdSoundPlayer/Images/purple_martin2.jpg");
        int width = i.getWidth(null);
        int height = i.getHeight(null);
        g.drawImage(i, width / 4, height / 4, this);
    }
}

