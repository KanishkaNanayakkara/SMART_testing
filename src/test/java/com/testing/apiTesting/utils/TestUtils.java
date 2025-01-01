package com.testing.apiTesting.utils;

import java.util.Map;

import com.testing.apiTesting.apiClients.BookAPIClient;
import io.restassured.response.Response;

public class TestUtils {

    public Response createTestBook(BookAPIClient bookAPIClient, String user) {
        // Generate unique book title and author
        String uniqueIdentifier = String.valueOf(System.currentTimeMillis());
        String title = "Test Book " + uniqueIdentifier;
        String author = "Test Author " + uniqueIdentifier;
        
        Map<String, Object> bookData = BookDataFactory.createValidBook(title, author);
        Response response = bookAPIClient.createBook(user, bookData);
        return response;
    }

}
