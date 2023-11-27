package api;

import models.AddBookModel;
import models.CredentialsModel;
import models.LoginResponseModel;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class BooksApi {

    public static void deleteAllBooks(LoginResponseModel loginResponse) {
        given()
                .contentType(JSON)
                .log().uri()
                .log().method()
                .header("Authorization", "Bearer " + loginResponse.getToken())
                .queryParam("UserId", loginResponse.getUserId())
                .when()
                .delete("/BookStore/v1/Books")
                .then()
                .log().body()
                .log().status()
                .statusCode(204)
                .extract();
    }


    public static void addBook(LoginResponseModel loginResponse, AddBookModel booksList) {
        given()
                .contentType(JSON)
                .log().uri()
                .log().method()
                .header("Authorization", "Bearer " + loginResponse.getToken())
                .body(booksList)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .log().body()
                .log().status()
                .statusCode(201);
//                .extract();
    }
}