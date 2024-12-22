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

public class TestUpdateBook_withInvalidData extends BaseAPITest {

    private BookAPIClient bookAPIClient;
    private TestUtils testUtils;

    @BeforeMethod
    public void setup() {
        bookAPIClient = new BookAPIClient(this);
        testUtils = new TestUtils();
    }

    @Test
    public void testInvalidBookUpdate() {

        String username = "admin";

        Response createValiedBook = testUtils.createTestBook(bookAPIClient, "First Book Title", "First Author", username);

        Integer createdBookId = createValiedBook.jsonPath().getInt("id");
        
        Map<String, Object> invalidBookData = new HashMap<>();
        invalidBookData.put("id", createdBookId);
        invalidBookData.put("title", ""); 
        invalidBookData.put("author", null);

        Response updateResponse = bookAPIClient.updateBook(username, createdBookId, invalidBookData);
        APIResponseValidator.invalidBookUpdate(updateResponse);
        
    }
}