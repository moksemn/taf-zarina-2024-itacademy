package ru.zarina.listener;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.List;

public class ApiTestListener implements BeforeEachCallback {
    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        RestAssured.replaceFiltersWith(List.of());
        RestAssured.filters(new AllureRestAssured());
    }
}


