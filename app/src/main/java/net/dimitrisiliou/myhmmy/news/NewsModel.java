package net.dimitrisiliou.myhmmy.news;

public class NewsModel {
    private String link_id;
    private String title;
    private String pubDate;
    private String context;
    private int counter;

    public NewsModel(String link_id, String title, String pubDate, String context, int counter) {
        this.link_id = link_id;
        this.title = title;
        this.pubDate = pubDate;
        this.context = context;
        this.counter = counter;
    }

    public String getLink_id() {
        return link_id;
    }

    public void setLink_id(String link_id) {
        this.link_id = link_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    @Override
    public String toString() {
        return "RssModel{" +
                "link_id='" + link_id + '\'' +
                ", title='" + title + '\'' +
                ", pubDate='" + pubDate + '\'' +
                ", context='" + context + '\'' +
                '}';
    }
}



