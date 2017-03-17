package pl.nikowis.shopping;

/**
 * Created by Nikodem on 3/17/2017.
 */
public class ShoppingItem {
    private String title;
    private String description;
    private Long photoId;


    public ShoppingItem(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Long getPhotoId() {
        return photoId;
    }
}
