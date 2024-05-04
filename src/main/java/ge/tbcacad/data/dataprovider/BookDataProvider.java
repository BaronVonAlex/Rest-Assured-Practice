package ge.tbcacad.data.dataprovider;

import ge.tbcacad.steps.swaggerSteps.SwaggerSteps;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.DataProvider;

import java.util.List;
import java.util.Map;

import static ge.tbcacad.steps.swaggerSteps.SwaggerSteps.prepareBookData;


public class BookDataProvider {
    @DataProvider(name = "bookDataProvider")
    public Object[][] bookDataProvider() {
        ValidatableResponse response = SwaggerSteps.getListOfBooksValidation();
        List<Map<String, Object>> allBooks = SwaggerSteps.extractBooksFromResponse(response);
        return prepareBookData(allBooks);
    }
}
