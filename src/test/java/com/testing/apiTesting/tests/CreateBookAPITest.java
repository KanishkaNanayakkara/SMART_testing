package com.testing.apiTesting.tests;

import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Map;

import com.testing.apiTesting.base.BaseApiTest;
import com.testing.apiTesting.pages.BookAPIPage;
import com.testing.apiTesting.utils.APIResponseValidator;
import com.testing.apiTesting.utils.BookDataFactory;

public class CreateBookAPITest extends BaseApiTest {

    private BookAPIPage bookPage;

    @BeforeMethod
    public void setUp() {
        bookPage = new BookAPIPage(this);
    }

    @Test(description = "Verify creating a book with valid data")
    public void testCreateBook() {
        Map<String, Object> bookData = BookDataFactory.createValidBook();
        Response response = bookPage.createBook("admin", bookData);
        APIResponseValidator.validateSuccessfulCreation(response);
    }

    @Test(description = "Verify getting all books")
    public void testGetAllBooks() {
        Response response = bookPage.getAllBooks("user");
        APIResponseValidator.validateSuccessfulRetrieval(response);
    }
}
