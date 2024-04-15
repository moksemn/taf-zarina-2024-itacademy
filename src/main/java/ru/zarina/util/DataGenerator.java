package ru.zarina.util;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataGenerator {
    private static Faker faker = new Faker();

    public static String getRandomEmailAddress() {
        return faker.internet().emailAddress();
    }

    public static String getRandomPassword() {
        return faker.internet().password();
    }

    public static String getRandomString(int length) {
        return faker.internet().password(length, length + 1);
    }

    public static String getRandomCorrectProduct() {
        List<String> products = new ArrayList<>(Arrays.asList("джинсы", "футболка", "платье", "сумка", "свитер"));
        return products.get(getRandomNumber(products.size() - 1));
    }

    public static int getRandomNumber(int bound) {
        return faker.random().nextInt(0, bound);
    }
    public static int getRandomNumber(){
        return getRandomNumber(Integer.MAX_VALUE-1);
    }
}
