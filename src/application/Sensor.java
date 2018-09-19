package application;

public class Sensor {
	
	String SID;
    String IID;
    long Time;
    int weight;
	
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
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}

}
