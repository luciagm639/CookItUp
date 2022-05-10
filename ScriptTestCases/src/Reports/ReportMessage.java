package Reports;

import User.Message;
import User.RegisteredUser;

public class ReportMessage extends Report {
	
	Message reportedMessage;
	
	public ReportMessage(RegisteredUser reporting, String justification, Message reportedMessage) {
		super(reporting, justification);
		this.reportedMessage = reportedMessage;
	}
}
