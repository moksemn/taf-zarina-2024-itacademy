package ru.zarina.ui.page;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.zarina.ui.driver.DriverSingleton;
import ru.zarina.ui.waiters.Waiters;

public class CartPage {
    public static final String EXPECTED_MESSAGE_EMPTY_CART = "В корзине нет товаров!\nПРОДОЛЖИТЬ ШОППИНГ";
    @FindBy(css = ".basket__item-title>a")
    private WebElement productTitleInCart;
    @FindBy(xpath = "//div[@class=\"basket__item-button grey--button\"]")
    private WebElement deleteProductFromCartBtn;
    @FindBy(css = "div.basket__empty")
    private WebElement messageEmptyCart;

    private WebDriver driver;

    public CartPage() {
        this.driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }

    @Step("Получение названия товара в корзине")
    public String getTextProductTitleInCart() {
        return Waiters.waitForVisibilityOfElement(productTitleInCart).getText();
    }

    @Step("Удаление товара из корзины")
    public CartPage deleteProductFromCart() {
        Waiters.waitForElementToBeClickable(deleteProductFromCartBtn).click();
        return this;
    }

    @Step("Получение сообщения об отсутствии товаров в корзине")
    public String getTextMessageEmptyCart() {
        return Waiters.waitForVisibilityOfElement(messageEmptyCart).getText();
    }
}
