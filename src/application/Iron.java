package application;

public class Iron {
	
	String SID;
    String IID;
    long Time;
    int ironLevel;
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
	public int getIronLevel() {
		return ironLevel;
	}
	public void setIronLevel(int ironLevel) {
		this.ironLevel = ironLevel;
	}

}
