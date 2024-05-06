package ge.tbcacad.steps.bookstore;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static ge.tbcacad.data.constants.Constants.bookstoreBaseUrl;
import static io.restassured.RestAssured.given;

public class BooksSteps {
    public static Response getAllBooks() {
        RestAssured.baseURI = bookstoreBaseUrl;
        return given()
                .basePath("/BookStore/v1")
                .contentType("application/json")
                .accept("application/json")
                .when()
                .get("/Books")
                .then()
                .extract().response();
    }
}
