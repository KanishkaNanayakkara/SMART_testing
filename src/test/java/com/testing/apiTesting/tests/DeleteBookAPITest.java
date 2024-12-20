package com.testing.apiTesting.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.restassured.response.Response;

import com.testing.apiTesting.apiClients.BookAPIClient;
import com.testing.apiTesting.base.BaseAPITest;
import com.testing.apiTesting.utils.APIResponseValidator;

public class DeleteBookAPITest extends BaseAPITest {

    private BookAPIClient bookAPIClient;

    @BeforeMethod
    public void setUp() {
        bookAPIClient = new BookAPIClient(this);
    }

    @Test(description = "Verify successful deletion of a book by ID")
    public void testDeleteBook_SuccessScenario() {
        // Step 1: Use a predefined book ID to test deletion
        String username = "user";
        int bookId = 1; // Replace with a valid book ID in your database

        // Step 2: Delete the book
        Response deleteResponse = bookAPIClient.deleteBook(username, bookId);

        // // Step 3: Validate successful deletion
        APIResponseValidator.validateSuccessfulDeletion(deleteResponse);

        // Step 4: Verify the book no longer exists
        // Response getDeletedResponse = bookAPIClient.getBookById(username, bookId);
        // APIResponseValidator.validateInvalidID(getDeletedResponse, bookId);
    }
}
