package ge.tbcacad.steps.countryinfoservice;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.matchesPattern;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.collection.IsIterableContainingInRelativeOrder.containsInRelativeOrder;
import static org.testng.Assert.assertEquals;

public class CountryInfoServiceSteps {

    private Response response;
    private XmlPath xmlPath;

    @Step("Make get Request and retrieve response, also get XmlPath")
    public CountryInfoServiceSteps getListOfContinentsByName() {
        response = RestAssured.given()
                .when()
                .get("http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso/ListOfContinentsByName")
                .then()
                .statusCode(200)
                .extract()
                .response();

        xmlPath = new XmlPath(response.asString());
        return this;
    }

    @Step("Get list of Continent names and validate that their size is 6")
    public CountryInfoServiceSteps countValidation(int sNodeCount) {
        List<String> sNames = xmlPath.getList("ArrayOftContinent.tContinent.sName");
        assertEquals(sNodeCount, sNames.size(), "Count of all `sName` nodes");
        return this;
    }

    @Step("Validate list of all `sName` nodes' values")
    public CountryInfoServiceSteps nodeValuesValidation() {
        List<String> sNames = xmlPath.getList("ArrayOftContinent.tContinent.sName");
        List<String> expectedSNames = List.of("Africa", "Antarctica", "Asia", "Europe", "Ocenania", "The Americas");
        assertThat(sNames, containsInAnyOrder(expectedSNames.toArray()));
        return this;
    }

    @Step("Validate `sName` node result with value of `sCode` equals to `AN`")
    public CountryInfoServiceSteps validateIfsNameHasProperValue() {
        String sNameForAN = xmlPath.getString("ArrayOftContinent.tContinent.find { it.sCode == 'AN' }.sName");
        assertThat(sNameForAN, Matchers.is("Antarctica"));
        return this;
    }

    @Step("Validate the last `tContinent` node's `sName` value")
    public CountryInfoServiceSteps validateContinentNodeValues() {
        String lastSName = xmlPath.getString("ArrayOftContinent.tContinent[-1].sName");
        assertThat(lastSName, Matchers.is("The Americas"));
        return this;
    }

    @Step("Verify that each `sName` is unique")
    public CountryInfoServiceSteps checkIfEachSNameIsUnique() {
        List<String> sNames = xmlPath.getList("ArrayOftContinent.tContinent.sName");
        assertThat(sNames, containsInAnyOrder(sNames.stream().distinct().toArray(String[]::new)));
        return this;
    }

    @Step("Validate the presence and correctness of both `sCode` and `sName` for each `tContinent`")
    public CountryInfoServiceSteps codeAndNameValidation() {
        List<String> sCodes = xmlPath.getList("ArrayOftContinent.tContinent.sCode");
        List<String> sNames = xmlPath.getList("ArrayOftContinent.tContinent.sName");

        for (int i = 0; i < sCodes.size(); i++) {
            assertThat(sCodes.get(i), matchesPattern("^[A-Z]{2}$"));
            assertThat(sNames.get(i), not(matchesPattern(".*\\d.*")));
        }
        return this;
    }

    @Step("Ensure that the order of `tContinent` nodes corresponds to a specific sequence (alphabetical order by `sName`)")
    public CountryInfoServiceSteps validateIfAlphabetical() {
        List<String> sNames = xmlPath.getList("ArrayOftContinent.tContinent.sName");
        List<String> sortedSNames = sNames.stream().sorted().toList();
        assertThat(sNames, containsInRelativeOrder(sortedSNames.toArray()));
        return this;
    }

    @Step("Validate the presence of all six continents")
    public CountryInfoServiceSteps validateIfAll6ContinentsArePresent() {
        List<String> sNames = xmlPath.getList("ArrayOftContinent.tContinent.sName");
        List<String> expectedSNames = List.of("Africa", "Antarctica", "Asia", "Europe", "Ocenania", "The Americas");
        assertThat(sNames, hasSize(6));
        assertThat(sNames, containsInAnyOrder(expectedSNames.toArray()));
        return this;
    }

    @Step("Validate that no `sName` node contains numeric characters")
    public CountryInfoServiceSteps checkIfNameDoNotContainDigits() {
        List<String> sNames = xmlPath.getList("ArrayOftContinent.tContinent.sName");
        for (String sName : sNames) {
            assertThat(sName, not(matchesPattern(".*\\d.*")));
        }
        return this;
    }

    @Step("Find `sCode` that starts with `O` and ensure that is `Ocenania`")
    public CountryInfoServiceSteps findSCodeOandValidateIfOceania() {
        String sNameForOC = xmlPath.getString("ArrayOftContinent.tContinent.find { it.sCode == 'OC' }.sName");
        assertThat(sNameForOC, Matchers.is("Ocenania"));
        return this;
    }

    @Step("Find all `sName` that start with `A` and end with `ca`")
    public CountryInfoServiceSteps findAllSNamesStartingWithAAndEndingWithCa() {
        List<String> sNames = xmlPath.getList("ArrayOftContinent.tContinent.sName");
        List<String> filteredSNames = sNames.stream()
                .filter(sName -> sName.startsWith("A") && sName.endsWith("ca"))
                .collect(Collectors.toList());
        assertThat(filteredSNames, Matchers.containsInAnyOrder("Africa", "Antarctica"));
        return this;
    }
}
