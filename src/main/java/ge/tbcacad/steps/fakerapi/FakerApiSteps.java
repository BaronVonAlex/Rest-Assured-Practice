package ge.tbcacad.steps.fakerapi;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class FakerApiSteps {
    @Step("Get response from Faker API")
    public Response getFakerResponse() {
        return RestAssured.given()
                .baseUri("https://fakerapi.it/api/v1")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .get("/credit_cards?_quantity=2")
                .then()
                .extract()
                .response();
    }

    @Step("Make JsonSchema Validation on our response with existing JSON FIle")
    public void validateJsonSchema(Response response) {
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("FakerJsonSchema.json"));
    }
}
