package com.leonlib.model;

import java.util.List;

public class BookDetail {
    private Long id;
    private String title;
    private String author;
    private String description;
    private List<String> imageNames;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
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

    public List<String> getImageNames() {
        return imageNames;
    }

    public void setImageNames(final List<String> imageNames) {
        this.imageNames = imageNames;
    }

    @Override
    public String toString() {
        return "BookDetail [id=" + id + ", title=" + title + ", author=" + author + ", description=" + description
                + ", imageNames=" + imageNames + "]";
    }
}
