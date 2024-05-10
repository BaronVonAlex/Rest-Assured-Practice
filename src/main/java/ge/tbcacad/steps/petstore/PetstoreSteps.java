package ge.tbcacad.steps.petstore;

import ge.tbcacad.data.models.petstore.request.AddNewPetRequest;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.File;

import static ge.tbcacad.data.constants.Constants.petstoreBaseUrl;

public class PetstoreSteps {
    @Step
    public Response addNewPet(AddNewPetRequest requestBody) {
        return RestAssured.given()
                .baseUri(petstoreBaseUrl)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/v2/pet")
                .then()
                .extract()
                .response();
    }

    @Step
    public Response updateExistingPet(AddNewPetRequest requestBody) {
        return RestAssured.given()
                .baseUri(petstoreBaseUrl)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestBody)
                .when()
                .put("/v2/pet")
                .then()
                .extract()
                .response();
    }

    @Step
    public Response findPetsByStatus(String status) {
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

    @Step
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

    @Step
    public Response uploadImage(int id) {
        File file = new File("9eb83d4058c144401c5ea2596e658dbf.png");

        return RestAssured.given()
                .baseUri(petstoreBaseUrl)
                .accept(ContentType.JSON)
                .contentType(ContentType.MULTIPART)
                .multiPart("file", file, "image/img")
                .formParam("additionalMetadata", "Baron")
                .when()
                .post("/v2/pet/" + id + "/uploadImage")
                .then()
                .extract()
                .response();
    }
}
