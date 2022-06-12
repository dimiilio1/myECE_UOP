package net.dimitrisiliou.myhmmy.professors;

public class ProfessorsItem {

    private String name,desc,email,website;
    private int img ;

    public ProfessorsItem(String name, String desc, int img, String email, String website) {
        this.name = name;
        this.desc = desc;
        this.img = img;
        this.email = email;
        this.website = website;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getwebsite() {
        return website;
    }

    public void setwebsite(String website) {
        this.website = website;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
