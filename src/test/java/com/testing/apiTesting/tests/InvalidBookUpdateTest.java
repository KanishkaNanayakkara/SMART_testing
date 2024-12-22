package com.testing.apiTesting.tests;

import com.testing.apiTesting.apiClients.BookAPIClient;
import com.testing.apiTesting.base.BaseAPITest;
import com.testing.apiTesting.utils.APIResponseValidator;

import io.restassured.response.Response;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class InvalidBookUpdateTest extends BaseAPITest {

    private BookAPIClient bookAPIClient;

    @BeforeMethod
    public void setup() {
        bookAPIClient = new BookAPIClient(this);
    }

    @Test
    public void testInvalidBookUpdate() {

        String username = "admin";

        String uniqueSuffix = String.valueOf(System.currentTimeMillis());

        Map<String, Object> initialBookData = new HashMap<>();
        initialBookData.put("title", "Valid Title " + uniqueSuffix);
        initialBookData.put("author", "Valid Author " + uniqueSuffix);

        Response createResponse = bookAPIClient.createBook(username, initialBookData);

        Integer createdBookId = createResponse.jsonPath().getInt("id");
        
        Map<String, Object> invalidBookData = new HashMap<>();
        invalidBookData.put("id", createdBookId);
        invalidBookData.put("title", ""); 
        invalidBookData.put("author", null);

        Response updateResponse = bookAPIClient.updateBook(username, createdBookId, invalidBookData);
        APIResponseValidator.invalidBookUpdate(updateResponse);
        
    }
}