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
        Assert.assertEquals(response.getStatusCode(), 200, "Book deletion failed");
    }

    public static void validateBadRequest(Response response) {
        Assert.assertEquals(response.getStatusCode(), 400, "Bad request validation failed");
    }

    public static void validateUnauthorizedAccess(Response response) {
        Assert.assertEquals(response.getStatusCode(), 403, "Unauthorized access should be forbidden");
    }
}
