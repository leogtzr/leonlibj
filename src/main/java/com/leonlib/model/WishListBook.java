package com.leonlib.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class WishListBook {
    @Id
    private Long id;
    private String title;
    private String author;
    private String description;
    private String imageLink;
    private String goodreadsLink;

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

    public String getGoodreadsLink() {
        return goodreadsLink;
    }

    public void setGoodreadsLink(final String goodreadsLink) {
        this.goodreadsLink = goodreadsLink;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(final String imageLink) {
        this.imageLink = imageLink;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "WishListBook{" +
                "author='" + author + '\'' +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", imageLink='" + imageLink + '\'' +
                ", goodreadsLink='" + goodreadsLink + '\'' +
                '}';
    }
}
