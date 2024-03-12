package com.example.helloworldspring.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Loans {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "bookId", nullable = false)
    private Book book;
    private Date loanDate;
    private Date returnDate;
    private Date DueDate;

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public Long getUserId() {
        return user.getUserId();
    }

    public void setUserId(Long userId) {
        if (this.user == null) {
            this.user = new User();
        }
        this.user.setUserId(userId);
    }

    public Long getBookId() {
        return book.getBookId();
    }

    public void setBookId(Long bookId) {
        if (this.book == null) {
            this.book = new Book();
        }
        this.book.setBookId(bookId);
    }

    public Date getIssueDate() {
        return loanDate;
    }

    public void setLoanDate(Date issueDate) {
        this.loanDate = issueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Date getStatus() {
        return DueDate;
    }


}
