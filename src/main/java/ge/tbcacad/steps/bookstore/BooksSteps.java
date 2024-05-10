package ge.tbcacad.steps.bookstore;

import ge.tbcacad.data.models.bookstore.response.BookstoreResponse;
import io.qameta.allure.Step;
import io.restassured.RestAssured;

import static ge.tbcacad.data.constants.Constants.bookstoreBaseUrl;
import static io.restassured.RestAssured.given;

public class BooksSteps {
    @Step
    public static BookstoreResponse getAllBooks() {
        RestAssured.baseURI = bookstoreBaseUrl;
        return given()
                .basePath("/BookStore/v1")
                .contentType("application/json")
                .accept("application/json")
                .when()
                .get("/Books")
                .then()
                .extract().body().as(BookstoreResponse.class);
    }
}
