package ge.tbcacad.data.models.f1.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DriversItem {

    @JsonProperty("code")
    private String code;

    @JsonProperty("driverId")
    private String driverId;

    @JsonProperty("permanentNumber")
    private String permanentNumber;

    @JsonProperty("nationality")
    private String nationality;

    @JsonProperty("givenName")
    private String givenName;

    @JsonProperty("familyName")
    private String familyName;

    @JsonProperty("dateOfBirth")
    private String dateOfBirth;

    @JsonProperty("url")
    private String url;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getPermanentNumber() {
        return permanentNumber;
    }

    public void setPermanentNumber(String permanentNumber) {
        this.permanentNumber = permanentNumber;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return
                "DriversItem{" +
                        "code = '" + code + '\'' +
                        ",driverId = '" + driverId + '\'' +
                        ",permanentNumber = '" + permanentNumber + '\'' +
                        ",nationality = '" + nationality + '\'' +
                        ",givenName = '" + givenName + '\'' +
                        ",familyName = '" + familyName + '\'' +
                        ",dateOfBirth = '" + dateOfBirth + '\'' +
                        ",url = '" + url + '\'' +
                        "}";
    }
}