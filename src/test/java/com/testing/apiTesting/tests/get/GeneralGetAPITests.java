package com.testing.apiTesting.tests.get;

import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.testing.apiTesting.apiClients.BookAPIClient;
import com.testing.apiTesting.base.BaseAPITest;
import com.testing.apiTesting.utils.APIResponseValidator;
import com.testing.apiTesting.utils.TestUtils;


public class GeneralGetAPITests extends BaseAPITest {

    private BookAPIClient bookAPIClient;
    private TestUtils testUtils;

    String title = "";
    String author = "";
    String adminUser = "admin";
    String generalUser = "user";

    @BeforeMethod
    public void setUp() {
        bookAPIClient = new BookAPIClient(this);
        testUtils = new TestUtils();
    }

    @Test(description = "Verify getting all books with valid response and headers")
    public void testGetAllBooks_ValidScenario() {
        // Create book before get
        title = "Test Book 6";
        author = "Test author 6";
        Response createdResponse = testUtils.createTestBook(bookAPIClient, title, author, adminUser);
        APIResponseValidator.validateSuccessfulCreation(createdResponse);

        Response response = bookAPIClient.getAllBooks(generalUser);
        APIResponseValidator.validateSuccessfulRetrieval(response);
    }

    @Test(description = "Verify getting a book by ID with valid response")
    public void testGetBookById_ValidScenario() {
        // Create book before get
        title = "Test Book 7";
        author = "Test author 7";
        Response createdResponse = testUtils.createTestBook(bookAPIClient, title, author, adminUser);

        int validBookId = createdResponse.jsonPath().getInt("id");

        Response response = bookAPIClient.getBookById(generalUser, validBookId);
        APIResponseValidator.validateSuccessfulRetrievalofaSinglebook(response, validBookId);
    }

    @Test(description = "Verify getting all books when the database is empty")
    public void testGetAllBooks_EmptyDatabase() {
        Response response = bookAPIClient.getAllBooks(generalUser);
        APIResponseValidator.validateRetrievalEmptyDatabase(response);
    }

    @Test(description = "Verify getting a book by non-exist book id")
    public void testGetBookById_NonExistBookId() {
        int nonExistBookId = 999;

        Response response = bookAPIClient.getBookById(adminUser, nonExistBookId);
        APIResponseValidator.validateInvalidId(response);
    }

    @Test(description = "Verify getting a book by ID with invalid Id format")
    public void testGetBookById_WithInvalidId() {
        int invalidBookId = -5;
        Response response = bookAPIClient.getBookById(adminUser, invalidBookId);
        APIResponseValidator.validateBadRequest(response); 
    }
}


