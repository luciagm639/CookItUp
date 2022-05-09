package Reports;

import User.RegisteredUser;

public class Report {
	
	RegisteredUser reporting;
	String justification;
	
	public Report(RegisteredUser reporting, String justification) {
		this.reporting = reporting;
		this.justification = justification;
	}
}
