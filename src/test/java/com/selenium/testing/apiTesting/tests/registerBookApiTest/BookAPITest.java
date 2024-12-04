package com.selenium.testing.apiTesting.tests.registerBookApiTest;

import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Map;

import com.selenium.testing.apiTesting.tests.BaseApiTest;
import com.selenium.testing.apiTesting.tests.bookDataFactory.BookDataFactory;
import com.selenium.testing.apiTesting.tests.pages.BookPage;
import com.selenium.testing.apiTesting.tests.utils.APIResponseValidator;

public class BookAPITest extends BaseApiTest {

    private BookPage bookPage;

    @BeforeMethod
    public void setUp() {
        bookPage = new BookPage(this);
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
