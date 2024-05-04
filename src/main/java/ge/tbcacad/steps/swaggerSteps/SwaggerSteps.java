package ge.tbcacad.steps.swaggerSteps;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import java.util.List;
import java.util.Map;

public class SwaggerSteps {
    public static ValidatableResponse getListOfBooksValidation() {
        RestAssured.baseURI = "https://bookstore.toolsqa.com";
        return RestAssured.given()
                .basePath("/BookStore/v1/Books")
                .contentType("application/json")
                .accept("application/json")
                .when().get().then();
    }

    public static Response getBookByISBN(String isbn) {
        return RestAssured.given()
                .basePath("/BookStore/v1/Book")
                .queryParam("ISBN", isbn)
                .contentType("application/json")
                .accept("application/json")
                .get();
    }

    public static Response deleteBook(String userId) {
        return RestAssured.given()
                .basePath("/BookStore/v1/Books")
                .queryParam("UserId", userId)
                .delete();
    }

    public static Response jsonRequest(String reqBody) {
        RestAssured.baseURI = "https://petstore.swagger.io";
        return RestAssured.given()
                .basePath("/v2/store/order")
                .contentType(ContentType.JSON)
                .body(reqBody)
                .post();
    }

    public static Response formPostReq(int petId, String name, String status) {
        RestAssured.baseURI = "https://petstore.swagger.io";
        return RestAssured.given()
                .basePath("v2/pet/{petId}")
                .pathParam("petId", petId)
                .contentType("application/json")
                .formParam("name", name)
                .formParam("status", status)
                .when().post();
    }

    public static void printFirstAndSecondBookInfo(ValidatableResponse response) {
        JsonPath jsonPath = response.extract().jsonPath();
        List<String> isbns = jsonPath.getList("books.isbn");
        List<String> authors = jsonPath.getList("books.author");

        if (isbns.size() >= 2 && authors.size() >= 2) {
            System.out.println("First Book ISBN: " + isbns.get(0));
            System.out.println("First Book Author: " + authors.get(0));
            System.out.println("Second Book ISBN: " + isbns.get(1));
            System.out.println("Second Book Author: " + authors.get(1));
        } else {
            System.out.println("Insufficient books in the response.");
        }
    }

    public static List<Map<String, Object>> extractBooksFromResponse(ValidatableResponse response) {
        JsonPath jsonPath = response.extract().jsonPath();
        List<Map<String, Object>> books = jsonPath.getList("books");
        return books;
    }
}
