package net.dimitrisiliou.myhmmy.map;

public class MapItem {

    private String name,desc,map;

    public MapItem(String name, String desc, String website) {
        this.name = name;
        this.desc = desc;
        this.map = website;
    }

    @Override
    public String toString() {
        return "MapItem{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", website='" + map + '\'' +
                '}';
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

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }
}
