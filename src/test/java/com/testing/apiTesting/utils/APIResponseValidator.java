package com.testing.apiTesting.utils;

import java.util.List;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class APIResponseValidator {

    public static void validateSuccessfulCreation(Response response) {
        Assert.assertEquals(response.getStatusCode(), 201, "Expected status code 201");
        Assert.assertNotNull(response.jsonPath().getInt("id"), "Book ID should be generated");
    }

    public static void validateSuccessfulRetrieval(Response response) {
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200");
        // Assert response headers
        Assert.assertEquals(response.getHeader("Content-Type"), "application/json",
                "Expected Content-Type to be application/json");

        // Assert response body is not empty
        Assert.assertNotNull(response.getBody().asString(), "Response body should not be null");
    }

    public static void validateSuccessfulRetrievalofaSinglebook(Response response, int validBookId) {
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200");
        // Assert the specific book details
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.getInt("id"), validBookId, "Book ID does not match");
        Assert.assertNotNull(jsonPath.getString("title"), "Book title should not be null");
        Assert.assertNotNull(jsonPath.getString("author"), "Book author should not be null");
    }

    public static void validateRetrievalEmptyDatabase(Response response) {
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200");
        // Validate response body is an empty list
        List<?> books = response.jsonPath().getList("$");
        Assert.assertTrue(books.isEmpty(), "Expected an empty book list");
    }

    public static void validateSuccessfulUpdate(Response response) {
        Assert.assertEquals(response.getStatusCode(), 200, "Book update failed");
    }

    public static void validateSuccessfulDeletion(Response response) {
        Assert.assertEquals(response.getStatusCode(), 200, "Book deletion successful");
    }

    public static void validateBadRequest(Response response) {
        Assert.assertEquals(response.getStatusCode(), 400, "Bad request validation failed");
    }

    public static void validateUnauthorizedAccess(Response response) {
        Assert.assertEquals(response.getStatusCode(), 403, "Unauthorized access should be forbidden");
    }
      public static void validateInvalidId(Response response) {
        Assert.assertEquals(response.getStatusCode(), 404, "Expected status code 404");
    }
    public static void validateDuplicateCreation(Response response) {
        int statusCode = response.statusCode();
        if (statusCode == 409) {
            Assert.assertEquals(statusCode, 409, "Expected 409 Conflict for duplicate book ID");
        } else if (statusCode == 208) {
            Assert.assertEquals(statusCode, 208, "Expected 208 Already Reported for duplicate book ID");
        }
    }
    public static void updateBookTest(Response response) {
        Assert.assertEquals(response.jsonPath().getString("title"), "Updated Book Title", "Title did not update correctly");
        Assert.assertEquals(response.jsonPath().getString("author"), "Updated Author", "Author did not update correctly");
    }
    public static void invalidBookUpdate(Response response) {
        Assert.assertEquals(response.statusCode(), 400, "Expected 400 Bad Request for invalid book update");
    }
    public static void notExistBookUpdate(Response response) {
        Assert.assertEquals(response.statusCode(), 400, "Expected 400 Bad Request for non-existent book update");
    }
    
    public static void bookValidation(Response response){
        Assert.assertEquals(response.statusCode(), 400, "Expected 400 Bad Request for invalid input");
    }

    public static void unauthorizedUpdate(Response response){
        Assert.assertEquals(response.getStatusCode(), 403, "Expected 403 Unauthorized status code");
    }
}
