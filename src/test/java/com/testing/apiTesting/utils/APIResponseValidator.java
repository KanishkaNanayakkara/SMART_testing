package com.testing.apiTesting.utils;

import org.testng.Assert;

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
        Assert.assertEquals(response.getStatusCode(), 200, "Book deletion failed");
    }

    public static void validateBadRequest(Response response) {
        Assert.assertEquals(response.getStatusCode(), 400, "Bad request validation failed");
    }

    public static void validateUnauthorizedAccess(Response response) {
        Assert.assertEquals(response.getStatusCode(), 403, "Unauthorized access should be forbidden");
    }
}
