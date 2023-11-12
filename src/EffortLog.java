//importing necessary packages for parsing start and end time for effort log database
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
		//replace the starting words for a new string to parse it to TimeStamp
		String startT = s.replace("Start Time: ", "");
		String endT = st.replace("Stop Time: ", "");	
		//specifying what time format is being used to parse
		SimpleDateFormat dFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
		Timestamp startTimeStamp = null;
		Timestamp stopTimeStamp = null;
		try { //try parsing the string to TimeStamp
			startTimeStamp = new Timestamp(dFormat.parse(startT).getTime());
			stopTimeStamp = new Timestamp(dFormat.parse(endT).getTime());
		} catch (ParseException err) { //catch exceptions
			err.printStackTrace();
		}
		//insert the new entry to the database
		EffortLogTableOps.insertEffortLog(this.projectName, startTimeStamp, stopTimeStamp, this.lifeCycleStep, this.effortCategory, this.deliverable);
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
