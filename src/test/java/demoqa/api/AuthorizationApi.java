package demoqa.api;

import demoqa.models.CredentialsModel;
import demoqa.models.LoginResponseModel;

import static io.restassured.RestAssured.given;
import static spec.LoginUserSpec.loginRequestSpec;
import static spec.LoginUserSpec.loginResponseSpec;

public class AuthorizationApi {

    public static LoginResponseModel login(CredentialsModel credentials) {
        return given(loginRequestSpec)
                .body(credentials)
                .when()
                .post("/Account/v1/Login")
                .then()
                .spec(loginResponseSpec)
                .statusCode(200)
                .extract()
                .as(LoginResponseModel.class);
    }
}