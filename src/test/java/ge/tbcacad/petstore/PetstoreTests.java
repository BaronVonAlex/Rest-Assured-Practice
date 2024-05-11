package ge.tbcacad.petstore;

import ge.tbcacad.data.models.petstore.response.AddNewPetResponse;
import ge.tbcacad.data.models.petstore.response.PetImageUploadResponse;
import ge.tbcacad.data.requestbody.petstore.PetstoreRequestBody;
import ge.tbcacad.enums.StatusAvailability;
import ge.tbcacad.steps.petstore.PetstoreSteps;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static ge.tbcacad.data.constants.Constants.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.IsIterableContaining.hasItem;

public class PetstoreTests {

    protected static PetstoreSteps petstoreSteps;

    @BeforeTest
    public void setUp() {
        petstoreSteps = new PetstoreSteps();
        RestAssured.filters(new AllureRestAssured());
    }

    @Test(priority = 1, description = "Create Post request to Petstore, add new pet and validate that new PET exists by checking its' ID, get our new pet details and validate.")
    public void validateRequest() {
        Response response = petstoreSteps.addNewPet(PetstoreRequestBody.addAndReturnNewPet());

        assertThat(response.getStatusCode(), equalTo(200));

        int id = Integer.parseInt(response.body().as(AddNewPetResponse.class).getId().toString());

        Response responseByStatus = petstoreSteps.findPetsByStatus(StatusAvailability.STATUS_AVAILABLE.toString());

        responseByStatus.then().assertThat().body(ID, hasItem(id));

        AddNewPetResponse responseById = petstoreSteps.findPetById(id).as(AddNewPetResponse.class);
        AddNewPetResponse expResponse = response.as(AddNewPetResponse.class);

        assertThat(responseById.getId(), equalTo(expResponse.getId()));
        assertThat(responseById.getCategory().getId(), equalTo(expResponse.getCategory().getId()));
        assertThat(responseById.getCategory().getName(), equalTo(expResponse.getCategory().getName()));
        assertThat(responseById.getName(), equalTo(expResponse.getName()));
        assertThat(responseById.getPhotoUrls(), equalTo(expResponse.getPhotoUrls()));
        assertThat(responseById.getTags().get(0).getId(), equalTo(expResponse.getTags().get(0).getId()));
        assertThat(responseById.getTags().get(0).getName(), equalTo(expResponse.getTags().get(0).getName()));
        assertThat(responseById.getStatus(), equalTo(expResponse.getStatus()));
    }

    @Test(priority = 2, description = "Create put request to update our pet, then get response + Deserialize and then make assertions.")
    public void updateExistingPet() {
        Response response = petstoreSteps.updateExistingPet(PetstoreRequestBody.updatePet());

        int id = Integer.parseInt(response.body().as(AddNewPetResponse.class).getId().toString());

        AddNewPetResponse responseDeserialized = response.as(AddNewPetResponse.class);
        AddNewPetResponse responsePet = petstoreSteps.findPetById(id).as(AddNewPetResponse.class);

        assertThat(responsePet.getName(), equalTo(responseDeserialized.getName()));
        assertThat(responsePet.getStatus(), equalTo(responseDeserialized.getStatus()));
    }

    @Test(priority = 3, description = "Upload picture to existing pet, then validate that uploaded information/metadata matches one that we sent initially.")
    public void uploadPetPicture() {
        Response responseInitial = petstoreSteps.addNewPet(PetstoreRequestBody.addAndReturnNewPet());

        int id = Integer.parseInt(responseInitial.body().as(AddNewPetResponse.class).getId().toString());

        PetImageUploadResponse uploadResponse = petstoreSteps.uploadImage(id).as(PetImageUploadResponse.class);

        assertThat(uploadResponse.getMessage(), Matchers.containsString(UPLOAD_EXP_BYTES));
        assertThat(uploadResponse.getMessage(), Matchers.containsString(UPLOAD_EXP_NAME));
        assertThat(uploadResponse.getMessage(), Matchers.containsString(UPLOAD_EXP_FILENAME));
    }
}
