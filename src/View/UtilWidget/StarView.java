package View.UtilWidget;

import javax.swing.*;
import java.awt.*;

public class StarView extends JLabel {
    public ImageIcon starImgIconActive;
    public ImageIcon starImgIconInactive;

    public StarView (Color bgColor, int size) {
        // Set icon images
        starImgIconActive = getImageIcon("/lib/star_active.png", size, size);
        starImgIconInactive = getImageIcon("/lib/star_inactive.png", size, size);
        this.setIcon(starImgIconActive);
        this.setDisabledIcon(starImgIconInactive);
        this.setBorder(BorderFactory.createEmptyBorder(0, 0,0,0));

        // Set view configs
        this.setEnabled(false);
        if (bgColor != null) {
            this.setBackground(bgColor);
        } else {
            this.setOpaque(true);
        }
    }

    // According to https://stackoverflow.com/questions/12691832/how-to-put-an-image-on-a-jbutton
    private ImageIcon getImageIcon(String path, int width, int height) {
        ImageIcon imgIcon = new ImageIcon(new ImageIcon(getClass()
                .getResource(path))
                .getImage()
                .getScaledInstance(width, height, Image.SCALE_SMOOTH));
        return imgIcon;
    }
}
