package Model;

import View.IView;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ImageModel implements IRateModel {
    private File file;
    private ImageListModel imageListModel;
    private BufferedImage bufferedImage;
    private String name;
    private String path;
    private String date;
    private int width;
    private int height;
    private int rate;
    private ArrayList<IView> views = new ArrayList<IView>();

    public ImageModel(File file, ImageListModel imageListModel) throws IOException {
        this.file = file;
        this.imageListModel = imageListModel;
        this.bufferedImage = ImageIO.read(file);
        this.name = file.getName();
        this.path = file.getAbsolutePath();
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        this.date = dateFormat.format(file.lastModified());
        this.width = bufferedImage.getWidth();
        this.height = bufferedImage.getHeight();
        this.rate = 0;
    }

    public int getWidth() { return width; }

    public int getHeight() { return height; }

    public BufferedImage getBufferedImage() { return bufferedImage; }

    public void setRate(int rate) {
        this.rate = rate;
        notifyObservers("rate");
    }

    public File getFile() { return this.file; }

    public String getName() {
        return this.name;
    }

    public String getPath() {
        return this.path;
    }

    public String getDate() { return date; }

    public int getRate() {
        return this.rate;
    }

    public boolean isModelEmpty() {
        return false;
    }

    public void addView(IView view) {
        views.add(view);
        view.updateView("addImageModel");
    }

    private void notifyObservers(String action) {
        for (IView view : this.views) {
            view.updateView(action);
        }
        imageListModel.setIndividualRate();
    }
}
