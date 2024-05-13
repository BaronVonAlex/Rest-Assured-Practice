package ge.tbcacad.openapi.springsecurity;

import ge.tbcacad.data.dataprovider.SpringSecurityDataProvider;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import spring.security.invoker.ApiClient;
import spring.security.invoker.JacksonObjectMapper;
import spring.security.model.*;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;

public class SpringSecurityTests {
    private ApiClient api;

    private String accessToken;

    @BeforeSuite
    public void createApiClient() {
        api = ApiClient.api(ApiClient.Config.apiConfig()
                .reqSpecSupplier(() -> new RequestSpecBuilder()
                        .log(LogDetail.ALL)
                        .setConfig(config()
                                .objectMapperConfig(ObjectMapperConfig.objectMapperConfig()
                                        .defaultObjectMapper(JacksonObjectMapper.jackson())))
                        .addFilter(new ErrorLoggingFilter())
                        .setBaseUri("http://localhost:8086")));
    }

    @Test(dataProviderClass = SpringSecurityDataProvider.class, dataProvider = "PasswordDP", priority = 1)
    public void authenticationTests(String password) {
        RegisterRequest authRegRequest = new RegisterRequest()
                .firstname("Alexander")
                .lastname("Kotliarov")
                .email("randgmail@gmail.com")
                .password(password)
                .role(RegisterRequest.RoleEnum.ADMIN);


        Response authRes = api
                .authentication()
                .register()
                .body(authRegRequest)
                .execute(response -> {
                    response.then().statusCode(200);
                    return response;
                });

        if (authRes.statusCode() == 200) {
            accessToken = authRes.as(AuthenticationResponse.class).getAccessToken();
        } else {
            System.out.println("Authentication failed.");
        }
    }

    @Test(priority = 2)
    public void authorizationTests() {
        Response response = given()
                .baseUri("http://localhost:8086")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .get("/api/v1/admin/resource")
                .then()
                .extract().response();

        assertThat(response.getStatusCode(), Matchers.is(200));
        assertThat(response.getBody(), Matchers.equalTo("Hello, you have access to a protected resource that requires admin role and read authority"));
    }

    @Test(dataProviderClass = SpringSecurityDataProvider.class, dataProvider = "PasswordDP", priority = 3)
    public void authenticateTests(String password) {
        AuthenticationRequest authReq = new AuthenticationRequest()
                .email("randgmail@gmail.com")
                .password(password);

        AuthenticationResponse authRes = api
                .authentication()
                .authenticate()
                .body(authReq)
                .execute(response -> {
                    response.then().statusCode(200);
                    return response.as(AuthenticationResponse.class);
                });
        assertThat(authRes.getRoles(), Matchers.containsInAnyOrder("READ_PRIVILEGE", "WRITE_PRIVILEGE", "DELETE_PRIVILEGE", "UPDATE_PRIVILEGE", "ROLE_ADMIN"));
    }

    @Test(priority = 4)
    public void refreshTokenTest() {
        RefreshTokenRequest refreshTokenRequest = new RefreshTokenRequest()
                .refreshToken(accessToken);

        RefreshTokenResponse refreshTokenResponse = api
                .authentication()
                .refreshToken()
                .body(refreshTokenRequest)
                .execute(response -> {
                    response.then().statusCode(200);
                    return response.as(RefreshTokenResponse.class);
                });

        System.out.println(refreshTokenResponse);

        // add Validation for checking if old Access Token still works.
    }
}
