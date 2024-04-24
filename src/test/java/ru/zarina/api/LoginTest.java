package ru.zarina.api;

import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.zarina.api.factory.LoginFactory;
import ru.zarina.api.service.LoginService;
import ru.zarina.listener.ApiTestListener;

@ExtendWith(ApiTestListener.class)
public class LoginTest {
    @Test
    @DisplayName("API:POST Unregistered user login")
    public void testUnregisteredUserLogin() {
        ValidatableResponse response = LoginService.
                post(LoginFactory.getBodyWithUnregisteredUser());
        Assertions.assertEquals(404, LoginService.getStatusCode(response));
        Assertions.assertEquals(LoginFactory.EXPECTED_BODY_INVALID_DATA, LoginService.getBody(response));
    }

    @Test
    @DisplayName("API:POST Registered user login ")
    public void testRegisteredUserLogin() {
        ValidatableResponse response = LoginService.
                post(LoginFactory.getBodyWithRegisteredUser());
        Assertions.assertEquals(200, LoginService.getStatusCode(response));
        Assertions.assertEquals(LoginFactory.EXPECTED_USER_ID, LoginService.getUserId(response));
    }

    @Test
    @DisplayName("API:POST Login with empty password field")
    public void testLoginEmptyPasswordField() {
        ValidatableResponse response = LoginService
                .post(LoginFactory.getBodyWithEmptyPassword());
        Assertions.assertEquals(422, LoginService.getStatusCode(response));
        Assertions.assertEquals(LoginFactory.EXPECTED_BODY_EMPTY_PASSWORD, LoginService.getBody(response));
    }

    @Test
    @DisplayName("API:POST Login with empty email field")
    public void testLoginEmptyEmailField() {
        ValidatableResponse response = LoginService
                .post(LoginFactory.getBodyWithEmptyEmail());
        Assertions.assertEquals(422, LoginService.getStatusCode(response));
        Assertions.assertEquals(LoginFactory.EXPECTED_BODY_EMPTY_EMAIL, LoginService.getBody(response));
    }

    @Test
    @DisplayName("API:POST Login with empty email and password fields")
    public void testLoginEmptyEmailAndPasswordFields() {
        ValidatableResponse response = LoginService
                .post(LoginFactory.getBodyWithEmptyEmailAndPassword());
        Assertions.assertEquals(422, LoginService.getStatusCode(response));
        Assertions.assertEquals(LoginFactory.EXPECTED_BODY_EMPTY_DATA, LoginService.getBody(response));
    }

    @Test
    @DisplayName("API:POST Login with invalid email format")
    public void testLoginInvalidEmailFormat() {
        ValidatableResponse response = LoginService
                .post(LoginFactory.getBodyWithInvalidEmailFormat());
        Assertions.assertEquals(422, LoginService.getStatusCode(response));
        Assertions.assertEquals(LoginFactory.EXPECTED_BODY_INVALID_EMAIL_FORMAT, LoginService.getBody(response));
    }

    @Test
    @DisplayName("API:POST Login with invalid password format")
    public void testLoginInvalidPasswordFormat() {
        ValidatableResponse response = LoginService
                .post(LoginFactory.getBodyWithInvalidPasswordFormat());
        Assertions.assertEquals(404, LoginService.getStatusCode(response));
        Assertions.assertEquals(LoginFactory.EXPECTED_BODY_INVALID_DATA, LoginService.getBody(response));
    }

    @Test
    @DisplayName("API:POST Login with invalid email and password format")
    public void testLoginInvalidEmailAndPasswordFormat() {
        ValidatableResponse response = LoginService
                .post(LoginFactory.getBodyWithInvalidEmailAndPasswordFormat());
        Assertions.assertEquals(422, LoginService.getStatusCode(response));
        Assertions.assertEquals(LoginFactory.EXPECTED_BODY_INVALID_EMAIL_FORMAT, LoginService.getBody(response));
    }
}

