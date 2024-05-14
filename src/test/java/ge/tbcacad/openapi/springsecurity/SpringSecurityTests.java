package ge.tbcacad.openapi.springsecurity;

import ge.tbcacad.data.dataprovider.SpringSecurityDataProvider;
import ge.tbcacad.steps.openapi.springsecurityopenapi.SpringSecuritySteps;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import spring.security.invoker.ApiClient;
import spring.security.invoker.JacksonObjectMapper;
import spring.security.model.*;

import static ge.tbcacad.data.constants.Constants.*;
import static io.restassured.RestAssured.config;

public class SpringSecurityTests {
    protected static SpringSecuritySteps springSecuritySteps;
    private ApiClient api;
    private String accessToken;

    @BeforeSuite
    public void createApiClient() {
        springSecuritySteps = new SpringSecuritySteps();
        api = ApiClient.api(ApiClient.Config.apiConfig()
                .reqSpecSupplier(() -> new RequestSpecBuilder()
                        .log(LogDetail.ALL)
                        .setConfig(config()
                                .objectMapperConfig(ObjectMapperConfig.objectMapperConfig()
                                        .defaultObjectMapper(JacksonObjectMapper.jackson())))
                        .addFilter(new ErrorLoggingFilter())
                        .setBaseUri(LOCAL_HOST_BASE_URI)));
    }

    @Test(dataProviderClass = SpringSecurityDataProvider.class, dataProvider = "PasswordDP", priority = 1)
    public void authenticationTests(String firstName, String lastName, String email, String password) {
        RegisterRequest authRegRequest = springSecuritySteps
                .createAuthRegBody(
                        firstName,
                        lastName,
                        email,
                        password,
                        RegisterRequest.RoleEnum.ADMIN
                );


        Response authRes = springSecuritySteps.registerUser(api, authRegRequest);

        accessToken = springSecuritySteps.extractAccessToken(authRes, 200);
    }

    @Test(priority = 2)
    public void authorizationTests() {
        Response response = springSecuritySteps.getAuthorizationResponse(api, AUTHORIZATION, accessToken);

        springSecuritySteps
                .validateStatusCode(response, 200)
                .validateBodyMessage(response, HELLO_MSG);
    }

    @Test(dataProviderClass = SpringSecurityDataProvider.class, dataProvider = "PasswordDP", priority = 3)
    public void authenticateTests(String firstName, String lastName, String email, String password) {
        AuthenticationRequest authReq = springSecuritySteps.createAuthenticateReqBody(EMAIL, password);

        AuthenticationResponse authRes = springSecuritySteps.getAuthenticationResponse(api, authReq);
        springSecuritySteps.validateTokenRoles(authRes);
    }

    @Test(priority = 4)
    public void refreshTokenTest() {
        RefreshTokenRequest refreshTokenRequest = springSecuritySteps.refreshTokenRequest(accessToken);

        RefreshTokenResponse refreshTokenResponse = springSecuritySteps.getRefreshedTokenResponse(api, refreshTokenRequest);

        System.out.println(refreshTokenResponse.getTokenType());
        // after refreshing token try to authorize again with old one.
        Response response = springSecuritySteps.getAuthorizationResponse(api, AUTHORIZATION, accessToken);
        // validate if we get same response as we got before refreshing.
        springSecuritySteps
                .validateStatusCode(response, 200)
                .validateBodyMessage(response, HELLO_MSG);
    }
}
