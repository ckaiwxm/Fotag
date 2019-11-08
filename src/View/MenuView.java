package View;

import Model.DisplayOptions;
import Model.ImageListModel;
import View.LeftMenuView.GridButtonView;
import View.LeftMenuView.ListButtonView;
import View.RightMenuView.RateFilterView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuView extends JPanel{
    public ImageListModel model;
    public GridButtonView gridButton;
    public ListButtonView listButton;
    public JLabel appLogo;
    public RateFilterView rateFilter;

    public MenuView(ImageListModel model) {
        InitModels(model);
        InitButtons();
        InitViewConfigs();
    }

    private void InitModels(ImageListModel model) {
        this.model = model;
    }

    private void InitButtons() {
        // Init display modes button
        gridButton = new GridButtonView();
        listButton = new ListButtonView();
        gridButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (model.getDisplayOption() == DisplayOptions.LIST) {
                    model.setDisplayOption(DisplayOptions.GRID);
                    gridButton.setEnabled(true);
                    listButton.setEnabled(false);
                }
            }
        });
        listButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (model.getDisplayOption() == DisplayOptions.GRID) {
                    model.setDisplayOption(DisplayOptions.LIST);
                    gridButton.setEnabled(false);
                    listButton.setEnabled(true);
                }
            }
        });

        // Init app logo
        appLogo = new JLabel(getImageIcon("/lib/logo.png", 135, 90));

        // init rate filter
        rateFilter = new RateFilterView(model);
    }

    private void InitViewConfigs() {
        this.setBackground(Color.WHITE);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        this.add(gridButton);
        this.add(listButton);
        this.add(Box.createRigidArea(new Dimension(15,0)));
        appLogo.setBorder(BorderFactory.createEmptyBorder(5, 0,12,0));
        this.add(appLogo);
        this.add(rateFilter);
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
