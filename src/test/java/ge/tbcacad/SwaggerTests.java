package ge.tbcacad;

import ge.tbcacad.data.dataprovider.BookDataProvider;
import ge.tbcacad.steps.swaggerSteps.SwaggerSteps;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static ge.tbcacad.data.constants.Constants.*;
import static org.hamcrest.Matchers.*;

public class SwaggerTests {

    protected static SwaggerSteps swaggerSteps;
    private SoftAssert softAssert;

    @BeforeTest
    public void setUp() {
        swaggerSteps = new SwaggerSteps();
        softAssert = new SoftAssert();
    }

    @Test
    public void bookDataTest() {
        SwaggerSteps.getListOfBooksValidation();

        ValidatableResponse response = SwaggerSteps.getListOfBooksValidation();

        SwaggerSteps.printFirstAndSecondBookInfo(response);
    }

    @Test(dataProviderClass = BookDataProvider.class, dataProvider = "isbnProvider")
    public void eachBookTest(String isbn) {
        Response response = SwaggerSteps.getBookByISBN(isbn);

        softAssert.assertEquals(response.getStatusCode(), 200);

        softAssert.assertTrue(response.getBody().asString().contains(TITLE), TITLE_ERR_MSG);
        softAssert.assertTrue(response.getBody().asString().contains(ISBN), ISBN_ERR_MSG);
        softAssert.assertTrue(response.getBody().asString().contains(PUB_DATE), PUB_DATE_ERR_MSG);
        softAssert.assertTrue(response.getBody().asString().contains(PAGES), PAGES_ERR_MSG);
    }

    @Test
    public void deleteTest() {
        Response response = SwaggerSteps.deleteBook("219812");
        softAssert.assertEquals(response.getStatusCode(), 401, STATUS_CODE_ERR_MSG);
        softAssert.assertTrue(response.getBody().asString().contains("User not authorized!"), RESPONSE_ERR_MSG);
    }

    @Test
    public void petStorePostTest() {
        Response response = SwaggerSteps.jsonRequest(JSON_RESPONSE_BODY);

        softAssert.assertTrue(response.getBody().asString().contains("\"id\":0"), ID_ERR_MSG);
        softAssert.assertTrue(response.getBody().asString().contains("\"petId\":0"), PET_ID_ERR_MSG);
        softAssert.assertTrue(response.getBody().asString().contains("\"quantity\":0"), QUANT_ERR_MSG);
        softAssert.assertTrue(response.getBody().asString().contains("\"shipDate\":\"0\""), DATE_ERR_MSG);
        softAssert.assertTrue(response.getBody().asString().contains("\"status\":\"2024-05-03T10:42:46.645Z\""), STATUS_ERR_MSG);
        softAssert.assertTrue(response.getBody().asString().contains("\"complete\":true"), COMPLETION_STATUS_ERR_MSG);
    }

    @Test
    public void petStoreFormPostTest() {
        Response response = SwaggerSteps.formPostReq(12, "Test123", "Test213");
        softAssert.assertTrue(response.getBody().asString().contains("code"), CODE_ERR_MSG);
        softAssert.assertTrue(response.getBody().asString().contains("type"), TYPE_ERR_MSG);
        softAssert.assertTrue(response.getBody().asString().contains("message"), MSG_ERR_MSG);

        Response response1 = SwaggerSteps.formPostReq(112312, "112312", "d1");
        softAssert.assertEquals(response1.getStatusCode(), 404, ERR_404_MSG);
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

        response.then().assertThat().body("docs", not(empty()));

        response.then().assertThat().body("docs[0].title", equalTo("Harry Potter and the Philosopher's Stone"));
        response.then().assertThat().body("docs[0].author_name[0]", equalTo("J. K. Rowling"));
        response.then().assertThat().body("docs[0].place", hasItems("England", "Hogwarts School of Witchcraft and Wizardry", "Platform Nine and Three-quarters"));
    }

    @AfterTest
    private void tearDown() {
        softAssert.assertAll();
    }
}
