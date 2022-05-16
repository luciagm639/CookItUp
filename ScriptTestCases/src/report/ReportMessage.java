package report;

import recipe.Message;
import user.RegisteredUser;

public class ReportMessage extends Report {
	
	private Message reportedMessage;
	
	public ReportMessage(RegisteredUser reporting, String justification, Message reportedMessage) {
		super(reporting, justification);
		this.reportedMessage = reportedMessage;
	}

	public Message getReportedMessage() {
		return reportedMessage;
	}
}
