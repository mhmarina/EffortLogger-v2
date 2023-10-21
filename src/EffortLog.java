
public class EffortLog {
	private int totalSecs;
	private String startTime;
	private String stopTime;
	private String projectName;
	private String lifeCycleStep;
	private String effortCategory;
	private String deliverable;
	
	//default constructor
	public EffortLog(int ts, String s, String st, String p, String lc, String ec, String d) {
		this.totalSecs = ts;
		this.startTime = s;
		this.stopTime = st;
		this.projectName = p;
		this.lifeCycleStep = lc;
		this.effortCategory = ec;
		this.deliverable = d;
	}
	
	public String toString() {
		String log = "";
		log += "Project Name: " + this.projectName +
				"\nStart Time: " + this.startTime + "\tStop Time: " + this.stopTime + "\tTotal Time: " + this.totalSecs + "s\n" +
				"Life Cycle Step: " + this.lifeCycleStep +
				"\nEffort Category: " + this.effortCategory +
				"\nDeliverable: " + this.deliverable + "\n";
		return log;
	}
}
