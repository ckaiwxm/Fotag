package View.LeftMenuView;

import javax.swing.*;
import java.awt.*;

public class ListButtonView extends JButton{
    public ImageIcon listImgIconActive;
    public ImageIcon listImgIconInactive;

    public ListButtonView () {
        // Set icon images
        listImgIconActive = getImageIcon("/lib/list_active.png", 45, 45);
        listImgIconInactive = getImageIcon("/lib/list_inactive.png", 45, 45);
        this.setIcon(listImgIconActive);
        this.setDisabledIcon(listImgIconInactive);

        // Set view configs
        this.setEnabled(false);
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setBorder(BorderFactory.createEmptyBorder(0, 0,0,0));
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
