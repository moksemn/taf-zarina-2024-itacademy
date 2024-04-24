package ru.zarina.api.service;

import io.restassured.response.ValidatableResponse;
import ru.zarina.api.factory.SearchFactory;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class SearchService {
    public static ValidatableResponse get(Map<String, String> queryParams) {
        return given()
                .queryParams(queryParams)
                .headers(SearchFactory.getHeaders())
                .when()
                .get(SearchFactory.URL)
                .then();
    }

    public static int getStatusCode(ValidatableResponse response) {
        return response.extract().statusCode();
    }

    public static boolean hasZeroQueries(ValidatableResponse response) {
        return response.extract().jsonPath().getBoolean("zeroQueries");
    }
}
