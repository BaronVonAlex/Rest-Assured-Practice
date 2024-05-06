package ge.tbcacad.petstore;

import ge.tbcacad.data.requestbody.petstore.PetstoreRequestBody;
import ge.tbcacad.steps.petstore.PetstoreSteps;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static ge.tbcacad.data.constants.Constants.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.stringContainsInOrder;
import static org.hamcrest.core.IsIterableContaining.hasItem;

public class PetstoreTests {

    protected static PetstoreSteps petstoreSteps;

    @BeforeTest
    public void setUp() {
        petstoreSteps = new PetstoreSteps();
    }

    @Test(priority = 1)
    public void validateRequest() {
        Response response = petstoreSteps.addNewPet(PetstoreRequestBody.returnPetstoreReqBody());
        response.then().statusCode(200);

        int id = response.then().extract().path(ID);

        Response responseByStatus = petstoreSteps.findPets(AVAILABLE_STATUS);

        responseByStatus.then().body(ID, hasItem(id));

        Response responseById = petstoreSteps.findPetById(id);
        responseById.then()
                .body(ID, equalTo(id))
                .body(CATEGORY_ID, equalTo(response.then().extract().path(CATEGORY_ID)))
                .body(CATEGORY_NAME, equalTo(response.then().extract().path(CATEGORY_NAME)))
                .body(PET_NAME, equalTo(response.then().extract().path(PET_NAME)))
                .body(PHOTO_URLS, equalTo(response.then().extract().path(PHOTO_URLS)))
                .body(TAGS_ID, equalTo(response.then().extract().path(TAGS_ID)))
                .body(TAGS_NAME, equalTo(response.then().extract().path(TAGS_NAME)))
                .body(STATUS, equalTo(response.then().extract().path(STATUS)));

        responseById.prettyPrint();
    }

    @Test(priority = 2)
    public void updateExistingPet() {
        Response response = petstoreSteps.updateExistingPet(PetstoreRequestBody.updatedPetReqBody());

        int id = response.then().extract().path(ID);

        Response responsePet = petstoreSteps.findPetById(id);

        responsePet.then()
                .body(PET_NAME, equalTo(response.then().extract().path(PET_NAME)))
                .body(STATUS, equalTo(response.then().extract().path(STATUS)));

        responsePet.prettyPrint();
    }

    @Test(priority = 3)
    public void uploadPetPicture() {
        Response responseInitial = petstoreSteps.addNewPet(PetstoreRequestBody.returnPetstoreReqBody());

        int id = responseInitial.then().extract().path(ID);

        Response response = petstoreSteps.uploadImage(id);

        response.prettyPrint();

        response.then()
                .body(CODE, equalTo(200))
                .body(MESSAGE, stringContainsInOrder("37886"))
                .body(MESSAGE, stringContainsInOrder("Baron"))
                .body(MESSAGE, stringContainsInOrder("9eb83d4058c144401c5ea2596e658dbf"));
    }
}
