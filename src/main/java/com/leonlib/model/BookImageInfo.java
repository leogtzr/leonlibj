package com.leonlib.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;


@Entity
public class BookImageInfo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq")
    @SequenceGenerator(name = "id_seq", sequenceName = "id_seq", allocationSize = 1)
    private Long id;

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "image_id_seq")
    @SequenceGenerator(name = "image_id_seq", sequenceName = "image_id_seq", allocationSize = 1)
    private Long imageId;
    private Long bookId;

    private String name;

    public long getImageId() {
        return imageId;
    }
    
    public void setImageId(final long imageId) {
        this.imageId = imageId;
    }
    
    public long getBookId() {
        return bookId;
    }
    
    public void setBookId(final long bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BookImageInfo [id=" + id + ", imageId=" + imageId + ", bookId=" + bookId + ", name=" + name + "]";
    }
}
