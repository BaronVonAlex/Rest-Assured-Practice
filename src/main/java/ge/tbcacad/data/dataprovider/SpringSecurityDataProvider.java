package ge.tbcacad.data.dataprovider;

import com.github.javafaker.Faker;
import ge.tbcacad.util.RandCharGen;
import org.testng.annotations.DataProvider;

public class SpringSecurityDataProvider {

    private static final Faker faker = new Faker();

    @DataProvider(name = "PasswordDP")
    public static Object[][] passwords() {
        return new Object[][]{
                {faker.lorem().characters(7).toLowerCase()},
                {faker.lorem().characters(7).toUpperCase()},
                {faker.numerify("########")},
                {RandCharGen.generateRandomSpecialChars(7)},
                {"a"},
                {"1"},
                {" "},
                {"P@ssw0rd"} // passes test
        };
    }
}
