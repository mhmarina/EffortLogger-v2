
public class EffortLog {
	private int totalSecs;
	private String startTime;
	private String stopTime;
	private String projectName;
	private String lifeCycleStep;
	private String effortCategory;
	private String deliverable;
	
	//overloaded constructor
	public EffortLog(int ts, String s, String st, String p, String lc, String ec, String d) {
		// all necessary information in an effort log
		this.totalSecs = ts;
		this.startTime = s;
		this.stopTime = st;
		this.projectName = p;
		this.lifeCycleStep = lc;
		this.effortCategory = ec;
		this.deliverable = d;
	}
	
	public String toString() {
		// to be used in logs console
		String log = "";
		log += "Project Name: " + this.projectName +
				"\n" + this.startTime + "\t" + this.stopTime + "\tTotal Time: " + this.totalSecs + "s\n" +
				"Life Cycle Step: " + this.lifeCycleStep +
				"\nEffort Category: " + this.effortCategory +
				"\nDeliverable: " + this.deliverable + "\n";
		return log;
	}
}
