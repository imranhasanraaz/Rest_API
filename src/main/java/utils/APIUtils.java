package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.Post;

public class APIUtils {
    public static Response sendPostRequest(String url, Post post) {

        return RestAssured.given()
                .contentType("application/json")
                .body(post)
                .post(url);
    }

    public static Response sendGetRequest(String url) {
        return RestAssured.
                when().
                get(url);
    }
}