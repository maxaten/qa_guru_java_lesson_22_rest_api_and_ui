package demoqa.tests;

import demoqa.api.AuthorizationApi;
import demoqa.api.BooksApi;
import demoqa.extentions.WithLogin;
import demoqa.models.*;
import demoqa.pages.ProfilePage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;


public class ProfileBooksListTests extends TestBase {

    AuthorizationApi authorizationApi = new AuthorizationApi();
    LoginResponseModel loginResponse = authorizationApi.login(loginRequestModel);
    BooksApi booksApi = new BooksApi();
    ProfilePage profilePage = new ProfilePage();

    @Test
    @WithLogin
    @DisplayName("Добавление книги в профиль")
    void addBookToProfileTest() {
        step("Удаление всех книг из профиля", () -> {
            booksApi.deleteAllBooks(loginResponse);
        });

        step("Добавление книги в профиль", () -> {
            IsbnModel isbnModel = new IsbnModel();
            isbnModel.setIsbn("9781449325862");
            List<IsbnModel> isbnList = new ArrayList<>();
            isbnList.add(isbnModel);

            AddBookModel booksList = new AddBookModel();
            booksList.setUserId(loginResponse.getUserId());
            booksList.setCollectionOfIsbns(isbnList);

            booksApi.addBook(loginResponse, booksList);
        });

        step("Книга отображается в профиле", () -> {
            open("/profile");
            profilePage.checkUserName(profilePage.name)
                    .checkVisibleLinkBook()
                    .checkBookListHaveBook(profilePage.nameBook);
        });
    }

    @Test
    @WithLogin
    @DisplayName("Удаление книги из профиля")
    void deleteBookFromProfile() {
        step("Удаление всех книг из профиля", () -> {
            booksApi.deleteAllBooks(loginResponse);
        });

        step("Добавление книги в профиль", () -> {
            IsbnModel isbnModel = new IsbnModel();
            isbnModel.setIsbn("9781449325862");
            List<IsbnModel> isbnList = new ArrayList<>();
            isbnList.add(isbnModel);

            AddBookModel booksList = new AddBookModel();
            booksList.setUserId(loginResponse.getUserId());
            booksList.setCollectionOfIsbns(isbnList);

            booksApi.addBook(loginResponse, booksList);
        });

        step("Удаление книги из профиля", () -> {
            DeleteBookModel deleteBook = new DeleteBookModel();
            deleteBook.setUserId(loginResponse.getUserId());
            deleteBook.setIsbn("9781449325862");

            booksApi.deleteBook(loginResponse, deleteBook);
        });

        step("Проверка удаления книги", () -> {
            open("/profile");
            profilePage.checkUserName(profilePage.name)
                    .bookMissing(profilePage.nameBook)
                    .linkForBookMissing();
        });
    }
}