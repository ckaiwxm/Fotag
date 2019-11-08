package View.RightMenuView;

import javax.swing.*;
import java.awt.*;

public class FilterTextView extends JLabel {
    public FilterTextView() {
        this.setText("Filter by:");
        this.setFont(new Font("Arial", Font.PLAIN, 16));
        this.setForeground(Color.BLACK);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.CENTER);
        this.setBorder(BorderFactory.createEmptyBorder(0, 0,0,0));
    }
}
