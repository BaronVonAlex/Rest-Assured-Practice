package ge.tbcacad.models.booker.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthTokenRequest {

    @JsonProperty("password")
    private String password;

    @JsonProperty("username")
    private String username;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return
                "AuthTokenRequest{" +
                        "password = '" + password + '\'' +
                        ",username = '" + username + '\'' +
                        "}";
    }
}