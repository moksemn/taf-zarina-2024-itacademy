package ru.zarina.ui;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.zarina.listener.UiTestListener;
import ru.zarina.ui.page.CatalogPage;
import ru.zarina.ui.page.FavoritesPage;
import ru.zarina.ui.page.HomePage;

@ExtendWith(UiTestListener.class)
public class FavoritesTest extends BaseTest {
    @BeforeEach
    public void openCatalogPage() {
        new HomePage().openCatalogPage();
    }

    @Test
    @DisplayName("UI:Adding a product to favorites")
    public void testAddProductToFavorites() {
        CatalogPage catalogPage = new CatalogPage();
        String productTitle = catalogPage
                .openFirstProductInCatalog()
                .getTextProductTitle();
        String productTitleInFavorites = catalogPage
                .addProductToFavorites()
                .openFavoritesPage()
                .getTextProductTitleInFavorites();
        Assertions.assertEquals(productTitle, productTitleInFavorites);
    }

    @Test
    @DisplayName("UI:Delete an added product from the favorites")
    public void testDeleteProductFromFavorites() {
        String messageEmptyFavorites = new CatalogPage()
                .openFirstProductInCatalog()
                .addProductToFavorites()
                .openFavoritesPage()
                .deleteProductInFavorites()
                .getTextMessageEmptyFavorites();
        Assertions.assertEquals(FavoritesPage.EXPECTED_MESSAGE_EMPTY_FAVORITES, messageEmptyFavorites);
    }
}
