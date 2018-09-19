package application;

public class CalciumLevel {
	
	String SID;
    String IID;
    long Time;
    int calciumlevel;
    
	 public String getSID() {
		return SID;
	}
	public void setSID(String sID) {
		SID = sID;
	}
	public String getIID() {
		return IID;
	}
	public void setIID(String iID) {
		IID = iID;
	}
	public long getTime() {
		return Time;
	}
	public void setTime(long time) {
		Time = time;
	}
	public int getCalciumlevel() {
		return calciumlevel;
	}
	public void setCalciumlevel(int calciumlevel) {
		this.calciumlevel = calciumlevel;
	}

}
