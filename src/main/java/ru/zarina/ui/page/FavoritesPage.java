package ru.zarina.ui.page;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.zarina.ui.driver.DriverSingleton;
import ru.zarina.ui.waiters.Waiters;

public class FavoritesPage {
    public static final String EXPECTED_MESSAGE_EMPTY_FAVORITES = "В избранном нет товаров. Вам могут понравиться товары в категории Одежда.";
    @FindBy(css = "div.catalog__product-title>a")
    private WebElement productTitleInFavorites;
    @FindBy(xpath = "//div[@class=\"favorites__title favorites__title--left\"]/h1")
    private WebElement favoritesTitle;
    @FindBy(xpath = "//div[@class=\"catalog__product-favorites active\"]")
    private WebElement deleteProductFromFavoritesBtn;
    @FindBy(css = ".plainText>p")
    private WebElement messageEmptyFavorites;
    private WebDriver driver;

    public FavoritesPage() {
        this.driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }
    private static final Logger logger = LogManager.getLogger();

    @Step("Получение названия товара в избранном")
    public String getTextProductTitleInFavorites() {
        logger.info("Get text product title in favorites");
        return Waiters.waitForVisibilityOfElement(productTitleInFavorites).getText();
    }

    @Step("Удаление товара из избранного")
    public FavoritesPage deleteProductFromFavorites() {
        logger.info("Delete product from favorites");
        Waiters.waitForVisibilityOfElement(deleteProductFromFavoritesBtn).isDisplayed();
        new Actions(driver)
                .moveToElement(deleteProductFromFavoritesBtn).click()
                .perform();
        return this;
    }

    @Step("Получение сообщения об отсутствии товаров в избранном")
    public String getTextMessageEmptyFavorites() {
        logger.info("Get text message empty favorites");
        return Waiters.waitForVisibilityOfElement(messageEmptyFavorites).getText();
    }
}
