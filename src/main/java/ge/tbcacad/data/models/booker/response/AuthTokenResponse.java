package ge.tbcacad.data.models.booker.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthTokenResponse {

    @JsonProperty("token")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return
                "AuthTokenResponse{" +
                        "token = '" + token + '\'' +
                        "}";
    }
}