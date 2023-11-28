package demoqa.tests;

import demoqa.api.AuthorizationApi;
import demoqa.api.BooksApi;
import demoqa.models.AddBookModel;
import demoqa.models.DeleteBookModel;
import demoqa.models.IsbnModel;
import demoqa.models.LoginResponseModel;
import demoqa.pages.ProfilePage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static demoqa.tests.TestData.credentials;
import static io.qameta.allure.Allure.step;


public class ProfileBooksListTests extends TestBase {

    ProfilePage profilePage = new ProfilePage();

    @Test
    @DisplayName("Добавление книги в профиль")
    void addBookToProfileTest() {
        LoginResponseModel loginResponse = AuthorizationApi.login(credentials);

        step("Удаление всех книг из профиля", () -> {
            BooksApi.deleteAllBooks(loginResponse);
        });

        step("Добавление книги в профиль", () -> {
            IsbnModel isbnModel = new IsbnModel();
            isbnModel.setIsbn("9781449325862");
            List<IsbnModel> isbnList = new ArrayList<>();
            isbnList.add(isbnModel);

            AddBookModel booksList = new AddBookModel();
            booksList.setUserId(loginResponse.getUserId());
            booksList.setCollectionOfIsbns(isbnList);

            BooksApi.addBook(loginResponse, booksList);
        });


        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", loginResponse.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("token", loginResponse.getToken()));
        getWebDriver().manage().addCookie(new Cookie("expires", loginResponse.getExpires()));

        step("Книга отображается в профиле", () -> {
            open("/profile");
            profilePage.checkUserName(profilePage.name)
                    .checkVisibleLinkBook()
                    .checkBookListHaveBook(profilePage.nameBook);
        });
    }

    @Test
    @DisplayName("Удаление книги из профиля")
    void deleteBookFromProfile() {
        LoginResponseModel loginResponse = AuthorizationApi.login(credentials);

        step("Удаление всех книг из профиля", () -> {
            BooksApi.deleteAllBooks(loginResponse);
        });

        step("Добавление книги в профиль", () -> {
            IsbnModel isbnModel = new IsbnModel();
            isbnModel.setIsbn("9781449325862");
            List<IsbnModel> isbnList = new ArrayList<>();
            isbnList.add(isbnModel);

            AddBookModel booksList = new AddBookModel();
            booksList.setUserId(loginResponse.getUserId());
            booksList.setCollectionOfIsbns(isbnList);

            BooksApi.addBook(loginResponse, booksList);
        });

        step("Удаление книги из профиля", () -> {
            DeleteBookModel deleteBook = new DeleteBookModel();
            deleteBook.setUserId(loginResponse.getUserId());
            deleteBook.setIsbn("9781449325862");

            BooksApi.deleteBook(loginResponse, deleteBook);
        });

        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", loginResponse.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("token", loginResponse.getToken()));
        getWebDriver().manage().addCookie(new Cookie("expires", loginResponse.getExpires()));

        step("Проверка удаления книги", () -> {
            open("/profile");
            profilePage.checkUserName(profilePage.name)
                    .bookMissing(profilePage.nameBook)
                    .linkForBookMissing();
        });
    }
}
