package com.leonlib.model;

public enum BookSearchType {
    ByTitle,
    ByAuthor,
    Unknown
    ;

    public static BookSearchType parse(final String type) {
        switch (type.toUpperCase()) {
            case "BYTITLE":
                return ByTitle;
            case "BYAUTHOR":
                return ByAuthor;
            default:
                return Unknown;
        }
    }
}
