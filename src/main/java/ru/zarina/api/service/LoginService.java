package ru.zarina.api.service;

import io.restassured.response.ValidatableResponse;
import ru.zarina.api.factory.LoginFactory;
import ru.zarina.util.Decoder;

import static io.restassured.RestAssured.given;

public class LoginService {
    public static ValidatableResponse post(String body) {
        return given()
                .body(body)
                .headers(LoginFactory.getHeaders())
                .when()
                .post(LoginFactory.URL)
                .then();
    }

    public static int getStatusCode(ValidatableResponse response) {
        return response.extract().statusCode();
    }

    public static String getBody(ValidatableResponse response) {
        return Decoder.decodedJsonResponseBody(response);
    }

    public static String getUserId(ValidatableResponse response) {
        return response.extract().jsonPath().getString("user.id");
    }
}
