package ru.zarina.ui;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.zarina.listener.UiTestListener;
import ru.zarina.ui.domain.service.UserCreator;
import ru.zarina.ui.page.HomePage;
import ru.zarina.ui.page.LoginPage;

@ExtendWith(UiTestListener.class)
public class LoginTest extends BaseTest {
    @BeforeEach
    public void openLoginPage() {
        new HomePage().openLoginPage();
    }

    @Test
    @DisplayName("UI:POST Registered user login")
    public void testRegisteredUserLogin() {
        String titleSuccessfulLogin = new LoginPage()
                .login(UserCreator.withRegisteredUser())
                .openProfile()
                .getTextTitleSuccessfulLogin();
        Assertions.assertEquals(LoginPage.EXPECTED_TITLE_SUCCESSFUL_LOGIN, titleSuccessfulLogin);
    }

    @Test
    @DisplayName("UI:POST Unregistered user login")
    public void testUnregisteredUserLogin() {
        String loginErrorMessage = new LoginPage()
                .login(UserCreator.withUnregisteredUser())
                .getTextLoginErrorMessage();
        Assertions.assertEquals(LoginPage.EXPECTED_LOGIN_ERROR_MESSAGE, loginErrorMessage);
    }

    @Test
    @DisplayName("UI:Login with empty password field")
    public void testLoginEmptyPassword() {
        String passwordErrorMessage = new LoginPage()
                .login(UserCreator.withEmptyPassword())
                .getTextPasswordErrorMessage();
        Assertions.assertEquals(LoginPage.EXPECTED_PASSWORD_ERROR_MESSAGE, passwordErrorMessage);
    }

    @Test
    @DisplayName("UI:Login with empty email field")
    public void testLoginEmptyEmail() {
        String emailErrorMessage = new LoginPage()
                .login(UserCreator.withEmptyEmail())
                .getTextEmailErrorMessage();
        Assertions.assertEquals(LoginPage.EXPECTED_EMAIL_ERROR_MESSAGE, emailErrorMessage);
    }

    @Test
    @DisplayName("UI:Login with empty email and password fields")
    public void testLoginEmptyEmailAndPassword() {
        LoginPage loginPage = new LoginPage()
                .login(UserCreator.withEmptyEmailAndPassword());
        Assertions.assertEquals(LoginPage.EXPECTED_EMAIL_ERROR_MESSAGE, loginPage.getTextEmailErrorMessage());
        Assertions.assertEquals(LoginPage.EXPECTED_PASSWORD_ERROR_MESSAGE, loginPage.getTextPasswordErrorMessage());
    }
}
