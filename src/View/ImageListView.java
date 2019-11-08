package View;

import Model.DisplayOptions;
import Model.ImageListModel;
import Model.ImageModel;
import View.UtilWidget.SmartLayout;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ImageListView extends JPanel{
    public ImageListModel model;
    public ArrayList<ImageView> imageViewList;

    public ImageListView(ImageListModel model) {
        this.model = model;
        imageViewList = new ArrayList<ImageView>();

        // Set actions
        this.model.addView(new IView() {
            public void updateView(String action) {
                removeAll();
                imageViewList.clear();
                imageViewList = new ArrayList<ImageView>();
                ArrayList<ImageModel> shownImageList = model.getShownImageList();
                setImageViewConfig();
                revalidate();
                repaint();
            }
        });

        // Set view configs
        setImageViewConfig();
    }

    void setImageViewConfig() {
        if (model.getDisplayOption() == DisplayOptions.GRID) {
            this.setLayout(new SmartLayout(FlowLayout.LEFT));
            for (ImageModel imageModel : model.getShownImageList()) {
                ImageView imageView = new ImageView(imageModel, model.getDisplayOption());
                imageView.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
                this.imageViewList.add(imageView);
                this.add(imageView);
            }
        } else {
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            for (ImageModel imageModel : model.getShownImageList()) {
                ImageView imageView = new ImageView(imageModel, model.getDisplayOption());
                imageView.setBorder(BorderFactory.createEmptyBorder(20, 20,20,20));
                this.imageViewList.add(imageView);
                this.add(imageView);
            }
        }
    }
}
