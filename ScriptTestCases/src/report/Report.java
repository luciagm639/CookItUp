package report;

import user.RegisteredUser;

public class Report {
	
	private RegisteredUser reporting;
	private String justification;
	
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
