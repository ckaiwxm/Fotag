import Model.ImageListModel;
import Model.ImageModel;
import View.ImageListView;
import View.MenuView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        new FotagFrame();
    }
}

class FotagFrame extends JFrame {
    private ImageListModel model;
    private MenuView menu;
    private JScrollPane scrollPane;
    public FotagFrame() {
        super("Fotag!");

        // Init Model
        model = new ImageListModel();

        // Set actions
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                super.windowOpened(e);
                File cache = new File("cache.txt");
                if (cache.exists()) {
                    BufferedReader reader = null;
                    try {
                        reader = new BufferedReader(new FileReader("cache.txt"));
                        ArrayList<File> files = new ArrayList<File>();
                        ArrayList<Integer> rates = new ArrayList<Integer>();
                        String path = reader.readLine();
                        while (path != null) {
                            int rate = Integer.parseInt(reader.readLine());
                            File image = new File(path);
                            files.add(image);
                            rates.add(rate);
                            path = reader.readLine();
                        }
                        reader.close();
                        model.loadCache(files, rates);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }

            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                // Delete old cache
                File oldCache = new File("cache.txt");
                if (oldCache.exists()) {
                    oldCache.delete();
                }

                // Create a new cache
                File newCache = new File("cache.txt");
                try {
                    newCache.createNewFile();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                FileWriter writer = null;
                try {
                    writer = new FileWriter("cache.txt");
                    for (ImageModel image : model.getImageList()) {
                        writer.write(image.getPath() + System.getProperty( "line.separator"));
                        int rate = image.getRate();
                        writer.write(Integer.toString(rate) + System.getProperty( "line.separator"));
                    }
                    writer.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Set view configs
        menu = new MenuView(model);
        ImageListView imageList = new ImageListView(model);
        scrollPane = new JScrollPane(imageList,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        scrollPane.getVerticalScrollBar().setUnitIncrement(15);
        this.add(menu, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.setSize(new Dimension(1000, 800));
        this.setMinimumSize(new Dimension(600, 420));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}