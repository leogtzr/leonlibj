package com.leonlib.model;

import java.util.List;

public class BookDetail {
    private int id;
    private String title;
    private String author;
    private String description;
    private List<BookImageInfo> images;

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public void setAuthor(final String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }
    
    public void setDescription(final String description) {
        this.description = description;
    }

    public List<BookImageInfo> getImages() {
        return images;
    }
    
    public void setImages(final List<BookImageInfo> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "BookDetail [id=" + id + ", title=" + title + ", author=" + author + ", description=" + description
                + ", images=" + images + "]";
    }
}
