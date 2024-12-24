package com.testing.apiTesting.tests;

import com.testing.apiTesting.apiClients.BookAPIClient;
import com.testing.apiTesting.base.BaseAPITest;
import com.testing.apiTesting.utils.APIResponseValidator;
import com.testing.apiTesting.utils.TestUtils;

import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class TestUpdateBook extends BaseAPITest {

    private BookAPIClient bookAPIClient;
    private TestUtils testUtils;

    @BeforeMethod
    public void setup() {
        bookAPIClient = new BookAPIClient(this);
        testUtils = new TestUtils();
    }

    @Test
    public void testValidBookUpdate() {
        String username = "admin";

        Response createValiedBook = testUtils.createTestBook(bookAPIClient, "Test Book 9", "Test Author 9", username);
        
        Integer createdBookId = createValiedBook.jsonPath().getInt("id");

        Map<String, Object> updatedBookData = new HashMap<>();
        updatedBookData.put("id", createdBookId);
        updatedBookData.put("title", "Updated Book Title");
        updatedBookData.put("author", "Updated Author");

        bookAPIClient.updateBook(username, createdBookId, updatedBookData);

        Response getUpdatedResponse = bookAPIClient.getBookById(username, createdBookId);
        APIResponseValidator.updateBookTest(getUpdatedResponse);
    }
}
