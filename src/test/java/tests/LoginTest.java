package tests;

import api.AuthorizationApi;
import models.LoginResponseModel;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class LoginTest extends TestBase {

    @Test
    void successfulLoginTest() {

        LoginResponseModel response = AuthorizationApi.login(credentials);


        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", response.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("token", response.getToken()));
        getWebDriver().manage().addCookie(new Cookie("expires", response.getExpires()));

        open("/profile");
        $("#userName-value").shouldHave(text(credentials.getUserName()));


    }

}
