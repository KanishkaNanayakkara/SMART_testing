package com.testing.apiTesting.tests.post;

import io.restassured.response.Response;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.testing.apiTesting.apiClients.BookAPIClient;
import com.testing.apiTesting.base.BaseAPITest;
import com.testing.apiTesting.utils.APIResponseValidator;
import com.testing.apiTesting.utils.BookDataFactory;
import com.testing.apiTesting.utils.TestUtils;

import java.util.Map;

public class GeneralPostAPITests extends BaseAPITest {

    private BookAPIClient bookAPIClient;
    private TestUtils testUtils;

    String title = "";
    String author = "";
    String adminUser = "admin";
    String generalUser = "user";
    
    @BeforeMethod
    public void setUp(){
        bookAPIClient = new BookAPIClient(this);
        testUtils = new TestUtils();
    }

    @Test(description = "Verify creating a book with valid data")
    public void testCreateBookSuccessfully(){
        title = "Test Book 1";
        author = "Test author 1";
        Map<String, Object> bookData = BookDataFactory.createValidBook(title, author);
        Response response = bookAPIClient.createBook(adminUser, bookData);
        APIResponseValidator.validateSuccessfulCreation(response);
    }

    @Test(description = "Verify book creation fails when the title is missing")
    public void testCreateBookWithMissingTitle(){
        author = "Test author 2";
        Map<String, Object> invalidBookData = BookDataFactory.createBookWithMissingTitle(author);
        Response response = bookAPIClient.createBook(adminUser, invalidBookData);
        APIResponseValidator.validateBadRequest(response);
    }

    @Test(description = "Verify book creation fails when the author is missing")
    public void testCreateBookWithMissingAuthor(){
        title = "Test Book 2";
        Map<String, Object> invalidBookData = BookDataFactory.createBookWithMissingAuthor(title);
        Response response = bookAPIClient.createBook(adminUser, invalidBookData);
        APIResponseValidator.validateBadRequest(response);
    }

    @Test(description = "Verify unauthorized access error for non-admin user")
    public void testUnauthorizedAccess(){
        title = "Test Book 3";
        author = "Test author 3";
        Map<String, Object> bookData = BookDataFactory.createValidBook(title, author);
        Response response = bookAPIClient.createBook(generalUser, bookData);
        APIResponseValidator.validateUnauthorizedAccess(response);
    }

    @Test(description = "Verify creating a book with empty strings for title and author")
    public void testCreateBook_WithEmptyStringsForTitleAndAuthor(){
        title = "";
        author = "";
        Map<String, Object> bookData = BookDataFactory.createValidBook(title, author);
        Response response = bookAPIClient.createBook(adminUser, bookData);
        APIResponseValidator.validateBadRequest(response);
    }

    @Test(description = "Verify duplicate book id handling")
    public void testDuplicateBookIdHandling() {

        Response createdBook = testUtils.createTestBook(bookAPIClient, adminUser);

        int bookId = createdBook.jsonPath().getInt("id");
        String createdBookTitle = createdBook.jsonPath().getString("title");
        String createdBookAuthor = createdBook.jsonPath().getString("author");

        Response duplicateResponse = bookAPIClient.createBook(adminUser, Map.of(
            "id", bookId,
            "title", createdBookTitle,
            "author", createdBookAuthor
        ));

        APIResponseValidator.validateDuplicateCreation(duplicateResponse);
    }
}
