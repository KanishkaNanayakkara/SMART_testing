package com.testing.apiTesting.tests;

import com.testing.apiTesting.apiClients.BookAPIClient;
import com.testing.apiTesting.base.BaseAPITest;
import com.testing.apiTesting.utils.APIResponseValidator;

import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class UpdateBookTest extends BaseAPITest {

    private BookAPIClient bookAPIClient;

    @BeforeMethod
    public void setup() {
        bookAPIClient = new BookAPIClient(this);
    }

    @Test
    public void testValidBookUpdate() {
        String username = "admin";

        Map<String, Object> initialBookData = new HashMap<>();
        initialBookData.put("title", "Initial Book Title");
        initialBookData.put("author", "Initial Author");

        Response createResponse = bookAPIClient.createBook(username, initialBookData);
        System.out.println("Create Response: " + createResponse.body().asString());
        Integer createdBookId = createResponse.jsonPath().getInt("id");
        System.out.println("created book id is " + createdBookId);

        Response getCreatedResponse = bookAPIClient.getBookById(username,createdBookId);
        System.out.println("Get Created Book: " + getCreatedResponse.body().asString());

        Map<String, Object> updatedBookData = new HashMap<>();
        updatedBookData.put("id", createdBookId);
        updatedBookData.put("title", "Updated Book Title");
        updatedBookData.put("author", "Updated Author");

        Response updateResponse = bookAPIClient.updateBook(username, createdBookId, updatedBookData);
        System.out.println("Update Response: " + updateResponse.body().asString());

        Response getUpdatedResponse = bookAPIClient.getBookById(username, createdBookId);
        APIResponseValidator.updateBookTest(getUpdatedResponse);
    }
}
