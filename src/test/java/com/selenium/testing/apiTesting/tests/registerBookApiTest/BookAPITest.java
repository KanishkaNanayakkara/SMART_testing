package com.selenium.testing.apiTesting.tests.registerBookApiTest;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;

import com.selenium.testing.apiTesting.tests.BaseApiTest;

public class BookAPITest extends BaseApiTest {

    @Test(priority = 1, description = "Verify creating a book with valid data")
    public void testCreateBook() {
        Map<String, Object> bookPayload = new HashMap<>();
        bookPayload.put("title", "Test Book");
        bookPayload.put("author", "Test Author");

        Response response = authAs("admin")
            .body(bookPayload)
            .when()
            .post("/api/books")
            .then()
            .extract().response();

        Assert.assertEquals(response.getStatusCode(), 201, "Book creation failed");
        Assert.assertNotNull(response.jsonPath().getInt("id"), "Book ID should be generated");
    }

    @Test(priority = 2, description = "Verify creating a book with missing title")
    public void testCreateBookWithMissingTitle() {
        Map<String, Object> bookPayload = new HashMap<>();
        bookPayload.put("author", "Test Author");

        Response response = authAs("admin")
            .body(bookPayload)
            .when()
            .post("/api/books")
            .then()
            .extract().response();

        Assert.assertEquals(response.getStatusCode(), 400, "Missing title should return 400");
    }

    @Test(priority = 3, description = "Verify creating a book with missing author")
    public void testCreateBookWithMissingAuthor() {
        Map<String, Object> bookPayload = new HashMap<>();
        bookPayload.put("title", "Test Book");

        Response response = authAs("admin")
            .when()
            .body(bookPayload)
            .post("/api/books")
            .then()
            .extract().response();

        Assert.assertEquals(response.getStatusCode(), 400, "Missing author should return 400");
    }

    @Test(priority = 4, description = "Verify getting all books")
    public void testGetAllBooks() {
        Response response = authAs("user")
            .when()
            .get("/api/books")
            .then()
            .extract().response();

        Assert.assertEquals(response.getStatusCode(), 200, "Get all books failed");
        Assert.assertTrue(response.jsonPath().getList("").size() >= 0, "Book list should be returned");
    }

    @Test(priority = 5, description = "Verify getting a specific book")
    public void testGetSpecificBook() {
        // First create a book
        Map<String, Object> bookPayload = new HashMap<>();
        bookPayload.put("title", "Specific Book");
        bookPayload.put("author", "Specific Author");

        Response createResponse = authAs("admin")
            .body(bookPayload)
            .when()
            .post("/api/books")
            .then()
            .extract().response();

        int bookId = createResponse.jsonPath().getInt("id");

        // Then retrieve the book
        Response getResponse = authAs("user")
            .when()
            .get("/api/books/" + bookId)
            .then()
            .extract().response();

        Assert.assertEquals(getResponse.getStatusCode(), 200, "Get specific book failed");
        Assert.assertEquals(getResponse.jsonPath().getString("title"), "Specific Book");
    }

    @Test(priority = 6, description = "Verify updating a book")
    public void testUpdateBook() {
        // First create a book
        Map<String, Object> createPayload = new HashMap<>();
        createPayload.put("title", "Original Book");
        createPayload.put("author", "Original Author");

        Response createResponse = authAs("admin")
            .body(createPayload)
            .when()
            .post("/api/books")
            .then()
            .extract().response();

        int bookId = createResponse.jsonPath().getInt("id");

        // Then update the book
        Map<String, Object> updatePayload = new HashMap<>();
        updatePayload.put("id", bookId);
        updatePayload.put("title", "Updated Book");
        updatePayload.put("author", "Updated Author");

        Response updateResponse = authAs("admin")
            .body(updatePayload)
            .when()
            .put("/api/books/" + bookId)
            .then()
            .extract().response();

        Assert.assertEquals(updateResponse.getStatusCode(), 200, "Book update failed");
    }

    @Test(priority = 7, description = "Verify deleting a book")
    public void testDeleteBook() {
        // First create a book
        Map<String, Object> bookPayload = new HashMap<>();
        bookPayload.put("title", "Book to Delete");
        bookPayload.put("author", "Delete Author");

        Response createResponse = authAs("admin")
            .body(bookPayload)
            .when()
            .post("/api/books")
            .then()
            .extract().response();

        int bookId = createResponse.jsonPath().getInt("id");

        // Then delete the book
        Response deleteResponse = authAs("admin")
            .when()
            .delete("/api/books/" + bookId)
            .then()
            .extract().response();

        Assert.assertEquals(deleteResponse.getStatusCode(), 200, "Book deletion failed");

        // Verify book is actually deleted
        Response getResponse = authAs("user")
            .when()
            .get("/api/books/" + bookId)
            .then()
            .extract().response();

        Assert.assertEquals(getResponse.getStatusCode(), 404, "Deleted book should not be found");
    }

    @Test(priority = 8, description = "Verify unauthorized access")
    public void testUnauthorizedAccess() {
        Map<String, Object> bookPayload = new HashMap<>();
        bookPayload.put("title", "Unauthorized Book");
        bookPayload.put("author", "Unauthorized Author");

        // Try to create book with user role (should be forbidden for POST)
        Response response = authAs("user")
            .body(bookPayload)
            .when()
            .post("/api/books")
            .then()
            .extract().response();

        Assert.assertEquals(response.getStatusCode(), 403, "Unauthorized user should be forbidden");
    }
}
