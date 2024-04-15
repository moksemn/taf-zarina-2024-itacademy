package ru.zarina.ui;

import org.junit.jupiter.api.BeforeEach;
import ru.zarina.ui.page.HomePage;

public class BaseTest {
    @BeforeEach
    public void openHomePage() {
        new HomePage()
                .openHomePage()
                .closeAdvertising()
                .confirmCity()
                .acceptCookies();

    }
}
