package ge.tbcacad.bookstore;

import ge.tbcacad.steps.bookstore.BooksSteps;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static ge.tbcacad.data.constants.Constants.BOOK_ONE_EXP_AUTH;
import static ge.tbcacad.data.constants.Constants.BOOK_TWO_EXP_AUTH;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

public class BookstoreTests {

    protected static BooksSteps booksSteps;

    @BeforeTest
    public void setUp() {
        booksSteps = new BooksSteps();
    }

    @Test
    public void allBooksWithinPageValue() {
        Response response = BooksSteps.getAllBooks();

        response.then()
                .body("books.pages", greaterThan(1000));
    }

    @Test
    public void booksAuthors() {
        Response response = BooksSteps.getAllBooks();

        response.then()
                .body("books[0].author", equalTo(BOOK_ONE_EXP_AUTH))
                .body("books[1].author", equalTo(BOOK_TWO_EXP_AUTH));
    }
}
