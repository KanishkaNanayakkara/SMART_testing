package com.testing.apiTesting.utils;

import java.util.Map;

import com.testing.apiTesting.apiClients.BookAPIClient;
import io.restassured.response.Response;

public class TestUtils {

    public Response createTestBook(BookAPIClient bookAPIClient, String title, String author, String user) {
        Map<String, Object> bookData = BookDataFactory.createValidBook(title, author);
        Response response = bookAPIClient.createBook(user, bookData);
        return response;
    }
}
