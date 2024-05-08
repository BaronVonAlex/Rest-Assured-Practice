package ge.tbcacad.models.f1;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FormulaOne{

	@JsonProperty("MRData")
	private MRData mRData;

	public void setMRData(MRData mRData){
		this.mRData = mRData;
	}

	public MRData getMRData(){
		return mRData;
	}

	@Override
 	public String toString(){
		return 
			"FormulaOne{" + 
			"mRData = '" + mRData + '\'' + 
			"}";
		}
}