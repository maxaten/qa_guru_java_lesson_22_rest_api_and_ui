package demoqa.tests;

import demoqa.api.AuthorizationApi;
import demoqa.models.LoginResponseModel;
import demoqa.pages.ProfilePage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static demoqa.tests.TestData.credentials;
import static io.qameta.allure.Allure.step;

public class LoginTest extends TestBase {

    ProfilePage profilePage = new ProfilePage();

    @Test
    @DisplayName("Авторизация пользователя")
    void successfulLoginTest() {
        LoginResponseModel response = AuthorizationApi.login(credentials);

        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", response.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("token", response.getToken()));
        getWebDriver().manage().addCookie(new Cookie("expires", response.getExpires()));

        step("Проверка авторизации пользователя", () -> {
            open("/profile");
            profilePage.checkUserName(profilePage.name);
        });
    }
}
