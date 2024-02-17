package com.leonlib.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.util.List;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private String description;
    private boolean hasBeenRead;
    
    @ElementCollection
    private List<String> imageNames;

    private String addedOn;

    private String goodreadsLink;

    public void setId(final Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
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

    public boolean isHasBeenRead() {
        return hasBeenRead;
    }

    public void setHasBeenRead(final boolean hasBeenRead) {
        this.hasBeenRead = hasBeenRead;
    }

    public List<String> getImageNames() {
        return imageNames;
    }

    public void setImageNames(final List<String> imageNames) {
        this.imageNames = imageNames;
    }

    public String getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(final String addedOn) {
        this.addedOn = addedOn;
    }

    public String getGoodreadsLink() {
        return goodreadsLink;
    }

    public void setGoodreadsLink(final String goodreadsLink) {
        this.goodreadsLink = goodreadsLink;
    }

    @Override
    public String toString() {
        return "Book [id=" + id + ", title=" + title + ", author=" + author + ", description=" + description
                + ", hasBeenRead=" + hasBeenRead + ", imageNames=" + imageNames
                + ", addedOn=" + addedOn + ", goodreadsLink=" + goodreadsLink + "]";
    }
}
