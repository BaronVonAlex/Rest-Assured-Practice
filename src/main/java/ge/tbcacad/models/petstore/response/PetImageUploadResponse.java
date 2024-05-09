package ge.tbcacad.models.petstore.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PetImageUploadResponse {

    @JsonProperty("code")
    private int code;

    @JsonProperty("type")
    private String type;

    @JsonProperty("message")
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return
                "PetImageUploadResponse{" +
                        "code = '" + code + '\'' +
                        ",type = '" + type + '\'' +
                        ",message = '" + message + '\'' +
                        "}";
    }
}