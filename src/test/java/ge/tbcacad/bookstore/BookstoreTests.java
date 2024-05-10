package ge.tbcacad.bookstore;

import ge.tbcacad.data.models.bookstore.response.BookstoreResponse;
import ge.tbcacad.enums.BookAuthor;
import ge.tbcacad.steps.bookstore.BooksSteps;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static ge.tbcacad.data.constants.Constants.BOOK_ASRT_MSG_1;
import static ge.tbcacad.data.constants.Constants.BOOK_ASRT_MSG_2;
import static org.hamcrest.MatcherAssert.assertThat;

public class BookstoreTests {

    protected static BooksSteps booksSteps;

    @BeforeTest
    public void setUp() {
        booksSteps = new BooksSteps();
        RestAssured.filters(new AllureRestAssured());
    }

    @Test
    public void allBooksWithinPageValue() {
        BookstoreResponse response = BooksSteps.getAllBooks();

        response.getBooks().forEach(booksItem -> {
            assertThat(BOOK_ASRT_MSG_1 + booksItem.getIsbn() + BOOK_ASRT_MSG_2, booksItem.getPages(), Matchers.greaterThan(1000));
        });
    }

    @Test
    public void booksAuthors() {
        BookstoreResponse response = BooksSteps.getAllBooks();

        assertThat(response.getBooks().get(0).getAuthor(), Matchers.is(BookAuthor.BOOK_AUTHOR_ONE.toString()));
        assertThat(response.getBooks().get(1).getAuthor(), Matchers.is(BookAuthor.BOOK_AUTHOR_TWO.toString()));
    }
}
