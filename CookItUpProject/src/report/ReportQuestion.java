package report;

import recipe.Question;
import user.RegisteredUser;

public class ReportQuestion extends Report {
	
	private Question reportedQuestion;
	
	public ReportQuestion(RegisteredUser reporting, String justification, Question reportedQuestion) {
		super(reporting, justification);
		this.reportedQuestion = reportedQuestion;
	}

	public Question getReportedQuestion() {
		return reportedQuestion;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("User " + getReporting() + " has reported ");
		sb.append("the question " + reportedQuestion + ".\n");
		sb.append("Justification: " + getJustification() + "\n");
		
		return sb.toString();
	}
	
	public String toStringExtended() {
		StringBuilder sb = new StringBuilder();
		sb.append(this);
		sb.append("Reported recipe: \n");
		sb.append(reportedQuestion.toString());
		
		return sb.toString();
	}
}
