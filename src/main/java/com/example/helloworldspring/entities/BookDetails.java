package com.example.helloworldspring.entities;

import jakarta.persistence.*;

import java.net.URL;
@Entity
public class BookDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bookId", nullable = false)
    private Book book;
    private String genre;
    private String summary;
    private String CoverImageURL;

    public Long getBookId() {
        return book.getBookId();
    }

    public void setBookId(Long bookId) {
        if (this.book == null) {
            this.book = new Book();
        }
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

    public String getCoverImageURL() {
        return CoverImageURL;
    }

    public void setCoverImageURL(String coverImageURL) {
        CoverImageURL = coverImageURL;
    }

}
