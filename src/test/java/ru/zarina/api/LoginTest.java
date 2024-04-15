package ru.zarina.api;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.zarina.api.service.LoginService;
import ru.zarina.listener.ApiTestListener;
import ru.zarina.util.Decoder;

import java.io.IOException;

import static io.restassured.RestAssured.given;

@ExtendWith(ApiTestListener.class)
public class LoginTest {
    @Test
    @DisplayName("API:POST Unregistered user login")
    public void testUnregisteredUserLogin() {
        Response response = given()
                .body(LoginService.getBodyWithUnregisteredUser())
                .headers(LoginService.getHeaders())
                .when()
                .post(LoginService.URL);
        Assertions.assertEquals(404, response.getStatusCode());
        Assertions.assertEquals(LoginService.EXPECTED_BODY_INVALID_DATA, Decoder.decodedJsonResponseBody(response));

    }

    @Test
    @DisplayName("API:POST Registered user login ")
    public void testRegisteredUserLogin() throws IOException {
        Response response = given()
                .body(LoginService.getBodyWithRegisteredUser())
                .headers(LoginService.getHeaders())
                .when()
                .post(LoginService.URL);
        Assertions.assertEquals(200, response.getStatusCode());
        Assertions.assertEquals(LoginService.EXPECTED_USER_ID, response.jsonPath().getString("user.id"));
    }

    @Test
    @DisplayName("API:POST Login with empty password field")
    public void testLoginEmptyPasswordField() {
        Response response = given()
                .body(LoginService.getBodyWithEmptyPassword())
                .headers(LoginService.getHeaders())
                .when()
                .post(LoginService.URL);
        Assertions.assertEquals(422, response.getStatusCode());
        Assertions.assertEquals(LoginService.EXPECTED_BODY_EMPTY_PASSWORD, Decoder.decodedJsonResponseBody(response));
    }

    @Test
    @DisplayName("API:POST Login with empty email field")
    public void testLoginEmptyEmailField() {
        Response response = given()
                .body(LoginService.getBodyWithEmptyEmail())
                .headers(LoginService.getHeaders())
                .when()
                .post(LoginService.URL);
        Assertions.assertEquals(422, response.getStatusCode());
        Assertions.assertEquals(LoginService.EXPECTED_BODY_EMPTY_EMAIL, Decoder.decodedJsonResponseBody(response));
    }

    @Test
    @DisplayName("API:POST Login with empty email and password fields")
    public void testLoginEmptyEmailAndPasswordFields() {
        Response response = given()
                .body(LoginService.getBodyWithEmptyEmailAndPassword())
                .headers(LoginService.getHeaders())
                .when()
                .post(LoginService.URL);
        Assertions.assertEquals(422, response.getStatusCode());
        Assertions.assertEquals(LoginService.EXPECTED_BODY_EMPTY_DATA, Decoder.decodedJsonResponseBody(response));
    }


    @Test
    @DisplayName("API:POST Login with invalid email format")
    public void testLoginInvalidEmailFormat() {
        Response response = given()
                .body(LoginService.getBodyWithInvalidEmailFormat())
                .headers(LoginService.getHeaders())
                .when()
                .post(LoginService.URL);
        Assertions.assertEquals(422, response.getStatusCode());
        Assertions.assertEquals(LoginService.EXPECTED_BODY_INVALID_EMAIL_FORMAT, Decoder.decodedJsonResponseBody(response));
    }

    @Test
    @DisplayName("API:POST Login with invalid password format")
    public void testLoginInvalidPasswordFormat() {
        Response response = given()
                .body(LoginService.getBodyWithInvalidPasswordFormat())
                .headers(LoginService.getHeaders())
                .when()
                .post(LoginService.URL);
        Assertions.assertEquals(404, response.getStatusCode());
        Assertions.assertEquals(LoginService.EXPECTED_BODY_INVALID_DATA, Decoder.decodedJsonResponseBody(response));
    }

    @Test
    @DisplayName("API:POST Login with invalid email and password format")
    public void testLoginInvalidEmailAndPasswordFormat() {
        Response response = given()
                .body(LoginService.getBodyWithInvalidEmailAndPasswordFormat())
                .headers(LoginService.getHeaders())
                .when()
                .post(LoginService.URL);
        Assertions.assertEquals(422, response.getStatusCode());
        Assertions.assertEquals(LoginService.EXPECTED_BODY_INVALID_EMAIL_FORMAT, Decoder.decodedJsonResponseBody(response));
    }
}

