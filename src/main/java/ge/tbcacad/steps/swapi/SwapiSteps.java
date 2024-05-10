package ge.tbcacad.steps.swapi;

import ge.tbcacad.data.models.swapi.responses.Planet;
import ge.tbcacad.data.models.swapi.responses.ResultsItem;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SwapiSteps {
    @Step
    public Response getPlanets() {
        return RestAssured.given()
                .baseUri("https://swapi.dev/api/planets/?format=json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .extract()
                .response();
    }
    @Step
    public Planet getPlanet(Response response) {
        return response.as(Planet.class);
    }
    @Step
    public ResultsItem[] getThreeMostRecent(Planet planets) {
        if (planets != null && planets.results() != null && !planets.results().isEmpty()) {

            ResultsItem[] resultsItems = planets.results().toArray(new ResultsItem[0]);
            Arrays.sort(resultsItems, Comparator.comparing(ResultsItem::created).reversed());

            return Arrays.copyOf(resultsItems, Math.min(3, resultsItems.length));
        } else {
            System.out.println("No planets found.");
            return new ResultsItem[0];
        }
    }
    @Step
    public void printPlanetDetails(ResultsItem[] planets) {
        for (ResultsItem planet : planets) {
            System.out.println("Name: " + planet.name());
            System.out.println("Creation Date: " + planet.created());
        }
    }
    @Step
    public ResultsItem getTopOneByRotation(Response response) {
        Planet planet = getPlanet(response);

        return planet.results().stream()
                .max(Comparator.comparing(ResultsItem::rotationPeriod))
                .orElse(null); // Handle case where list is empty
    }
    @Step
    public String getFirstResidentLink(ResultsItem planet) {
        List<String> residentLinks = planet.residents();

        if (residentLinks != null) {
            return residentLinks.get(0);
        } else {
            return null;
        }
    }
    @Step
    public void redirectToUrl(String url) {
        if (url != null) {
            Response response = RestAssured.given()
                    .baseUri(url)
                    .when()
                    .get();

            System.out.println("URL : " + url);
            response.prettyPrint();
        } else {
            System.out.println("Error");
        }
    }
}
