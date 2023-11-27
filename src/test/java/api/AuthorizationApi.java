package api;

import models.CredentialsModel;
import models.LoginResponseModel;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class AuthorizationApi {
    public static LoginResponseModel login(CredentialsModel credentials) {
        return given()
                .body(credentials)
                .contentType(JSON)
                .log().uri()
                .log().method()
                .when()
                .post("/Account/v1/Login")
                .then()
                .log().body()
                .log().status()
                .statusCode(200)
                .extract()
                .as(LoginResponseModel.class);
    }
}