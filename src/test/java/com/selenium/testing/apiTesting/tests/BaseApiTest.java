package com.selenium.testing.apiTesting.tests;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeSuite;

public class BaseApiTest {

    public RequestSpecification authAs(String username) {
        return RestAssured.given()
            .baseUri("http://localhost")
            .port(7081)
            .auth().preemptive().basic(username, "password")
            .header("Content-Type", "application/json");
    }

    @BeforeSuite
    public void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 7081;
    }
}
