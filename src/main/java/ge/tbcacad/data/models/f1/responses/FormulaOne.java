package ge.tbcacad.data.models.f1.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FormulaOne {

    @JsonProperty("MRData")
    private MRData mRData;

    public MRData getMRData() {
        return mRData;
    }

    public void setMRData(MRData mRData) {
        this.mRData = mRData;
    }

    @Override
    public String toString() {
        return
                "FormulaOne{" +
                        "mRData = '" + mRData + '\'' +
                        "}";
    }
}