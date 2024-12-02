package com.selenium.testing.apiTesting.tests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeSuite;

public class BaseApiTest {
    protected static RequestSpecification requestSpec;

    @BeforeSuite
    public void setup() {
        // Base configuration for all tests
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 7081;

        // Create a request specification with common configurations
        requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(7081)
            .addHeader("Content-Type", "application/json")
            .build();
    }

    // Utility method for basic authentication
    protected RequestSpecification authAs(String username) {
        return RestAssured.given(requestSpec)
            .auth().preemptive().basic(username, "password");
    }
}
