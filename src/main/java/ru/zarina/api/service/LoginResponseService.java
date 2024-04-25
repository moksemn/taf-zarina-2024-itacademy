package ru.zarina.api.service;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import ru.zarina.api.factory.LoginRequestFactory;
import ru.zarina.util.Decoder;

import static io.restassured.RestAssured.given;

public class LoginResponseService {
    public static final String EXPECTED_BODY_EMPTY_EMAIL = "[{\"field_name\":\"email\",\"description\":\"Введите Email\"}]";
    public static final String EXPECTED_BODY_INVALID_DATA = "{\"message\":\"Неверный email или пароль\"}";
    public static final String EXPECTED_BODY_EMPTY_PASSWORD = "[{\"field_name\":\"password\",\"description\":\"Введите пароль\"}]";
    public static final String EXPECTED_BODY_EMPTY_DATA = "[{\"field_name\":\"email\",\"description\":\"Введите Email\"},{\"field_name\":\"password\",\"description\":\"Введите пароль\"}]";
    public static final String EXPECTED_BODY_INVALID_EMAIL_FORMAT = "[{\"field_name\":\"email\",\"description\":\"Неверный формат Email\"}]";
    public static final String EXPECTED_USER_ID = "2214182";
    public static ValidatableResponse post(String body) {
        return given()
                .body(body)
                .contentType(ContentType.JSON)
                .when()
                .post(LoginRequestFactory.URL)
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
