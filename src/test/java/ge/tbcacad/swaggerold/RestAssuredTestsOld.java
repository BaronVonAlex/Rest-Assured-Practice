package ge.tbcacad.swaggerold;

import ge.tbcacad.data.dataprovider.BookDataProvider;
import ge.tbcacad.steps.swaggerSteps.SwaggerSteps;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static ge.tbcacad.data.constants.Constants.JSON_RESPONSE_BODY;
import static org.hamcrest.Matchers.*;

public class RestAssuredTestsOld {

    protected static SwaggerSteps swaggerSteps;

    @BeforeTest
    public void setUp() {
        swaggerSteps = new SwaggerSteps();
    }

    @Test
    public void bookDataTest() {
        SwaggerSteps.getListOfBooksValidation();

        ValidatableResponse response = SwaggerSteps.getListOfBooksValidation();

        SwaggerSteps.printFirstAndSecondBookInfo(response);
    }

    @Test(dataProviderClass = BookDataProvider.class, dataProvider = "bookDataProvider")
    public void eachBookTest(int index, String title, String isbn, String publishDate, int pages) {
        Response response = SwaggerSteps.getBookByISBN(isbn);

        response.then().statusCode(200).assertThat()
                .body("title", equalTo(title),
                        "isbn", equalTo(isbn),
                        "publish_date", equalTo(publishDate),
                        "pages", equalTo(pages));
    }

    @Test
    public void deleteTest() {
        Response response = SwaggerSteps.deleteBook("219812");

        response.then().statusCode(401).assertThat().body("message", equalTo("User not authorized!"));
    }

    @Test
    public void petStorePostTest() {
        Response response = SwaggerSteps.jsonRequest(JSON_RESPONSE_BODY);

        response.then().assertThat()
                .body("id", equalTo(6),
                        "petId", equalTo(76),
                        "quantity", equalTo(1),
                        "shipDate", equalTo("2024-05-04T08:06:49.817+0000"),
                        "status", equalTo("placed"),
                        "complete", equalTo(true));
    }

    @Test
    public void petStoreFormPostTest() {
        Response response = SwaggerSteps.formPostReq(12, "Test123", "Test213");

        response.then()
                .body("code", equalTo(12));

        response.then().body(contains("type"), contains("message"));

        Response response1 = SwaggerSteps.formPostReq(112312, "112312", "d1");
        response1.then().statusCode(404);
    }

    @Test
    public void petStoreUserLogTests() {
        ValidatableResponse response = RestAssured.given()
                .baseUri("https://petstore.swagger.io")
                .basePath("/v2/user/login")
                .queryParam("username", "Baron")
                .queryParam("password", "SomePassword123")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get().then();

        response.body("code", Matchers.is(200));

        String message = response.extract().body().toString();
        String sigNums = message.replaceAll("[^0-9]", "");

        if (sigNums.length() == 10) {
            System.out.println("Message contains 10 significant numbers:" + sigNums.length());
            System.out.println(sigNums);
        } else {
            System.out.println("Message does not contain 10 significant numbers - " + sigNums.length());
        }
    }

    @Test
    public void harryPotterTest() {
        Response response = RestAssured.given()
                .baseUri("https://openlibrary.org/search.json")
                .queryParam("q", "Harry Potter")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get();

        response.then().assertThat()
                .body("docs", not(empty()),
                        "docs[0].title", equalTo("Harry Potter and the Philosopher's Stone"),
                        "docs[0].author_name[0]", equalTo("J. K. Rowling"),
                        "docs[0].place", hasItems("England", "Hogwarts School of Witchcraft and Wizardry", "Platform Nine and Three-quarters"));
    }
}
