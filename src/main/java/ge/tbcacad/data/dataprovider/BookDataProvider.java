package ge.tbcacad.data.dataprovider;

import ge.tbcacad.steps.swaggerSteps.SwaggerSteps;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.DataProvider;

import java.util.List;


public class BookDataProvider {

    protected static SwaggerSteps swaggerSteps;

    @DataProvider(name = "isbnProvider")
    public Object[][] isbnProvider() {
        swaggerSteps = new SwaggerSteps();

        ValidatableResponse response = SwaggerSteps.getListOfBooksValidation();

        List<String> allIsbns = SwaggerSteps.extractISBNFromResponse(response);

        Object[][] data = new Object[allIsbns.size()][1];
        for (int i = 0; i < allIsbns.size(); i++) {
            data[i][0] = allIsbns.get(i);
        }
        return data;
    }
}
