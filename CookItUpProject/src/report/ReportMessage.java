package report;

import recipe.Message;
import user.RegisteredUser;

public class ReportMessage<E extends Message<E>> extends Report {
	
	private Message<E> reportedMessage;
	
	public ReportMessage(RegisteredUser reporting, String justification, Message<E> reportedMessage) {
		super(reporting, justification);
		this.reportedMessage = reportedMessage;
	}

	public Message<E> getReportedMessage() {
		return reportedMessage;
	}
}
