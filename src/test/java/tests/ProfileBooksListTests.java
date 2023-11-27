package tests;

import api.AuthorizationApi;
import api.BooksApi;
import com.codeborne.selenide.Condition;
import models.AddBookModel;
import models.IsbnModel;
import models.LoginResponseModel;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;


public class ProfileBooksListTests extends TestBase {


    @Test
    void addBookToProfileTest() {
        LoginResponseModel loginResponse = AuthorizationApi.login(credentials);
        BooksApi.deleteAllBooks(loginResponse);


        IsbnModel isbnModel = new IsbnModel();
        isbnModel.setIsbn("9781449325862");
        List<IsbnModel> isbnList = new ArrayList<>();
        isbnList.add(isbnModel);

        AddBookModel booksList = new AddBookModel();
        booksList.setUserId(loginResponse.getUserId());
        booksList.setCollectionOfIsbns(isbnList);

        BooksApi.addBook(loginResponse, booksList);

        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", loginResponse.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("token", loginResponse.getToken()));
        getWebDriver().manage().addCookie(new Cookie("expires", loginResponse.getExpires()));

        open("/profile");
        $("[href='/profile?book=9781449325862']").shouldBe(visible);
        $(".rt-tbody").shouldBe(visible).shouldHave(text("Git Pocket Guide"));



    }
}
