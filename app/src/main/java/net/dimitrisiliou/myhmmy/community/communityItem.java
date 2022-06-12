package net.dimitrisiliou.myhmmy.community;

import java.io.Serializable;

public class communityItem implements Serializable {

    private int image;
    private String title,description;

    public communityItem(){
    }

    public communityItem(int image) {
        this.image = image;
    }

    public communityItem(int image, String description) {
        this.image = image;
        this.title = description;
    }

    public communityItem(int image, String title, String description) {
        this.image = image;
        this.title = title;
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
