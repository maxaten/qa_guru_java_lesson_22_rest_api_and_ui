package demoqa.api;

import demoqa.models.LoginRequestModel;
import demoqa.models.LoginResponseModel;

import static io.restassured.RestAssured.given;
import static demoqa.api.spec.LoginUserSpec.loginRequestSpec;
import static demoqa.api.spec.LoginUserSpec.loginResponseSpec;

public class AuthorizationApi {
    public LoginResponseModel login(LoginRequestModel loginRequestModel) {
        return given(loginRequestSpec)
                .body(loginRequestModel)
                .when()
                .post("/Account/v1/Login")
                .then()
                .spec(loginResponseSpec)
                .statusCode(200)
                .extract().as(LoginResponseModel.class);
    }
}