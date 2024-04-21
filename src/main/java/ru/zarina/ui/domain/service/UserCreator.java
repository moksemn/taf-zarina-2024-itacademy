package ru.zarina.ui.domain.service;

import ru.zarina.ui.domain.model.User;
import ru.zarina.util.DataGenerator;

import java.io.IOException;

public class UserCreator {
    public static User withUnregisteredUser() {
        return new User(DataGenerator.getRandomEmailAddress(), DataGenerator.getRandomPassword());
    }

    public static User withEmptyPassword() {
        return new User(DataGenerator.getRandomEmailAddress(), "");
    }

    public static User withEmptyEmail() {
        return new User("", DataGenerator.getRandomPassword());
    }

    public static User withEmptyEmailAndPassword() {
        return new User("", "");
    }

    public static User withRegisteredUser() {
        try {
            System.getProperties().load(ClassLoader.getSystemResourceAsStream("user.properties"));
            return new User(System.getProperty("email"), System.getProperty("password"));
        } catch (IOException e) {
            return new User("", "");
        }
    }
}
