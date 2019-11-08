package Model;

import View.IView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ImageListModel implements IRateModel {
    private DisplayOptions displayOption;
    private int rate;
    private ArrayList<ImageModel> imageList;
    private ArrayList<ImageModel> shownImageList;
    private ArrayList<IView> views = new ArrayList<IView>();

    public ImageListModel() {
        this.displayOption = DisplayOptions.GRID;
        this.rate = 0;
        imageList = new ArrayList<ImageModel>();
        shownImageList = new ArrayList<ImageModel>();
    }

    public int getRate() {
        return rate;
    }

    public ArrayList<ImageModel> getShownImageList() { return shownImageList; }

    public ArrayList<ImageModel> getImageList() { return imageList; }

    public DisplayOptions getDisplayOption() {
        return displayOption;
    }

    public void setRate(int rate) {
        if (this.rate != rate) {
            this.rate = rate;
            shownImageList.clear();
            shownImageList = new ArrayList<ImageModel>();
            for (ImageModel image : imageList) {
                if (image.getRate() >= this.rate) {
                    shownImageList.add(image);
                }
            }
            notifyObservers("filter");
        }
    }

    public void setIndividualRate() {
        shownImageList.clear();
        shownImageList = new ArrayList<ImageModel>();
        for (ImageModel image : imageList) {
            if (image.getRate() >= this.rate) {
                shownImageList.add(image);
            }
        }
        notifyObservers("rateImageListModel");
    }

    public void setImageList(ArrayList<ImageModel> imageList) {
        this.imageList = imageList;
    }

    public void importImages(File[] files) throws IOException {
        boolean ifAdd = false;
        for (File file : files) {
            if (ifImageImported(file) == false) {
                ImageModel newImage = new ImageModel(file, this);
                imageList.add(newImage);
                if (newImage.getRate() >= this.rate) {
                    shownImageList.add(newImage);
                    ifAdd = true;
                }
            }
        }
        if (ifAdd == true) {
            notifyObservers("add");
        }
    }

    public void loadCache(ArrayList<File> files, ArrayList<Integer> rates) throws IOException {
        for (int i = 0; i < files.size(); i++) {
            File file = files.get(i);
            int rate = rates.get(i);
            if (ifImageImported(file) == false) {
                ImageModel newImage = new ImageModel(file, this);
                newImage.setRate(rate);
                imageList.add(newImage);
                if (newImage.getRate() >= this.rate) {
                    shownImageList.add(newImage);
                    notifyObservers("add");
                }
            }
        }
    }

    public void clearAllImages() {
        imageList.clear();
        imageList = new ArrayList<ImageModel>();
        shownImageList.clear();
        shownImageList = new ArrayList<ImageModel>();

        if (this.rate != 0) {
            this.rate = 0;
        }

        notifyObservers("clear");
    }

    public void setDisplayOption(DisplayOptions displayOption) {
        if (this.displayOption != displayOption) {
            this.displayOption = displayOption;
            notifyObservers("display");
        }
    }

    private boolean ifImageImported(File file) {
        for (ImageModel importedImg: imageList) {
            if (importedImg.getPath().equals(file.getAbsolutePath())) {
                return true;
            }
        }
        return false;
    }

    public boolean isModelEmpty() {
        return imageList.isEmpty();
    }

    public void addView(IView view) {
        views.add(view);
        view.updateView("addImageListView");
    }

    private void notifyObservers(String action) {
        for (IView view : this.views) {
            view.updateView(action);
        }
    }
}
