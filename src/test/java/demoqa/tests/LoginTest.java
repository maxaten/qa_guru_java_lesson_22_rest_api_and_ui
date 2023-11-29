package demoqa.tests;

import demoqa.extentions.WithLogin;
import demoqa.pages.ProfilePage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class LoginTest extends TestBase {
    ProfilePage profilePage = new ProfilePage();

    @Test
    @WithLogin
    @DisplayName("Авторизация пользователя")
    void successfulLoginTest() {
        step("Проверка авторизации пользователя", () -> {
            open("/profile");
            profilePage.checkUserName(profilePage.name);
        });
    }
}