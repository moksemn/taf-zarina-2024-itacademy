package ru.zarina.api.factory;

import ru.zarina.util.DataGenerator;

import java.io.IOException;

public class LoginRequestFactory {
    public static final String URL = "https://zarina.ru/api/auth/email/";

    public static String getBodyWithRegisteredUser() {
        try {
            System.getProperties().load(ClassLoader.getSystemResourceAsStream("user.properties"));
            return getBody(System.getProperty("email"), System.getProperty("password"));
        } catch (IOException e) {
            return getBody("", "");
        }
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
