package com.testing.apiTesting.tests;

import com.testing.apiTesting.apiClients.BookAPIClient;
import com.testing.apiTesting.base.BaseAPITest;
import com.testing.apiTesting.utils.APIResponseValidator;
import com.testing.apiTesting.utils.TestUtils;

import io.restassured.response.Response;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Map;

public class DuplicateBookIdAPITest extends BaseAPITest {

    private BookAPIClient bookAPIClient;
    private TestUtils testUtils;

    @BeforeMethod
    public void setUp() {
        bookAPIClient = new BookAPIClient(this);
        testUtils = new TestUtils();
    }

    @Test
    public void testDuplicateBookIdHandling() {
        String username = "admin";

        Response createdBook = testUtils.createTestBook(bookAPIClient, "First Book Title", "First Author", username);

        int bookId = createdBook.jsonPath().getInt("id");

        Response duplicateResponse = bookAPIClient.createBook(username, Map.of(
            "id", bookId,
            "title", "First Book Title",
            "author", "First Author"
        ));

        APIResponseValidator.validateDuplicateCreation(duplicateResponse);
    }
}
