package report;

import recipe.Comment;
import user.RegisteredUser;

public class ReportComment extends Report {
	
	private Comment reportedComment;
	
	public ReportComment(RegisteredUser reporting, String justification, Comment reportedComment) {
		super(reporting, justification);
		this.reportedComment = reportedComment;
	}

	public Comment getReportedComment() {
		return reportedComment;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("User " + getReporting() + " has reported ");
		sb.append("the comment " + reportedComment + ".\n");
		sb.append("Justification: " + getJustification() + "\n");
		
		return sb.toString();
	}
	
	public String toStringExtended() {
		StringBuilder sb = new StringBuilder();
		sb.append(this);
		sb.append("Reported recipe: \n");
		sb.append(reportedComment.toString());
		
		return sb.toString();
	}
}
