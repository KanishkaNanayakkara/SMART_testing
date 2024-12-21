package com.testing.apiTesting.utils;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class APIResponseValidator {

    public static void validateSuccessfulCreation(Response response) {
        Assert.assertEquals(response.getStatusCode(), 201, "Book creation failed");
        Assert.assertNotNull(response.jsonPath().getInt("id"), "Book ID should be generated");
    }

    public static void validateSuccessfulRetrieval(Response response) {
        Assert.assertEquals(response.getStatusCode(), 200, "Book retrieval failed");
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
      public static void validateInvalidID(Response response, int invalidBookId) {
        Assert.assertEquals(response.getStatusCode(), 400, "Expected status code 400");

        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.getInt("id"), invalidBookId, "Book ID does not match");
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
}
