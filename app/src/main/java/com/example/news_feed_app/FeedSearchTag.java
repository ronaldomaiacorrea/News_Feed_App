package com.example.news_feed_app;

public class FeedSearchTag {

    private long id;
    private String searchTag;

    public FeedSearchTag(long id, String searchTag) {
        this.id = id;
        this.searchTag = searchTag;
    }

    public FeedSearchTag(String searchTag) {
        this.searchTag = searchTag;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSearchTag() {
        return searchTag;
    }

    public void setSearchTag(String searchTag) {
        this.searchTag = searchTag;
    }
}