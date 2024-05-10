package ge.tbcacad.data.models.f1.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MRData {

    @JsonProperty("xmlns")
    private String xmlns;

    @JsonProperty("total")
    private String total;

    @JsonProperty("offset")
    private String offset;

    @JsonProperty("series")
    private String series;

    @JsonProperty("limit")
    private String limit;

    @JsonProperty("DriverTable")
    private DriverTable driverTable;

    @JsonProperty("url")
    private String url;

    public String getXmlns() {
        return xmlns;
    }

    public void setXmlns(String xmlns) {
        this.xmlns = xmlns;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public DriverTable getDriverTable() {
        return driverTable;
    }

    public void setDriverTable(DriverTable driverTable) {
        this.driverTable = driverTable;
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
                "MRData{" +
                        "xmlns = '" + xmlns + '\'' +
                        ",total = '" + total + '\'' +
                        ",offset = '" + offset + '\'' +
                        ",series = '" + series + '\'' +
                        ",limit = '" + limit + '\'' +
                        ",driverTable = '" + driverTable + '\'' +
                        ",url = '" + url + '\'' +
                        "}";
    }
}