package com.example.helloworldspring.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.net.URL;
@Entity
public class BookDetails {
    @Id
    @ManyToOne
    @JoinColumn(name = "bookId", nullable = false)
    private Book book;
    private String genre;
    private String summary;
    private URL CoverImageURL;

    public Long getBookId() {
        return book.getBookId();
    }

    public void setBookId(Long bookId) {
        this.book.setBookId(bookId);
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public URL getCoverImageURL() {
        return CoverImageURL;
    }

    public void setCoverImageURL(URL coverImageURL) {
        CoverImageURL = coverImageURL;
    }

}
