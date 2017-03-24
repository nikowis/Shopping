package pl.nikowis.shopping;

/**
 * Created by Nikodem on 3/17/2017.
 */
public class ShoppingItem {
    private String title;
    private String description;
    private long id;
    private int image;

    public ShoppingItem(String title, String description, int image) {
        this.title = title;
        this.description = description;
        this.image = image;
    }

    public ShoppingItem(long id, String title, String desc, int image) {
        this.title = title;
        this.description = desc;
        this.image = image;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getImage() {
        return image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
