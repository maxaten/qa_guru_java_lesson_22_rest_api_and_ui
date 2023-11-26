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
                .when()
                .post("/Account/v1/Login")
                .then()
                .statusCode(200)
                .extract()
                .as(LoginResponseModel.class);
    }
}