package com.selenium.testing.apiTesting.tests.bookDataFactory;

import java.util.HashMap;
import java.util.Map;

public class BookDataFactory {
    public static Map<String, Object> createValidBook() {
        Map<String, Object> bookData = new HashMap<>();
        bookData.put("title", "Test Book");
        bookData.put("author", "Test Author");
        return bookData;
    }

    public static Map<String, Object> createBookWithMissingTitle() {
        Map<String, Object> bookData = new HashMap<>();
        bookData.put("author", "Test Author");
        return bookData;
    }

    public static Map<String, Object> createBookWithMissingAuthor() {
        Map<String, Object> bookData = new HashMap<>();
        bookData.put("title", "Test Book");
        return bookData;
    }
}
