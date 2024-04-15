package ru.zarina.ui.page;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.zarina.ui.driver.DriverSingleton;
import ru.zarina.ui.waiting.Waiting;

public class CatalogPage {
    @FindBy(css = ".catalog__title>h1")
    private WebElement catalogTitle;
    @FindBy(xpath = "(//div[@class=\"catalog__product-content\"])[1]//a")
    private WebElement firstProductInCatalog;
    @FindBy(xpath = "//h1[@itemprop=\"name\"]")
    private WebElement productTitle;
    @FindBy(xpath = "//button[@class=\"ui-button ui-button-wide ui-button-dark\"]")
    private WebElement addProductToCartBtn;
    @FindBy(xpath = "//div[@class=\"product__favorites\"]")
    private WebElement addProductToFavoritesBtn;
    private WebDriver driver;

    public CatalogPage() {
        this.driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }

    @Step("Открытие первого товара в каталоге")
    public CatalogPage openFirstProductInCatalog() {
        Waiting.waitForVisibilityOfElement(catalogTitle).isDisplayed();
        Waiting.waitForElementToBeClickable(firstProductInCatalog).click();
        return this;
    }

    @Step("Получение названия товара в карточке продукта")
    public String getTextProductTitle() {
        return Waiting.waitForVisibilityOfElement(productTitle).getText()
                .replaceAll("[^а-яА-Яa-zA-Z\\s]", "").trim();
    }

    @Step("Добавление товара в корзину")
    public HomePage addProductToCart() {
        Waiting.waitForVisibilityOfElement(addProductToCartBtn).click();
        return new HomePage();
    }

    @Step("Добавление товара в избранное")
    public HomePage addProductToFavorites() {
        Waiting.waitForVisibilityOfElement(addProductToFavoritesBtn).click();
        return new HomePage();
    }
}
