package View.UtilWidget;

import Model.IRateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClearButtonView extends JButton{
    public IRateModel rateModel;

    public ClearButtonView(IRateModel rateModel, int size) {
        // Set Model
        this.rateModel = rateModel;

        // Set actions
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                rateModel.setRate(0);
            }
        });

        // Set icon images
        this.setIcon(getImageIcon("/lib/clear.png", size, size));

        // Set view configs
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
