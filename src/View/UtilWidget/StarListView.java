package View.UtilWidget;

import Model.IRateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class StarListView extends JPanel {
    public ArrayList<StarView> stars;
    public ClearButtonView clear;
    public IRateModel rateModel;

    public StarListView(Color bgColor, IRateModel rateModel, int size) {
        // Init models
        this.rateModel = rateModel;

        // Init comps
        this.stars = new ArrayList<StarView>();
        this.clear = new ClearButtonView(rateModel, size - 2);
        for(int i = 0; i < 5; i++) {
            StarView star = new StarView(bgColor, size);
            this.stars.add(star);
        }

        // Set actions
        for (int i = 0; i < 5; i++) {
            StarView star = stars.get(i);
            star.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (!rateModel.isModelEmpty()) {
                        super.mousePressed(e);
                        StarView pressedStar = (StarView) e.getSource();
                        int index = stars.indexOf(pressedStar);

                        if (rateModel.getRate() != (index + 1)) {
                            rateModel.setRate(index + 1);

                            for (int j = 0; j <= index; j++) {
                                stars.get(j).setEnabled(true);
                            }

                            for (int k = index + 1; k < 5; k++) {
                                stars.get(k).setEnabled(false);
                            }
                        }
                    }
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    if (!rateModel.isModelEmpty()) {
                        super.mouseEntered(e);
                        StarView overStar = (StarView) e.getSource();
                        int index = stars.indexOf(overStar);

                        for (int j = 0; j <= index; j++) {
                            stars.get(j).setEnabled(true);
                        }

                        for (int k = index + 1; k < 5; k++) {
                            stars.get(k).setEnabled(false);
                        }
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (!rateModel.isModelEmpty()) {
                        super.mouseExited(e);
                        int index = rateModel.getRate() - 1;

                        for (int j = 0; j <= index; j++) {
                            stars.get(j).setEnabled(true);
                        }

                        for (int k = index + 1; k < 5; k++) {
                            stars.get(k).setEnabled(false);
                        }
                    }
                }
            });
        }

        // Set view configs
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        for (int j = 0; j < rateModel.getRate(); j++) {
            stars.get(j).setEnabled(true);
        }
        for (int k = rateModel.getRate(); k < 5; k++) {
            stars.get(k).setEnabled(false);
        }

        for(int i = 0; i < 5; i++) {
            this.add(stars.get(i));
            this.add(Box.createRigidArea(new Dimension(1, 0)));
        }
        this.add(clear);
        this.setBackground(bgColor);
        this.setBorder(BorderFactory.createEmptyBorder(0, 0,0,0));
    }
}
