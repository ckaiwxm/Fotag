package View.LeftMenuView;

import javax.swing.*;
import java.awt.*;

public class GridButtonView extends JButton{
    public ImageIcon gridImgIconActive;
    public ImageIcon gridImgIconInactive;

    public GridButtonView () {
        // Set icon images
        gridImgIconActive = getImageIcon("/lib/grid_active.png", 50, 45);
        gridImgIconInactive = getImageIcon("/lib/grid_inactive.png", 50, 45);
        this.setIcon(gridImgIconActive);
        this.setDisabledIcon(gridImgIconInactive);

        // Set view configs
        this.setEnabled(true);
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setBorder(BorderFactory.createEmptyBorder(0, 10,0,10));
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
