package demoqa.tests;

import demoqa.extentions.WithLogin;
import demoqa.pages.ProfilePage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;


@Tag("ui")
public class LoginTest extends TestBase {
    ProfilePage profilePage = new ProfilePage();

    @Test
    @Tag("combined")
    @DisplayName("Авторизация пользователя")
    @WithLogin
    void successfulLoginTest() {
        step("Проверка авторизации пользователя", () -> {
            open("/profile");
            profilePage.checkUserName(profilePage.name);
        });
    }
}