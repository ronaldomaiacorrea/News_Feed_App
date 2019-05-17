package com.example.news_feed_app;

public class NewsFeed {

    private int id;
    private String name;
    private String title;
    private String url;
    private String urlImage;

    public NewsFeed(int id, String name, String title, String url, String urlImage) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.url = url;
        this.urlImage = urlImage;
    }

    public NewsFeed(String name, String title, String url, String urlImage) {
        this.name = name;
        this.title = title;
        this.url = url;
        this.urlImage = urlImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    @Override
    public String toString() {
        return "NewsFeed{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
