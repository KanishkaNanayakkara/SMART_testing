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

    @Test(description = "Verify getting a book by ID with invalid response")
    public void testGetBookById_ValidScenario() {
        int invalidBookId = 5; 
        Response response = bookAPIClient.getBookById("admin", invalidBookId);
        APIResponseValidator.validateInvalidID(response, invalidBookId); 
    }
}