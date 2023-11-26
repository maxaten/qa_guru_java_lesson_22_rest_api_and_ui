package tests;

import api.AuthorizationApi;
import models.CredentialsModel;
import models.LoginResponseModel;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class ProfileBooksListTests extends TestBase{


    @Test
    void addBookToProfileTest() {
        LoginResponseModel response = AuthorizationApi.login(credentials);

        given()
                .contentType(JSON)
                .header("Authorization", "Bearer " + response.getToken())
                .when()
                .get("/BookStore/v1/Books")
                .then()
                .statusCode(200)
                .extract()
                .as(LoginResponseModel.class);
    }
}
