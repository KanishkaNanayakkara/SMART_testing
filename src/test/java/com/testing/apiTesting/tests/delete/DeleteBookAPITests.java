package com.testing.apiTesting.tests.delete;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.restassured.response.Response;

import com.testing.apiTesting.apiClients.BookAPIClient;
import com.testing.apiTesting.base.BaseAPITest;
import com.testing.apiTesting.utils.APIResponseValidator;
import com.testing.apiTesting.utils.TestUtils;

public class DeleteBookAPITests extends BaseAPITest {

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

    @Test(description = "Verify successful deletion of a book by ID")
    public void testDeleteBook_SuccessScenario() {
        // Create book before delete
        title = "Test Book 4";
        author = "Test author 4";
        Response response = testUtils.createTestBook(bookAPIClient, title, author, adminUser);

        int bookId = response.jsonPath().getInt("id");

        Response deleteResponse = bookAPIClient.deleteBook(adminUser, bookId);

        APIResponseValidator.validateSuccessfulDeletion(deleteResponse);
    }

    @Test(description = "Verify unauthorized access is prevented when deleting a book")
    public void testDeleteBook_UnauthorizedAccess() {
        // Create book before delete
        title = "Test Book 5";
        author = "Test author 5";
        Response response = testUtils.createTestBook(bookAPIClient, title, author, adminUser);

        int bookId = response.jsonPath().getInt("id");

        Response deleteResponse = bookAPIClient.deleteBook(generalUser, bookId);

        APIResponseValidator.validateUnauthorizedAccess(deleteResponse);
    }

    @Test(description = "Verify deletion of a non-existent book")
    public void testDeleteBook_NonExistentBook() {

        int nonExistentBookId = 999;

        Response deleteResponse = bookAPIClient.deleteBook(adminUser, nonExistentBookId);

        APIResponseValidator.validateInvalidId(deleteResponse);
    }
}