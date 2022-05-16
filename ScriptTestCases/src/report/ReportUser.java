package report;

import user.RegisteredUser;

public class ReportUser extends Report {
	
	private RegisteredUser reportedUser;
	
	public ReportUser(RegisteredUser reporting, String justification, RegisteredUser reportedUser) {
		super(reporting, justification);
		this.reportedUser = reportedUser;
	}

	public RegisteredUser getReportedUser() {
		return reportedUser;
	}
}
