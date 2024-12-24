package com.testing.apiTesting.tests.put;

import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.testing.apiTesting.apiClients.BookAPIClient;
import com.testing.apiTesting.base.BaseAPITest;
import com.testing.apiTesting.utils.APIResponseValidator;
import com.testing.apiTesting.utils.BookDataFactory;
import com.testing.apiTesting.utils.TestUtils;

public class GeneralPutAPITests extends BaseAPITest {
    private BookAPIClient bookAPIClient;
    private TestUtils testUtils;

    String username = "admin";

    @BeforeMethod
    public void setUp(){
        bookAPIClient = new BookAPIClient(this);
        testUtils = new TestUtils();
    }

    @Test(description = "Verify updating a book with invalid data")
    public void testInvalidBookUpdate() {

        Response createValiedBook = testUtils.createTestBook(bookAPIClient, "Test Book 8", "Test Author 8", username);

        Integer createdBookId = createValiedBook.jsonPath().getInt("id");
        
        Map<String, Object> invalidBookData = new HashMap<>();
        invalidBookData.put("id", createdBookId);
        invalidBookData.put("title", ""); 
        invalidBookData.put("author", null);

        Response updateResponse = bookAPIClient.updateBook(username, createdBookId, invalidBookData);
        APIResponseValidator.invalidBookUpdate(updateResponse);
        
    }

    @Test(description = "Verify updating a book with valid data")
    public void testValidBookUpdate() {

        Response createValiedBook = testUtils.createTestBook(bookAPIClient, "Test Book 9", "Test Author 9", username);
        
        Integer createdBookId = createValiedBook.jsonPath().getInt("id");

        Map<String, Object> updatedBookData = new HashMap<>();
        updatedBookData.put("id", createdBookId);
        updatedBookData.put("title", "Updated Book Title");
        updatedBookData.put("author", "Updated Author");

        bookAPIClient.updateBook(username, createdBookId, updatedBookData);

        Response getUpdatedResponse = bookAPIClient.getBookById(username, createdBookId);
        APIResponseValidator.updateBookTest(getUpdatedResponse);
    }

    @Test(description = "Verify updating a non-existent book")
    public void testUpdateNonexistentBook() {

        Map<String, Object> invalidBookData = new HashMap<>();
        invalidBookData.put("title", "Invalid Update Title");
        invalidBookData.put("author", "Invalid Update Author");

        int nonExistentBookId = 99999;

        Response updateResponse = bookAPIClient.updateBook(username, nonExistentBookId, invalidBookData);
        APIResponseValidator.notExistBookUpdate(updateResponse);
       
    }

    @DataProvider(name = "invalidBookData")
    public Object[][] invalidBookDataProvider() {
        return new Object[][]{
                {1, BookDataFactory.createBookWithMissingTitle(username), "Title is required"},
                {2, BookDataFactory.createBookWithMissingAuthor(username), "Author is required"},
                {3, BookDataFactory.createBookWithMissingTitleAndAuthor(), "Title and Author are required"}
        };
    }
    
    @Test(dataProvider = "invalidBookData", description = "Verify updating a book with invalid data")
    public void testInputValidation(int bookId, Map<String, Object> bookData, String expectedErrorMessage) {
        Response response = bookAPIClient.updateBook(username, bookId, bookData);
        APIResponseValidator.bookValidation(response);
    }

    @Test(description = "Verify updating a book with unauthorized access")
    public void testUpdateBookUnauthorizedAccess() {
        
        int bookId = 1;
        Map<String, Object> bookData = new HashMap<>();
        bookData.put("title", "Unauthorized Update");
        bookData.put("author", "Unknown");

        Response response = bookAPIClient.updateBook("", bookId, bookData);
        APIResponseValidator.unauthorizedUpdate(response);
       
    }

    

    
}
