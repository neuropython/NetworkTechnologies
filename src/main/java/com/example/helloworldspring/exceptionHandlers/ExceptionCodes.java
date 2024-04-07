package com.example.helloworldspring.exceptionHandlers;

public enum ExceptionCodes {
    USER_NOT_FOUND("User not found"),
    USER_ALREADY_EXISTS("User already exists"),
    INVALID_CREDENTIALS("Invalid credentials"),
    INVALID_TOKEN("Invalid token"),
    INVALID_ROLE("Invalid role"),

    TOKEN_BLACKLISTED("Token is blacklisted"),

    BOOK_NOT_FOUND("Book not found"),

    BOOK_ALREADY_EXISTS("Book with that ISBN exists"),

    BOOK_NOT_AVAILABLE("Book not available"),

    BOOK_CANNOT_HAVE_NEGATIVE_AVAILABLE_COPIES("Book cannot have negative available copies"),

    GENRE_DOES_NOT_EXIST("Genre does not exist"),

    BOOK_DETAILS_NOT_FOUND("Book details not found"),

    BOOK_DETAILS_ALREADY_EXISTS("Book details already exists"),

    BOOK_AUTHOR_NOT_FOUND("Book author not found");


    private final String message;

    ExceptionCodes(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}
