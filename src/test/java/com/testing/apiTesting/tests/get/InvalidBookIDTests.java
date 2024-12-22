package com.testing.apiTesting.tests.get;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import com.testing.apiTesting.apiClients.BookAPIClient;
import com.testing.apiTesting.base.BaseAPITest;
import com.testing.apiTesting.utils.APIResponseValidator;

public class InvalidBookIDTests extends BaseAPITest {


    private BookAPIClient bookAPIClient;

    @BeforeMethod
    public void setUp() {
        bookAPIClient = new BookAPIClient(this);
    }

    @Test(description = "Verify getting a book by ID with invalid Id format")
    public void testGetBookById_WithInvalidId() {
        int invalidBookId = -5;
        String userName = "admin";
        Response response = bookAPIClient.getBookById(userName, invalidBookId);
        APIResponseValidator.validateBadRequest(response); 
    }
}