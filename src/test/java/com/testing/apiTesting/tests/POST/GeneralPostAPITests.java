package com.testing.apiTesting.tests.POST;

import io.restassured.response.Response;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.testing.apiTesting.apiClients.BookAPIClient;
import com.testing.apiTesting.base.BaseAPITest;
import com.testing.apiTesting.utils.APIResponseValidator;
import com.testing.apiTesting.utils.BookDataFactory;

import java.util.Map;

public class GeneralPostAPITests extends BaseAPITest {

    private BookAPIClient bookAPIClient;
    
    @BeforeMethod
    public void setUp(){
        bookAPIClient = new BookAPIClient(this);
    }

    @Test(description = "Verify creating a book with valid data")
    public void testCreateBookSuccessfully(){
        Map<String, Object> bookData = BookDataFactory.createValidBook();
        Response response = bookAPIClient.createBook("admin", bookData);
        APIResponseValidator.validateSuccessfulCreation(response);
    }

    @Test(description = "Verify book creation fails with missing mandatory fields")
    public void testCreateBookWithMissingField (){
        Map<String, Object> invalidBookData = BookDataFactory.creteInvalidBook();
        Response response = bookAPIClient.createBook("admin", invalidBookData);
        APIResponseValidator.validateBadRequest(response);
    }

    @Test(description = "Verify unauthorized access error for non-admin user")
    public void testUnauthorizedAccess(){
        Map<String, Object> bookData = BookDataFactory.createValidBook();
        Response response = bookAPIClient.createBook("user", bookData);
        APIResponseValidator.validateUnauthorizedAccess(response);
    }
}
