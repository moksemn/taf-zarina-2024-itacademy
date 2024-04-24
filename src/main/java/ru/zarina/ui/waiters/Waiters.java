package ru.zarina.ui.waiters;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.zarina.ui.driver.DriverSingleton;

import java.time.Duration;

public class Waiters {
    private static final int TIME_OUT_SECONDS = 10;

    public static WebElement waitForElementToBeClickable(WebElement webElement) {
        return new WebDriverWait(DriverSingleton.getDriver(), Duration.ofSeconds(TIME_OUT_SECONDS)).until(ExpectedConditions
                .elementToBeClickable(webElement));
    }

    public static WebElement waitForVisibilityOfElement(WebElement webElement) {
        return new WebDriverWait(DriverSingleton.getDriver(), Duration.ofSeconds(TIME_OUT_SECONDS)).until(ExpectedConditions
                .visibilityOf(webElement));
    }

    public static WebElement fluentWait(WebElement element) {
        Wait<WebDriver> waiter = new FluentWait<>(DriverSingleton.getDriver())
                .withTimeout(Duration.ofSeconds(35))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(ElementClickInterceptedException.class, NoSuchElementException.class);
        return waiter.until(ExpectedConditions.elementToBeClickable(element));
    }
}
