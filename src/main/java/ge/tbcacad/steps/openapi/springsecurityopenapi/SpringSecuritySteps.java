package ge.tbcacad.steps.openapi.springsecurityopenapi;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import spring.security.invoker.ApiClient;
import spring.security.model.*;

import static org.hamcrest.MatcherAssert.assertThat;

public class SpringSecuritySteps {
    @Step("Create Auth. Registration body.")
    public RegisterRequest createAuthRegBody(String firstname, String lastname, String email, String password, RegisterRequest.RoleEnum role) {
        return new RegisterRequest()
                .firstname(firstname)
                .lastname(lastname)
                .email(email)
                .password(password)
                .role(role);
    }

    @Step("Register new user and get return response")
    public Response registerUser(ApiClient api, RegisterRequest registerRequest) {
        return api
                .authentication()
                .register()
                .body(registerRequest)
                .execute(response -> {
                    response.then().statusCode(200);
                    return response;
                });
    }

    @Step("Extract access token from authentication response with desired status code.")
    public String extractAccessToken(Response authRes, int statusCode) {
        if (authRes.statusCode() == statusCode) {
            return authRes.as(AuthenticationResponse.class).getAccessToken();
        } else {
            System.out.println("Authentication failed.");
            return null;
        }
    }

    @Step("Make Authorization request with AccessToken as header and then receive response.")
    public Response getAuthorizationResponse(ApiClient api, String headerName, String accessToken) {
        return api.authorization().reqSpec(
                        requestSpecBuilder -> {
                            requestSpecBuilder.addHeader(headerName, "Bearer " + accessToken);
                        }
                ).sayHelloWithRoleAdminAndReadAuthority()
                .execute(response1 -> {
                    response1.then().log().all();
                    return response1;
                });
    }

    @Step("Validate Response's status code")
    public SpringSecuritySteps validateStatusCode(Response response, int expectedStatusCode) {
        assertThat(response.getStatusCode(), Matchers.is(expectedStatusCode));
        return this;
    }

    @Step("Validate Response's message code")
    public SpringSecuritySteps validateBodyMessage(Response response, String expectedMessage) {
        assertThat(response.getBody().asString(), Matchers.containsStringIgnoringCase(expectedMessage));
        return this;
    }

    @Step("Create Authentication authReq body")
    public AuthenticationRequest createAuthenticateReqBody(String email, String password) {
        return new AuthenticationRequest()
                .email(email)
                .password(password);
    }

    @Step("Get Authentication response from making authenticate() request")
    public AuthenticationResponse getAuthenticationResponse(ApiClient api, AuthenticationRequest authReq) {
        return api
                .authentication()
                .authenticate()
                .body(authReq)
                .execute(response -> {
                    response.then().statusCode(200);
                    return response.as(AuthenticationResponse.class);
                });
    }

    @Step("Validate that our token has appropriate roles")
    public void validateTokenRoles(AuthenticationResponse authRes) {
        assertThat(authRes.getRoles(), Matchers.containsInAnyOrder("READ_PRIVILEGE", "WRITE_PRIVILEGE", "DELETE_PRIVILEGE", "UPDATE_PRIVILEGE", "ROLE_ADMIN"));
    }

    @Step("Refresh Token")
    public RefreshTokenRequest refreshTokenRequest(String accessToken) {
        return new RefreshTokenRequest().refreshToken(accessToken);
    }

    @Step("Get refreshed Token Request")
    public RefreshTokenResponse getRefreshedTokenResponse(ApiClient api, RefreshTokenRequest reqBody) {
        return api
                .authentication()
                .refreshToken()
                .body(reqBody)
                .execute(response -> {
                    response.then().statusCode(200);
                    return response.as(RefreshTokenResponse.class);
                });
    }
}
