package ru.zarina.ui.page;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.zarina.ui.driver.DriverSingleton;
import ru.zarina.ui.waiters.Waiters;

public class HomePage {
    private static final String URL = "https://zarina.ru/";
    @FindBy(css = ".ui-button.ui-button-wide.ui-button-dark")
    private WebElement confirmCityBtn;
    @FindBy(css = "div.popmechanic-close")
    private WebElement closeAdvertisingBtn;
    @FindBy(css = ".cookies-info__apply>button:nth-child(1)")
    private WebElement acceptCookiesBtn;
    @FindBy(css = ".header__icons-item_link")
    private WebElement loginBtn;
    @FindBy(css = ".header__icons-item.parent-loupe-black")
    private WebElement searchBtn;
    @FindBy(css = ".header__icons-item.mobile--item>div")
    private WebElement cartBtn;
    @FindBy(css = ".header__icons>div:nth-child(4)>a")
    private WebElement favoritesBtn;
    @FindBy(css = ".header__burger")
    private WebElement catalogMenuBtn;
    @FindBy(xpath = "(//div[@class=\"menu__item-title\"])[1]/a")
    private WebElement firstSectionInCatalogMenuBtn;
    private WebDriver driver;

    public HomePage() {
        this.driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }
    private static final Logger logger = LogManager.getLogger();

    @Step("Открытие домашней страницы")
    public HomePage openHomePage() {
        logger.info("Open home page");
        driver.navigate().to(URL);
        return this;
    }

    @Step("Закрытие рекламы")
    public HomePage closeAdvertising() {
        try {
            Waiters.fluentWait(closeAdvertisingBtn).click();
            logger.info("Close advertising");
        } catch (TimeoutException e) {
            logger.info("Advertisement was not shown");
        }
        return this;
    }

    @Step("Подтверждение города")
    public HomePage confirmCity() {
        logger.info("Confirm city");
        confirmCityBtn.click();
        return this;
    }

    @Step("Принятие Cookies")
    public HomePage acceptCookies() {
        logger.info("Accept cookies");
        acceptCookiesBtn.click();
        return this;
    }

    @Step("Открытие страницы входа")
    public LoginPage openLoginPage() {
        logger.info("Open login page");
        Waiters.waitForElementToBeClickable(loginBtn).click();
        return new LoginPage();
    }

    @Step("Открытие страницы поиска")
    public SearchPage openSearchPage() {
        logger.info("Open search page");
        searchBtn.click();
        return new SearchPage();
    }

    @Step("Открытие корзины")
    public CartPage openCartPage() {
        logger.info("Open cart page");
        cartBtn.click();
        return new CartPage();
    }

    @Step("Открытие избранного")
    public FavoritesPage openFavoritesPage() {
        logger.info("Open favorites page");
        favoritesBtn.click();
        return new FavoritesPage();
    }

    @Step("Открытие каталога")
    public CatalogPage openCatalogPage() {
        logger.info("Open catalog page");
        catalogMenuBtn.click();
        Waiters.waitForElementToBeClickable(firstSectionInCatalogMenuBtn).click();
        return new CatalogPage();
    }

}
