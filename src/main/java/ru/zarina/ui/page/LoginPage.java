package ru.zarina.ui.page;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.zarina.ui.domain.model.User;
import ru.zarina.ui.driver.DriverSingleton;
import ru.zarina.ui.waiters.Waiters;

public class LoginPage {
    public final static String EXPECTED_LOGIN_ERROR_MESSAGE = "Неправильный логин или пароль";
    public final static String EXPECTED_PASSWORD_ERROR_MESSAGE = "Введите пароль";
    public final static String EXPECTED_EMAIL_ERROR_MESSAGE = "Введите Email";
    public final static String EXPECTED_TITLE_SUCCESSFUL_LOGIN = "Мой аккаунт";
    @FindBy(xpath = "//input[@type=\"email\"]")
    private WebElement inputEmail;
    @FindBy(xpath = "//input[@type=\"password\"]")
    private WebElement inputPassword;
    @FindBy(xpath = "//button[@type=\"submit\"]")
    private WebElement signInBtn;
    @FindBy(css = ".form__auth-error")
    private WebElement loginErrorMessage;
    @FindBy(css = ".z-form.form__auth>div:nth-child(1)>span")
    private WebElement emailErrorMessage;
    @FindBy(css = ".ui-field.ui-has-password.ui-invalid>.ui-error")
    private WebElement passwordErrorMessage;
    @FindBy(css = "div.profile__title-content>h1")
    private WebElement titleSuccessfulLogin;
    @FindBy(css = ".header__icons-item_link")
    private WebElement profileBtn;
    private WebDriver driver;

    public LoginPage() {
        this.driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }

    @Step("Ввод данных в форму авторизации и нажатие кнопки войти")
    public LoginPage login(User user) {
        inputEmail.sendKeys(user.getEmail());
        inputPassword.sendKeys(user.getPassword());
        signInBtn.click();
        return this;
    }

    @Step("Получение сообщения о некоректности введенных данных")
    public String getTextLoginErrorMessage() {
        return Waiters.waitForVisibilityOfElement(loginErrorMessage).getText();
    }

    @Step("Получение сообщения об отсутствии адреса электронной почты")
    public String getTextEmailErrorMessage() {
        return Waiters.waitForVisibilityOfElement(emailErrorMessage).getText();
    }

    @Step("Получение сообщения об отсутствии пароля")
    public String getTextPasswordErrorMessage() {
        return Waiters.waitForVisibilityOfElement(passwordErrorMessage).getText();
    }

    @Step("Получение заголовка успешной авторизации")
    public String getTextTitleSuccessfulLogin() {
        return Waiters.waitForVisibilityOfElement(titleSuccessfulLogin).getText();
    }

    @Step("Вход в профиль пользователя")
    public LoginPage openProfile() {
        Waiters.waitForVisibilityOfElement(profileBtn).click();
        return this;
    }
}
