package ru.zarina.ui.page;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.zarina.ui.driver.DriverSingleton;
import ru.zarina.ui.waiting.Waiting;

public class FavoritesPage {
    public static final String EXPECTED_MESSAGE_EMPTY_FAVORITES = "В избранном нет товаров. Вам могут понравиться товары в категории Одежда.";
    @FindBy(css = "div.catalog__product-title>a")
    private WebElement productTitleInFavorites;
    @FindBy(xpath = "//div[@class=\"favorites__title favorites__title--left\"]/h1")
    private WebElement favoritesTitle;
    @FindBy(xpath = "//div[@class=\"catalog__product-favorites active\"]")
    private WebElement deleteProductInFavoritesBtn;
    @FindBy(css = ".plainText>p")
    private WebElement messageEmptyFavorites;
    private WebDriver driver;

    public FavoritesPage() {
        this.driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }

    @Step("Получение названия товара в избранном")
    public String getTextProductTitleInFavorites() {
        return Waiting.waitForVisibilityOfElement(productTitleInFavorites).getText();
    }

    @Step("Удаление товара из избранного")
    public FavoritesPage deleteProductInFavorites() {
        Waiting.waitForVisibilityOfElement(deleteProductInFavoritesBtn).isDisplayed();
        new Actions(driver)
                .moveToElement(deleteProductInFavoritesBtn).click()
                .perform();
        return this;
    }

    @Step("Получение сообщения об отсутствии товаров в избранном")
    public String getTextMessageEmptyFavorites() {
        return Waiting.waitForVisibilityOfElement(messageEmptyFavorites).getText();
    }
}
