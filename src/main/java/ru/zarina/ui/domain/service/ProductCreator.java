package ru.zarina.ui.domain.service;

import ru.zarina.ui.domain.model.Product;
import ru.zarina.util.DataGenerator;

public class ProductCreator {
    public static Product withCorrectProduct() {
        return new Product(DataGenerator.getRandomCorrectProduct());
    }

    public static Product withIncorrectProduct() {
        return new Product(DataGenerator.getRandomString(14));
    }
}
