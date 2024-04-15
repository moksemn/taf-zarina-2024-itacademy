package ru.zarina.api.service;

import ru.zarina.util.DataGenerator;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginService {
    public static final String URL = "https://zarina.ru/api/auth/email/";
    public static final String EXPECTED_BODY_EMPTY_EMAIL = "[{\"field_name\":\"email\",\"description\":\"Введите Email\"}]";
    public static final String EXPECTED_BODY_INVALID_DATA = "{\"message\":\"Неверный email или пароль\"}";
    public static final String EXPECTED_BODY_EMPTY_PASSWORD = "[{\"field_name\":\"password\",\"description\":\"Введите пароль\"}]";
    public static final String EXPECTED_BODY_EMPTY_DATA = "[{\"field_name\":\"email\",\"description\":\"Введите Email\"},{\"field_name\":\"password\",\"description\":\"Введите пароль\"}]";
    public static final String EXPECTED_BODY_INVALID_EMAIL_FORMAT = "[{\"field_name\":\"email\",\"description\":\"Неверный формат Email\"}]";
    public static final String EXPECTED_USER_ID="2214182";

    public static Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("content-type", "application/json");
        return headers;
    }
    public static String getBodyWithRegisteredUser() throws IOException {
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("user.properties"));
        return getBody(System.getProperty("email"), System.getProperty("password"));
    }

    public static String getBodyWithUnregisteredUser() {
        return getBody(DataGenerator.getRandomEmailAddress(), DataGenerator.getRandomPassword());
    }

    public static String getBodyWithEmptyPassword() {
        return getBody(DataGenerator.getRandomEmailAddress(), "");
    }

    public static String getBodyWithEmptyEmail() {
        return getBody("", DataGenerator.getRandomPassword());
    }

    public static String getBodyWithEmptyEmailAndPassword() {
        return getBody("", "");
    }

    public static String getBodyWithInvalidEmailFormat() {
        return getBody(DataGenerator.getRandomNumber(), DataGenerator.getRandomPassword());
    }

    public static String getBodyWithInvalidPasswordFormat() {
        return getBody(DataGenerator.getRandomEmailAddress(), DataGenerator.getRandomNumber());
    }

    public static String getBodyWithInvalidEmailAndPasswordFormat() {
        return getBody(DataGenerator.getRandomNumber(), DataGenerator.getRandomNumber());
    }


    private static String getBody(String email, String password) {
        return String.format("{\"email\": \"%s\", \"password\": \"%s\"}", email, password);
    }

    private static String getBody(int email, String password) {
        return String.format("{\"email\": %s, \"password\": \"%s\"}", email, password);
    }

    private static String getBody(String email, int password) {
        return String.format("{\"email\": \"%s\", \"password\": %s}", email, password);
    }

    private static String getBody(int email, int password) {
        return String.format("{\"email\": %s, \"password\": %s}", email, password);
    }
}
