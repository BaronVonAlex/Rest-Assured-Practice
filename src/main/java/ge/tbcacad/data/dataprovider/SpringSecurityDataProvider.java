package ge.tbcacad.data.dataprovider;

import com.github.javafaker.Faker;
import ge.tbcacad.util.RandCharGen;
import org.testng.annotations.DataProvider;

public class SpringSecurityDataProvider {

    private static final Faker faker = new Faker();

    private static final Object[][] data;

    static {
        data = new Object[][]{
                {faker.name().firstName(), faker.name().lastName(), faker.internet().emailAddress(), faker.lorem().characters(7).toLowerCase()},
                {faker.name().firstName(), faker.name().lastName(), faker.internet().emailAddress(), faker.lorem().characters(7).toUpperCase()},
                {faker.name().firstName(), faker.name().lastName(), faker.internet().emailAddress(), faker.numerify("########")},
                {faker.name().firstName(), faker.name().lastName(), faker.internet().emailAddress(), RandCharGen.generateRandomSpecialChars(7)},
                {faker.name().firstName(), faker.name().lastName(), faker.internet().emailAddress(), "a"},
                {faker.name().firstName(), faker.name().lastName(), faker.internet().emailAddress(), "1"},
                {faker.name().firstName(), faker.name().lastName(), faker.internet().emailAddress(), " "},
                {faker.name().firstName(), faker.name().lastName(), faker.internet().emailAddress(), "P@ssw0rd"} // passes test
        };
    }

    @DataProvider(name = "PasswordDP")
    public static Object[][] authenticationData() {
        return data;
    }
}
