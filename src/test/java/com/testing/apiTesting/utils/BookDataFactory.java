package com.testing.apiTesting.utils;

import java.util.HashMap;
import java.util.Map;

public class BookDataFactory {
    public static Map<String, Object> createValidBook(String title, String author) {
        Map<String, Object> bookData = new HashMap<>();
        bookData.put("title", title);
        bookData.put("author", author);
        return bookData;
    }

    public static Map<String, Object> createBookWithMissingTitle(String author) {
        Map<String, Object> bookData = new HashMap<>();
        bookData.put("author", author);
        return bookData;
    }

    public static Map<String, Object> createBookWithMissingAuthor(String title) {
        Map<String, Object> bookData = new HashMap<>();
        bookData.put("title", title);
        return bookData;
    }
    public static Map<String, Object> createBookWithMissingTitleAndAuthor() {
        return new HashMap<>();
    }
}
