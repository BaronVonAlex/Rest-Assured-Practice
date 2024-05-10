package ge.tbcacad.swapi;

import ge.tbcacad.data.models.swapi.responses.ResultsItem;
import ge.tbcacad.steps.swapi.SwapiSteps;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static ge.tbcacad.data.constants.Constants.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class SwapiTests {
    protected static SwapiSteps SwapiSteps;

    @BeforeTest
    public void setUp() {
        SwapiSteps = new SwapiSteps();
        RestAssured.filters(new AllureRestAssured());
    }

    @Test
    public void planetsTest() {
        Response response = SwapiSteps.getPlanets();

        ResultsItem[] Planets = SwapiSteps.getThreeMostRecent(SwapiSteps.getPlanet(response));

        SwapiSteps.printPlanetDetails(Planets);

        assertThat(Planets[0].name(), Matchers.hasToString(PLANET_NAME));
        assertThat(Planets[0].diameter(), Matchers.hasToString(PLANET_DIAMETER));
        assertThat(Planets[0].gravity(), Matchers.hasToString(PLANET_GRAVITY));
        assertThat(Planets[0].surfaceWater(), Matchers.hasToString(PLANET_SUR_WATER));
        assertThat(Planets[0].orbitalPeriod(), Matchers.hasToString(PLANET_ORB_PERIOD));
    }

    @Test
    public void getFirstPlanetByRotation() {
        Response response = SwapiSteps.getPlanets();
        ResultsItem topPlanetByRotation = SwapiSteps.getTopOneByRotation(response);

        System.out.println(topPlanetByRotation.name());

        SwapiSteps.redirectToUrl(SwapiSteps.getFirstResidentLink(topPlanetByRotation));
    }
}
