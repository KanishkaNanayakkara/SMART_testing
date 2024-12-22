package com.testing.apiTesting.tests.delete;

import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Map;

import com.testing.apiTesting.apiClients.BookAPIClient;
import com.testing.apiTesting.base.BaseAPITest;
import com.testing.apiTesting.utils.APIResponseValidator;
import com.testing.apiTesting.utils.BookDataFactory;

public class CreateBookAPITests extends BaseAPITest {

    private BookAPIClient bookPage;

    @BeforeMethod
    public void setUp() {
        bookPage = new BookAPIClient(this);
    }

    @Test(description = "Verify creating a book with valid data")
    public void testCreateBook() {
        Map<String, Object> bookData = BookDataFactory.createValidBook();
        Response response = bookPage.createBook("admin", bookData);
        APIResponseValidator.validateSuccessfulCreation(response);
    }
}
