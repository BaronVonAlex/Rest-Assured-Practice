package ge.tbcacad.data.models.f1.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DriverTable {

    @JsonProperty("Drivers")
    private List<DriversItem> drivers;

    @JsonProperty("season")
    private String season;

    public List<DriversItem> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<DriversItem> drivers) {
        this.drivers = drivers;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    @Override
    public String toString() {
        return
                "DriverTable{" +
                        "drivers = '" + drivers + '\'' +
                        ",season = '" + season + '\'' +
                        "}";
    }
}