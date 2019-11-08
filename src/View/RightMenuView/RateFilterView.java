package View.RightMenuView;

import Model.ImageListModel;
import View.IView;
import View.UtilWidget.StarListView;

import javax.swing.*;
import java.awt.*;

public class RateFilterView extends JPanel {
    public ImageListModel model;
    public AddButtonView addButton;
    public TrashButtonView trashButton;
    public FilterTextView filterText;
    public StarListView rateList;

    public RateFilterView(ImageListModel model) {
        initModels(model);
        initButtons();
        regViewer();
        initViewConfigs();
    }

    private void initModels(ImageListModel model) {
        this.model = model;
    }

    private void initButtons() {
        // Init add button
        addButton = new AddButtonView(model);

        // Init trash button
        trashButton = new TrashButtonView(model);

        // Init filter text
        filterText = new FilterTextView();

        // Init stars
        rateList = new StarListView(Color.WHITE, model, 25);
    }

    private void regViewer() {
        this.model.addView(new IView() {
            public void updateView(String action) {
                remove(rateList);
                rateList = new StarListView(Color.WHITE, model, 25);
                add(rateList);
                revalidate();
                repaint();
            }
        });
    }

    private void initViewConfigs() {
        this.setBackground(Color.WHITE);
        this.setLayout(new FlowLayout(FlowLayout.RIGHT));
        this.add(addButton);
        this.add(trashButton);
        this.add(filterText);
        this.add(rateList);
        this.setBorder(BorderFactory.createEmptyBorder(26, 0,0,20));
    }
}
