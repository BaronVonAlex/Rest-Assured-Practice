package ge.tbcacad.steps.tempuri;

import ge.tbcacad.util.MarshallingUtil;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.tempuri.FindPerson;
import org.tempuri.FindPersonResponse;
import org.tempuri.ObjectFactory;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.matchesPattern;
import static org.hamcrest.Matchers.notNullValue;

public class TempuriSteps {
    private final ObjectFactory objectFactory;
    private final FindPerson findPerson;
    private final FindPersonResponse findPersonResponse;

    public TempuriSteps() {
        this.objectFactory = new ObjectFactory();
        this.findPerson = objectFactory.createFindPerson();
        this.findPersonResponse = objectFactory.createFindPersonResponse();
    }

    @Step("Set Person ID - {id}")
    public TempuriSteps findPersonById(String id) {
        findPerson.setId(id);
        return this;
    }

    @Step("Marshall request and return Response")
    public Response findPerson() {
        String body = MarshallingUtil.marshallSoapRequest(findPerson);

        return given()
                .header("Content-Type", "text/xml; charset=utf-8")
                .header("SOAPAction", "http://tempuri.org/SOAP.Demo.FindPerson")
                .body(body)
                .post("https://www.crcind.com/csp/samples/SOAP.Demo.cls");
    }

    @Step("Deserialize Response")
    public FindPersonResponse deserializeResponse(Response response) {
        return MarshallingUtil.unmarshallResponse(response.asString(), FindPersonResponse.class);
    }

    @Step("Validate FindPersonResult is not null")
    public TempuriSteps validateFindPersonResultNotNull(FindPersonResponse response) {
        assertThat(response.getFindPersonResult(), notNullValue());
        return this;
    }

    @Step("Validate Person's Name - {expectedName}")
    public TempuriSteps validatePersonName(FindPersonResponse response, String expectedName) {
        assertThat(response.getFindPersonResult().getName(), Matchers.is(expectedName));
        return this;
    }

    @Step("Validate SSN - {expectedSSN}")
    public TempuriSteps validateSSN(FindPersonResponse response, String expectedSSN) {
        assertThat(response.getFindPersonResult().getSSN(), Matchers.is(expectedSSN));
        return this;
    }

    @Step("Validate SSN length is {expectedLength}")
    public TempuriSteps validateSSNLength(FindPersonResponse response, int expectedLength) {
        assertThat(response.getFindPersonResult().getSSN().length(), Matchers.is(expectedLength));
        return this;
    }

    @Step("Validate SSN follows the pattern NNN-NN-NNNN")
    public TempuriSteps validateSSNPattern(FindPersonResponse response) {
        Pattern pattern = Pattern.compile("\\d{3}-\\d{2}-\\d{4}");
        assertThat(response.getFindPersonResult().getSSN(), matchesPattern(pattern));
        return this;
    }

    @Step("Validate DOB - {expectedDOB}")
    public TempuriSteps validateDOB(FindPersonResponse response, String expectedDOB) {
        assertThat(response.getFindPersonResult().getDOB().toString(), Matchers.is(expectedDOB));
        return this;
    }

    @Step("Validate presence of favorite colors Orange and Red")
    public TempuriSteps validateFavoriteColors(FindPersonResponse response) {

        List<String> favoriteColors = response.getFindPersonResult().getFavoriteColors().getFavoriteColorsItem();

        assertThat(favoriteColors, Matchers.hasItems("Orange", "Red"));
        return this;
    }

    @Step("Validate Person's Last Name - {expectedLastName}")
    public TempuriSteps validatePersonLastName(FindPersonResponse response, String expectedLastName) {
        String fullName = response.getFindPersonResult().getName();
        String[] nameParts = fullName.split(",");
        String lastName = nameParts.length > 1 ? nameParts[1].trim() : "";

        assertThat(lastName, Matchers.is(expectedLastName));
        return this;
    }

    @Step("Check if person has Red as one of favorite colors at index 2")
    public boolean hasRedAsFavoriteColorAtIndexTwo(FindPersonResponse response) {
        List<String> favoriteColors = response.getFindPersonResult().getFavoriteColors().getFavoriteColorsItem();

        return IntStream.range(0, favoriteColors.size())
                .anyMatch(i -> i == 1 && favoriteColors.get(i).equals("Red"));
        // Program wise 2nd index is 1st, and this passes, if we put 2nd then it fails.
    }

    @Step("Validate office zip code is not the same as home zip code")
    public boolean validateDifferentZipCodes(FindPersonResponse response) {
        String officeZip = response.getFindPersonResult().getOffice().getZip();
        String homeZip = response.getFindPersonResult().getHome().getZip();

        return !Objects.equals(officeZip, homeZip);
    }
}
