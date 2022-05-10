package Reports;

import User.RegisteredUser;

public class Report {
	
	public RegisteredUser reporting;
	String justification;
	
	public Report(RegisteredUser reporting, String justification) {
		this.reporting = reporting;
		this.justification = justification;
	}

	public RegisteredUser getReporting() {
		return reporting;
	}

	public void setReporting(RegisteredUser reporting) {
		this.reporting = reporting;
	}

	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}
}
