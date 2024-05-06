package ge.tbcacad.steps.petstore;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

import static ge.tbcacad.data.constants.Constants.petstoreBaseUrl;

public class PetstoreSteps {
    public Response addNewPet(JSONObject requestBody) {
        return RestAssured.given()
                .baseUri(petstoreBaseUrl)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestBody.toString())
                .when()
                .post("/v2/pet")
                .then()
                .extract()
                .response();
    }

    public Response findPets(String status) {
        return RestAssured.given()
                .baseUri(petstoreBaseUrl)
                .queryParam("status", status)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .get("/v2/pet/findByStatus")
                .then()
                .extract()
                .response();
    }

    public Response findPetById(int id) {
        return RestAssured.given()
                .baseUri(petstoreBaseUrl)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .get("v2/pet/" + id)
                .then()
                .extract()
                .response();
    }

    public Response updateExistingPet(JSONObject requestBody) {
        return RestAssured.given()
                .baseUri(petstoreBaseUrl)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestBody.toString())
                .when()
                .put("/v2/pet")
                .then()
                .extract()
                .response();
    }
}
