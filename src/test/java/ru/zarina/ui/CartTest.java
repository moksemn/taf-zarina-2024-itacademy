package ru.zarina.ui;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.zarina.listener.UiTestListener;
import ru.zarina.ui.page.CartPage;
import ru.zarina.ui.page.CatalogPage;
import ru.zarina.ui.page.HomePage;

@ExtendWith(UiTestListener.class)
public class CartTest extends BaseTest {
    @BeforeEach
    public void openSearchPage() {
        new HomePage().openCatalogPage();
    }


    @Test
    @DisplayName("UI:Adding a product to cart")
    public void testAddProductToCart() {
        CatalogPage catalogPage = new CatalogPage();
        String productTitle = catalogPage
                .openFirstProductInCatalog()
                .getTextProductTitle();
        String productTitleInCart = catalogPage
                .addProductToCart()
                .openCartPage()
                .getTextProductTitleInCart();
        Assertions.assertEquals(productTitle, productTitleInCart);
    }

    @Test
    @DisplayName("UI:Delete an added product from the cart")
    public void testDeleteProductFromCart() {
        String messageEmptyCart = new CatalogPage()
                .openFirstProductInCatalog()
                .addProductToCart()
                .openCartPage()
                .deleteProductFromCart()
                .getTextMessageEmptyCart();
        Assertions.assertEquals(CartPage.EXPECTED_MESSAGE_EMPTY_CART, messageEmptyCart);
    }
}
