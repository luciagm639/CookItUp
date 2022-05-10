package Reports;

import User.RegisteredUser;

public class ReportUser extends Report {
	
	RegisteredUser reportedUser;
	
	public ReportUser(RegisteredUser reporting, String justification, RegisteredUser reportedUser) {
		super(reporting, justification);
		this.reportedUser = reportedUser;
	}
}
