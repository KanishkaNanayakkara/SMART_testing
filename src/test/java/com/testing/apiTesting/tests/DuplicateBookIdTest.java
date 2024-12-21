package com.testing.apiTesting.tests;

import com.testing.apiTesting.apiClients.BookAPIClient;
import com.testing.apiTesting.base.BaseAPITest;
import com.testing.apiTesting.utils.APIResponseValidator;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;

public class DuplicateBookIdTest extends BaseAPITest {

    @Test
    public void testDuplicateBookIdHandling() {

        BookAPIClient bookAPIClient = new BookAPIClient(this);

        String username = "admin";

        Map<String, Object> bookData = new HashMap<>();
        bookData.put("id", 1);
        bookData.put("title", "First Book Title");
        bookData.put("author", "First Author");

        bookAPIClient.createBook(username, bookData);

        Response duplicateResponse = bookAPIClient.createBook(username, bookData);

        APIResponseValidator.validateDuplicateCreation(duplicateResponse);
    }
}
