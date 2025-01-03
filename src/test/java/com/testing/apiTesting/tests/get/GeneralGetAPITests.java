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

    String adminUser = "admin";
    String generalUser = "user";

    @BeforeMethod
    public void setUp() {
        bookAPIClient = new BookAPIClient(this);
        testUtils = new TestUtils();
    }

    @Test(description = "Verify Retrieving all books as an admin user")
    public void testGetAllBooksasAdminUser_ValidScenario() {
        // Create book before get
        Response createdResponse = testUtils.createTestBook(bookAPIClient,adminUser);
        APIResponseValidator.validateSuccessfulCreation(createdResponse);

        Response response = bookAPIClient.getAllBooks(generalUser);
        APIResponseValidator.validateSuccessfulRetrieval(response);
    }

    @Test(description = "Verify Retrieving a book by ID as an admin user")
    public void testGetBookByIdasAdminUser_ValidScenario() {
        // Create book before get
        Response createdResponse = testUtils.createTestBook(bookAPIClient,adminUser);

        int validBookId = createdResponse.jsonPath().getInt("id");

        Response response = bookAPIClient.getBookById(adminUser, validBookId);
        APIResponseValidator.validateSuccessfulRetrievalofaSinglebook(response, validBookId);
    }

    @Test(description = "Verify Retrieving a book by ID as a general user")
    public void testGetBookByIdasGeneralUser_ValidScenario() {
        // Create book before get
        Response createdResponse = testUtils.createTestBook(bookAPIClient, adminUser);

        int validBookId = createdResponse.jsonPath().getInt("id");

        Response response = bookAPIClient.getBookById(generalUser, validBookId);
        APIResponseValidator.validateSuccessfulRetrievalofaSinglebook(response, validBookId);
    }

    @Test(description = "Verify Retrieving a book by non-exist book id as an admin user")
    public void testGetBookById_NonExistBookId() {
        int nonExistBookId = 999;

        Response response = bookAPIClient.getBookById(adminUser, nonExistBookId);
        APIResponseValidator.validateInvalidId(response);
    }

    @Test(description = "Verify Retrieving a book by ID with invalid Id format as an admin user")
    public void testGetBookById_WithInvalidId() {
        int invalidBookId = -5;
        Response response = bookAPIClient.getBookById(adminUser, invalidBookId);
        APIResponseValidator.validateInvalidId(response); 
    }

    @Test(description ="Verify Retrieving all Books as a general user")
    public void testGetAllBooksasGeneralUser() {
        String user = "user";
        Response response = bookAPIClient.getAllBooks(user);
        APIResponseValidator.validateGetAllBooksWithGeneralUser(response);
    }
}


