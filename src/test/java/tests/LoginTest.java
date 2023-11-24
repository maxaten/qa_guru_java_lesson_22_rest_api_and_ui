package tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.CredentialsModel;
import models.LoginResponseModel;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class LoginTest extends TestBase {

    @Test
    void successfulLoginTest() {

        LoginResponseModel response = given()
                .body(credentials)
                .contentType(JSON)
                .when()
                .post("/Account/v1/Login")
                .then()
                .statusCode(200)
                .extract()
                .as(LoginResponseModel.class);

        System.out.println("1");

    }

}
