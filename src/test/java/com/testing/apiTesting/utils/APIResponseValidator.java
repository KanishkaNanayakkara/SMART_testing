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
        // Assert.assertNotNull(jsonPath.getString("title"), "Book title should not be null");
        // Assert.assertNotNull(jsonPath.getString("author"), "Book author should not be null");
    }

    
}
