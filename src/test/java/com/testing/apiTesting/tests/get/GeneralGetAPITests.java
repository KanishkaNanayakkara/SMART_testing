package com.testing.apiTesting.tests.get;

import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.testing.apiTesting.apiClients.BookAPIClient;
import com.testing.apiTesting.base.BaseAPITest;
import com.testing.apiTesting.utils.APIResponseValidator;


public class GeneralGetAPITests extends BaseAPITest {

    private BookAPIClient bookAPIClient;

    @BeforeMethod
    public void setUp() {
        bookAPIClient = new BookAPIClient(this);
    }

    @Test(description = "Verify getting all books with valid response and headers")
    public void testGetAllBooks_ValidScenario() {
        Response response = bookAPIClient.getAllBooks("user");
        APIResponseValidator.validateSuccessfulRetrieval(response);
    }

    @Test(description = "Verify getting a book by ID with valid response")
    public void testGetBookById_ValidScenario() {
        int validBookId = 1; // Replace with a valid book ID available in the database
        Response response = bookAPIClient.getBookById("user", validBookId);
        APIResponseValidator.validateSuccessfulRetrievalofaSinglebook(response, validBookId);
    }

    @Test(description = "Verify getting all books when the database is empty")
    public void testGetAllBooks_EmptyDatabase() {
        Response response = bookAPIClient.getAllBooks("user");
        APIResponseValidator.validateRetrievalEmptyDatabase(response);
    }
}


