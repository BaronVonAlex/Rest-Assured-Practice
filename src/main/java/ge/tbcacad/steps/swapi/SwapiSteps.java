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
    @Step("Make Get request to return response of all planets.")
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

    @Step("Take RestAssured Response and deserialize as Planet class.")
    public Planet getPlanet(Response response) {
        return response.as(Planet.class);
    }

    @Step("Get ResultsItem list and with use of Comparator, return first three planets, else return nothing and notify error.")
    public ResultsItem[] getThreeMostRecent(Planet planets) {
        if (planets != null && planets.getResults() != null && !planets.getResults().isEmpty()) {

            ResultsItem[] resultsItems = planets.getResults().toArray(new ResultsItem[0]);
            Arrays.sort(resultsItems, Comparator.comparing(ResultsItem::getCreated).reversed());

            return Arrays.copyOf(resultsItems, Math.min(3, resultsItems.length));
        } else {
            System.out.println("No planets found.");
            return new ResultsItem[0];
        }
    }

    @Step("Get list of planets and with use of loop, console log planet name and creation date.")
    public void printPlanetDetails(ResultsItem[] planets) {
        for (ResultsItem planet : planets) {
            System.out.println("Name: " + planet.getName());
            System.out.println("Creation Date: " + planet.getCreated());
        }
    }

    @Step("Get response and return planet with highest rotation period by using Comparator.")
    public ResultsItem getTopOneByRotation(Response response) {
        Planet planet = getPlanet(response);

        return planet.getResults().stream()
                .max(Comparator.comparing(ResultsItem::getRotationPeriod))
                .orElse(null);
    }

    @Step("Get String list of Links from Response, and return First nun-null Link.")
    public String getFirstResidentLink(ResultsItem planet) {
        List<String> residentLinks = planet.getResidents();

        if (residentLinks != null) {
            return residentLinks.get(0);
        } else {
            return null;
        }
    }

    @Step("If URL Value is not empty, then redirect to it and console log response.")
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
