package tests;

import com.codeborne.selenide.Configuration;
import io.restassured.RestAssured;
import models.CredentialsModel;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    private final String login = "Batman";
    private final String password = "Qwerty123!";

    public CredentialsModel credentials = new CredentialsModel(login, password);

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        RestAssured.baseURI = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
    }
}