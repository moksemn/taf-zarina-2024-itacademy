package ru.zarina.api;

import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.zarina.api.factory.SearchFactory;
import ru.zarina.api.service.SearchService;
import ru.zarina.listener.ApiTestListener;

@ExtendWith(ApiTestListener.class)
public class SearchTest {
    @Test
    @DisplayName("API:GET Search correct product")
    public void testSearchCorrectProduct() {
        ValidatableResponse response = SearchService
                .get(SearchFactory.getQueryParamsWithCorrectProduct());
        Assertions.assertEquals(200, SearchService.getStatusCode(response));
        Assertions.assertFalse(SearchService.hasZeroQueries(response));
    }


    @Test
    @DisplayName("API:GET Search incorrect product")
    public void testSearchIncorrectProduct() {
        ValidatableResponse response = SearchService
                .get(SearchFactory.getQueryParamsWithIncorrectProduct());
        Assertions.assertEquals(200, SearchService.getStatusCode(response));
        Assertions.assertTrue(SearchService.hasZeroQueries(response));
    }
}
