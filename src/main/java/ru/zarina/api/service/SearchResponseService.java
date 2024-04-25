package ru.zarina.api.service;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import ru.zarina.api.factory.SearchRequestFactory;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class SearchResponseService {
    public static ValidatableResponse get(Map<String, String> queryParams) {
        return given()
                .queryParams(queryParams)
                .contentType(ContentType.JSON)
                .when()
                .get(SearchRequestFactory.URL)
                .then();
    }

    public static int getStatusCode(ValidatableResponse response) {
        return response.extract().statusCode();
    }

    public static boolean hasZeroQueries(ValidatableResponse response) {
        return response.extract().jsonPath().getBoolean("zeroQueries");
    }
}
