package View.RightMenuView;

import Model.ImageListModel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class AddButtonView extends JButton {
    public ImageListModel model;

    public AddButtonView(ImageListModel model) {
        this.model = model;

        // Set actions
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                JFileChooser fc = new JFileChooser();
                fc.addChoosableFileFilter(new FileNameExtensionFilter("Image Files",
                        "jpg", "png", "gif"));
                fc.setAcceptAllFileFilterUsed(false);
                fc.setMultiSelectionEnabled(true);
                fc.setCurrentDirectory(new File("./"));
                int chosen = fc.showOpenDialog(null);
                if (chosen == JFileChooser.APPROVE_OPTION) {
                    File[] files = fc.getSelectedFiles();
                    try {
                        model.importImages(files);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        // Set icon images
        this.setIcon(getImageIcon("/lib/add.png", 37, 37));

        // Set view configs
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setBorder(BorderFactory.createEmptyBorder(0, 0,0,8));
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
