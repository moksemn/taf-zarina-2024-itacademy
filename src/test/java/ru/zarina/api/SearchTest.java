package ru.zarina.api;

import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.zarina.api.factory.SearchRequestFactory;
import ru.zarina.api.service.SearchResponseService;
import ru.zarina.listener.ApiTestListener;

@ExtendWith(ApiTestListener.class)
public class SearchTest {
    @Test
    @DisplayName("API:GET Search correct product")
    public void testSearchCorrectProduct() {
        ValidatableResponse response = SearchResponseService
                .get(SearchRequestFactory.getQueryParamsWithCorrectProduct());
        Assertions.assertEquals(200, SearchResponseService.getStatusCode(response));
        Assertions.assertFalse(SearchResponseService.hasZeroQueries(response));
    }


    @Test
    @DisplayName("API:GET Search incorrect product")
    public void testSearchIncorrectProduct() {
        ValidatableResponse response = SearchResponseService
                .get(SearchRequestFactory.getQueryParamsWithIncorrectProduct());
        Assertions.assertEquals(200, SearchResponseService.getStatusCode(response));
        Assertions.assertTrue(SearchResponseService.hasZeroQueries(response));
    }
}
