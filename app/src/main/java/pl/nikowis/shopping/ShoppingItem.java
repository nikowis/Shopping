package pl.nikowis.shopping;

/**
 * Created by Nikodem on 3/17/2017.
 */
public class ShoppingItem {
    private String title;
    private String description;
    private int resId;

    public ShoppingItem(String title, String description, int resId) {
        this.title = title;
        this.description = description;
        this.resId = resId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getResId() {
        return resId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }
}
