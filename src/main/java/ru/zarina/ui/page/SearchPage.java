package ru.zarina.ui.page;

import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.zarina.ui.domain.model.Product;
import ru.zarina.ui.driver.DriverSingleton;
import ru.zarina.ui.waiters.Waiters;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class SearchPage {
    public static final String EXPECTED_SEARCH_ERROR_MESSAGE = "По этому запросу ничего не находится.";
    @FindBy(css = "input.digi-search-form__input")
    private WebElement inputSearch;
    @FindBy(css = "div.digi-product__label")
    private List<WebElement> productsName;
    @FindBy(xpath = "//div[@class=\"digi-empty\"]/p[1]")//(css = "div.digi-empty>p:nth-child(1)")
    private WebElement searchErrorMessage;
    @FindBy(css = "h1.digi-category__title_slim")
    private WebElement titleSuccessfulSearch;
    @FindBy(xpath = "//button[@class=\"digi-search-form__submit\"]")
    private WebElement submitSearchBtn;
    private WebDriver driver;

    public SearchPage() {
        this.driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }

    @Step("Поиск товара")
    public SearchPage search(Product product) {
        inputSearch.sendKeys(product.getName(), Keys.ENTER);
        return this;
    }

    @Step("Получение сообщения об отсутсвии найденных товаров")
    public String getTextSearchErrorMessage() {
        return Waiters.waitForVisibilityOfElement(searchErrorMessage).getText();
    }

    @Step("Получение списка названий найденных товаров")
    public List<String> getProductsNames() {
        Waiters.waitForVisibilityOfElement(titleSuccessfulSearch).isDisplayed();
        return productsName.stream().map(x -> x.getText().toLowerCase()).collect(Collectors.toList());
    }
}
