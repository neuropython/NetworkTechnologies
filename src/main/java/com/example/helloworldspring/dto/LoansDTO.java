package com.example.helloworldspring.dto;

import java.util.Date;

public class LoansDTO {
    private Long loanId;
    private Long bookId;
    private Long userId;
    private Date loanDate;
    private Date returnDate;
    private Date dueDate;

    private Boolean borrowed = true;




    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public Integer getBookId() {
        return Math.toIntExact(bookId);
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Integer getUserId() {
        return Math.toIntExact(userId);
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
    public Date getDueDate() {
        return dueDate;
    }
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Boolean getStatus() {
        return borrowed;
    }
    public void setStatus(Boolean status) {
        this.borrowed = status;
    }
}
