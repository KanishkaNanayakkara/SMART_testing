package com.testing.apiTesting.pages;

import io.restassured.response.Response;
import java.util.Map;

import com.testing.apiTesting.base.BaseApiTest;

public class BookAPIPage {
    private BaseApiTest baseTest;

    public BookAPIPage(BaseApiTest baseTest) {
        this.baseTest = baseTest;
    }

    public Response createBook(String username, Map<String, Object> bookData) {
        return baseTest.authAs(username)
            .body(bookData)
            .post("/api/books");
    }

    public Response getAllBooks(String username) {
        return baseTest.authAs(username)
            .get("/api/books");
    }

    public Response getBookById(String username, int bookId) {
        return baseTest.authAs(username)
            .get("/api/books/" + bookId);
    }

    public Response updateBook(String username, int bookId, Map<String, Object> bookData) {
        return baseTest.authAs(username)
            .body(bookData)
            .put("/api/books/" + bookId);
    }

    public Response deleteBook(String username, int bookId) {
        return baseTest.authAs(username)
            .delete("/api/books/" + bookId);
    }
}
