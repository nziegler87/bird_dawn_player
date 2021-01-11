package View;

import java.awt.*;
import java.awt.event.ActionListener;

public interface IBirdSoundView {

    void setListener(ActionListener listener);

    void toggleGoButton();

    void enablePlayControls();

    void disablePlayControls();

    String selectAudioFile();

    void updateStatusMessage(String message, Color bgColor, Color fgColor);

    Double getDoubleInput(String message);

    Integer getIntegerInput(String message);

    void displayPopUpMessage(String message);

    }
