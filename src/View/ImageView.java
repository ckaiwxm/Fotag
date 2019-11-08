package View;

import Model.DisplayOptions;
import Model.ImageModel;
import View.UtilWidget.StarListView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ImageView extends JPanel{
    public ImageModel imageModel;
    public DisplayOptions displayOption;
    public int tbnlWidth;
    public int tbnlHeight;
    public int elgdWidth;
    public int elgdHeight;
    public int infoGridWidth;
    public int infoGridHeight;
    public int infoListWidth;
    public int infoListHeight;
    public JPanel thumbnail;
    public JLabel tbnlLabel;
    public JFrame elgdFrame;
    public JLabel elgdLabel;
    public JPanel elgdInfo;
    public StarListView elgdRate;
    public JPanel info;
    public JLabel infoName;
    public JLabel infoDate;
    public StarListView infoRate;

    public ImageView(ImageModel imageModel, DisplayOptions displayOption){
        initModel(imageModel, displayOption);
        initTbnl();

        if (displayOption == DisplayOptions.GRID) {
            initInfoGrid();
        } else {
            initInfoList();
        }

        regViewer();

        if (displayOption == DisplayOptions.GRID) {
            initViewConfigsGrid();
        } else {
            initViewConfigsList();
        }
    }

    private void initModel(ImageModel imageModel, DisplayOptions displayOption) {
        this.imageModel = imageModel;
        this.displayOption = displayOption;
        this.tbnlWidth = 280;
        this.tbnlHeight = 200;
        this.elgdWidth = 800;
        this.elgdHeight = 500;
        this.infoListWidth = 160;
        this.infoListHeight = 95;
        this.infoGridWidth = 280;
        this.infoGridHeight = 49;
    }

    private void initTbnl() {
        // Init
        thumbnail = new JPanel(new GridBagLayout());
        tbnlLabel = new JLabel(getSmallImageIcon(imageModel.getPath()));
        elgdFrame = new JFrame(imageModel.getName());
        elgdLabel = new JLabel(getLargeImageIcon(imageModel.getPath()));
        elgdInfo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        elgdRate = new StarListView(null, imageModel, 25);

        // Set view configs
        //thumbnail.setBackground(new Color(134,43,221));
        thumbnail.setPreferredSize(new Dimension(tbnlWidth, tbnlHeight));
        thumbnail.setMaximumSize(new Dimension(tbnlWidth, tbnlHeight));
        thumbnail.setMinimumSize(new Dimension(tbnlWidth, tbnlHeight));
        thumbnail.add(tbnlLabel);
        elgdFrame.add(elgdLabel, BorderLayout.CENTER);
        elgdInfo.add(elgdRate);
        elgdInfo.setBorder(BorderFactory.createEmptyBorder(20, 0,20,0));
        elgdFrame.add(elgdInfo, BorderLayout.SOUTH);
        elgdFrame.setSize(new Dimension(800, 600));
        elgdFrame.setResizable(false);
        elgdFrame.setLocationRelativeTo(null);

        // Set actions
        this.thumbnail.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);

                elgdFrame.setVisible(true);
            }
        });

        this.elgdFrame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);

                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    elgdFrame.setVisible(false);
                }
            }
        });
    }

    private void initInfoGrid() {
        info = new JPanel();
        infoName = new JLabel(imageModel.getName());
        infoDate = new JLabel(imageModel.getDate());
        infoRate = new StarListView(null, imageModel, 25);

        // Set view configs
        infoName.setFont(new Font("Arial", Font.PLAIN, 16));
        infoName.setForeground(Color.BLACK);
        infoName.setBorder(BorderFactory.createEmptyBorder(0, 0,5,0));
        infoDate.setFont(new Font("Arial", Font.PLAIN, 16));
        infoDate.setForeground(Color.BLACK);
        infoDate.setBorder(BorderFactory.createEmptyBorder(0, 0,0,15));
        JPanel drPanel = new JPanel();
        drPanel.setLayout(new BoxLayout(drPanel, BoxLayout.X_AXIS));
        drPanel.add(infoDate);
        drPanel.add(infoRate);

        info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
        //info.setBackground(new Color(192,14,221));
        info.setPreferredSize(new Dimension(infoGridWidth, infoGridHeight));
        info.setMaximumSize(new Dimension(infoGridWidth, infoGridHeight));
        info.setMinimumSize(new Dimension(infoGridWidth, infoGridHeight));
        infoName.setAlignmentX(Component.LEFT_ALIGNMENT);
        drPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        info.add(infoName);
        info.add(drPanel);

        thumbnail.setAlignmentX(Component.CENTER_ALIGNMENT);
        info.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    private void initInfoList() {
        info = new JPanel();
        infoName = new JLabel(imageModel.getName());
        infoDate = new JLabel(imageModel.getDate());
        infoRate = new StarListView(null, imageModel, 25);

        // Set view configs
        infoName.setFont(new Font("Arial", Font.PLAIN, 16));
        infoName.setForeground(Color.BLACK);
        infoName.setBorder(BorderFactory.createEmptyBorder(0, 0,12,0));
        infoDate.setFont(new Font("Arial", Font.PLAIN, 16));
        infoDate.setForeground(Color.BLACK);
        infoDate.setBorder(BorderFactory.createEmptyBorder(0, 0,12,0));

        info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
        //info.setBackground(new Color(132,124,221));
        info.setPreferredSize(new Dimension(infoListWidth, infoListHeight));
        info.setMaximumSize(new Dimension(infoListWidth, infoListHeight));
        info.setMinimumSize(new Dimension(infoListWidth, infoListHeight));
        infoName.setAlignmentX(Component.LEFT_ALIGNMENT);
        infoDate.setAlignmentX(Component.LEFT_ALIGNMENT);
        infoRate.setAlignmentX(Component.LEFT_ALIGNMENT);

        info.add(infoName);
        info.add(infoDate);
        info.add(infoRate);

        thumbnail.setAlignmentY(Component.CENTER_ALIGNMENT);
        info.setAlignmentY(Component.CENTER_ALIGNMENT);
    }

    private void regViewer() {
        this.imageModel.addView(new IView() {
            public void updateView(String action) {
                elgdInfo.remove(elgdRate);
                elgdRate = new StarListView(null, imageModel, 25);
                elgdInfo.add(elgdRate);

                remove(info);
                if(displayOption == DisplayOptions.GRID) {
                    initInfoGrid();
                    add(info);
                } else {
                    initInfoList();
                    add(info);
                }

                elgdInfo.revalidate();
                elgdInfo.repaint();
                info.revalidate();
                info.repaint();
            }
        });
    }

    private void initViewConfigsGrid() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(thumbnail);
        this.add(Box.createRigidArea(new Dimension(0,10)));
        this.add(info);
    }

    private void initViewConfigsList() {
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.add(thumbnail);
        this.add(Box.createRigidArea(new Dimension(90,0)));
        this.add(info);
    }

    private ImageIcon getSmallImageIcon(String path) {
        Dimension origDim = new Dimension(imageModel.getWidth(), imageModel.getHeight());
        Dimension smallDim = new Dimension(tbnlWidth, tbnlHeight);
        Dimension retDim = getRatioDimension(origDim, smallDim);
        return getImageIcon(path, retDim.width, retDim.height);
    }

    private ImageIcon getLargeImageIcon(String path) {
        Dimension origDim = new Dimension(imageModel.getWidth(), imageModel.getHeight());
        Dimension largeDim = new Dimension(elgdWidth, elgdHeight);
        Dimension retDim = getRatioDimension(origDim, largeDim);
        return getImageIcon(path, retDim.width, retDim.height);
    }

    // According to https://stackoverflow.com/questions/12691832/how-to-put-an-image-on-a-jbutton
    private ImageIcon getImageIcon(String path, int width, int height) {
        ImageIcon imgIcon = new ImageIcon(new ImageIcon(path)
                .getImage()
                .getScaledInstance(width, height, Image.SCALE_SMOOTH));
        return imgIcon;
    }

    private Dimension getRatioDimension(Dimension origSize, Dimension newSize) {

        int origWidth = origSize.width;
        int origHeight = origSize.height;
        int newWidth = newSize.width;
        int newHeight = newSize.height;
        int retWidth = origWidth;
        int retHeight = origHeight;

        if (origWidth > newWidth) {
            retWidth = newWidth;
            double ratio = (double)origHeight / (double)origWidth;
            retHeight = (int)Math.round((double)retWidth * ratio);
        }

        if (retHeight > newHeight) {
            retHeight = newHeight;
            double ratio = (double)origWidth / (double)origHeight;
            retWidth = (int)Math.round((double)retHeight * ratio);
        }

        return new Dimension(retWidth, retHeight);
    }
}
