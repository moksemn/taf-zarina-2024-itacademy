package ru.zarina.ui;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.zarina.ui.domain.model.Product;
import ru.zarina.ui.domain.service.ProductCreator;
import ru.zarina.listener.UiTestListener;
import ru.zarina.ui.page.HomePage;
import ru.zarina.ui.page.SearchPage;

import java.util.List;

@ExtendWith(UiTestListener.class)
public class SearchTest extends BaseTest {
    @BeforeEach
    public void openSearchPage() {
        new HomePage().openSearchPage();
    }

    @Test
    @DisplayName("UI:Search with incorrect product")
    public void testSearchIncorrectProduct() {
        String searchErrorMessage = new SearchPage()
                .search(ProductCreator.withIncorrectProduct())
                .getTextSearchErrorMessage();
        Assertions.assertEquals(SearchPage.EXPECTED_SEARCH_ERROR_MESSAGE, searchErrorMessage);
    }

    @Test
    @DisplayName("UI:Search with correct product")
    public void testSearchCorrectProduct() {
        Product product = ProductCreator.withCorrectProduct();
        List<String> productsName = new SearchPage()
                .search(product)
                .getProductsNames();
        Assertions.assertTrue(productsName.stream().allMatch(x -> x.contains(product.getName())));
    }
}
