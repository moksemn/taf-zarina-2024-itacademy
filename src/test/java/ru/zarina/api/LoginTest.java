package ru.zarina.api;

import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.zarina.api.factory.LoginRequestFactory;
import ru.zarina.api.service.LoginResponseService;
import ru.zarina.listener.ApiTestListener;

@ExtendWith(ApiTestListener.class)
public class LoginTest {
    @Test
    @DisplayName("API:POST Unregistered user login")
    public void testUnregisteredUserLogin() {
        ValidatableResponse response = LoginResponseService.
                post(LoginRequestFactory.getBodyWithUnregisteredUser());
        Assertions.assertEquals(404, LoginResponseService.getStatusCode(response));
        Assertions.assertEquals(LoginResponseService.EXPECTED_BODY_INVALID_DATA, LoginResponseService.getBody(response));
    }

    @Test
    @DisplayName("API:POST Registered user login ")
    public void testRegisteredUserLogin() {
        ValidatableResponse response = LoginResponseService.
                post(LoginRequestFactory.getBodyWithRegisteredUser());
        Assertions.assertEquals(200, LoginResponseService.getStatusCode(response));
        Assertions.assertEquals(LoginResponseService.EXPECTED_USER_ID, LoginResponseService.getUserId(response));
    }

    @Test
    @DisplayName("API:POST Login with empty password field")
    public void testLoginEmptyPasswordField() {
        ValidatableResponse response = LoginResponseService
                .post(LoginRequestFactory.getBodyWithEmptyPassword());
        Assertions.assertEquals(422, LoginResponseService.getStatusCode(response));
        Assertions.assertEquals(LoginResponseService.EXPECTED_BODY_EMPTY_PASSWORD, LoginResponseService.getBody(response));
    }

    @Test
    @DisplayName("API:POST Login with empty email field")
    public void testLoginEmptyEmailField() {
        ValidatableResponse response = LoginResponseService
                .post(LoginRequestFactory.getBodyWithEmptyEmail());
        Assertions.assertEquals(422, LoginResponseService.getStatusCode(response));
        Assertions.assertEquals(LoginResponseService.EXPECTED_BODY_EMPTY_EMAIL, LoginResponseService.getBody(response));
    }

    @Test
    @DisplayName("API:POST Login with empty email and password fields")
    public void testLoginEmptyEmailAndPasswordFields() {
        ValidatableResponse response = LoginResponseService
                .post(LoginRequestFactory.getBodyWithEmptyEmailAndPassword());
        Assertions.assertEquals(422, LoginResponseService.getStatusCode(response));
        Assertions.assertEquals(LoginResponseService.EXPECTED_BODY_EMPTY_DATA, LoginResponseService.getBody(response));
    }

    @Test
    @DisplayName("API:POST Login with invalid email format")
    public void testLoginInvalidEmailFormat() {
        ValidatableResponse response = LoginResponseService
                .post(LoginRequestFactory.getBodyWithInvalidEmailFormat());
        Assertions.assertEquals(422, LoginResponseService.getStatusCode(response));
        Assertions.assertEquals(LoginResponseService.EXPECTED_BODY_INVALID_EMAIL_FORMAT, LoginResponseService.getBody(response));
    }

    @Test
    @DisplayName("API:POST Login with invalid password format")
    public void testLoginInvalidPasswordFormat() {
        ValidatableResponse response = LoginResponseService
                .post(LoginRequestFactory.getBodyWithInvalidPasswordFormat());
        Assertions.assertEquals(404, LoginResponseService.getStatusCode(response));
        Assertions.assertEquals(LoginResponseService.EXPECTED_BODY_INVALID_DATA, LoginResponseService.getBody(response));
    }

    @Test
    @DisplayName("API:POST Login with invalid email and password format")
    public void testLoginInvalidEmailAndPasswordFormat() {
        ValidatableResponse response = LoginResponseService
                .post(LoginRequestFactory.getBodyWithInvalidEmailAndPasswordFormat());
        Assertions.assertEquals(422, LoginResponseService.getStatusCode(response));
        Assertions.assertEquals(LoginResponseService.EXPECTED_BODY_INVALID_EMAIL_FORMAT, LoginResponseService.getBody(response));
    }
}

