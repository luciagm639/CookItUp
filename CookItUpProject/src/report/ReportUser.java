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
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("User " + getReporting() + " has reported ");
		sb.append("the user " + reportedUser + ".\n");
		sb.append("Justification: " + getJustification() + "\n");
		
		return sb.toString();
	}
	
	public String toStringExtended() {
		StringBuilder sb = new StringBuilder();
		sb.append(this);
		sb.append("Reported user's profile: \n");
		sb.append(getReportedUser().showProfile());
		
		return sb.toString();
	}
}
