package ru.zarina.api;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.zarina.api.service.SearchService;
import ru.zarina.listener.ApiTestListener;

import static io.restassured.RestAssured.given;

@ExtendWith(ApiTestListener.class)
public class SearchTest {
    @Test
    @DisplayName("API:GET Search correct product")
    public void testSearchCorrectProduct() {
        Response response = given()
                .headers(SearchService.getHeaders())
                .queryParams(SearchService.getQueryParamsWithCorrectProduct())
                .when()
                .get(SearchService.URL);
        Assertions.assertEquals(200, response.getStatusCode());
        Assertions.assertFalse(response.jsonPath().getBoolean("zeroQueries"));
    }

    @Test
    @DisplayName("API:GET Search incorrect product")
    public void testSearchIncorrectProduct() {
        Response response = given()
                .headers(SearchService.getHeaders())
                .queryParams(SearchService.getQueryParamsWithIncorrectProduct())
                .when()
                .get(SearchService.URL);
        Assertions.assertEquals(200, response.getStatusCode());
        Assertions.assertTrue(response.jsonPath().getBoolean("zeroQueries"));
    }
}
