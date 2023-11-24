package tests;

import io.restassured.RestAssured;
import models.CredentialsModel;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    String login = "Batman",
            password = "Qwerty123!";

    CredentialsModel credentials = new CredentialsModel(login, password);

    @BeforeAll
    static void beforeAll() {
        RestAssured.baseURI = "https://demoqa.com";
//        RestAssured.basePath = "";
    }


}
