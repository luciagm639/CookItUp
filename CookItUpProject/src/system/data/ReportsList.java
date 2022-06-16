package system.data;

import recipe.Comment;
import recipe.Question;
import recipe.Recipe;
import report.*;
import system.MySystem;
import system.RecipeExtended;
import system.RegisteredUserExtended;
import user.RegisteredUser;

public class ReportsList extends DataSet<Report, Report> {

	public ReportsList() {
		super("Reports.txt");
	}
	private final static int COMMENT = 0;
	private final static int QUESTION = 1;
	private final static int RECIPE = 2;
	private final static int USER = 3;

	@Override
	public void readData(MySystem system, String text) {
		String[] sp = split(text);
		
		try {
			int id = Integer.parseInt(sp[0]);
			RegisteredUserExtended reporting = system.getUser(Integer.parseInt(sp[1]));
			String justification = sp[2];
			int type = Integer.parseInt(sp[3]);
			int ReportedId = Integer.parseInt(sp[4]);
			Report r = null;
			switch(type) {
			case COMMENT:
				Comment comment = system.getComment(ReportedId);
				r = new ReportComment(reporting.unextend(), justification, comment);
				break;
			case QUESTION:
				Question question = system.getQuestion(ReportedId);
				r = new ReportQuestion(reporting.unextend(), justification, question);
				break;
			case RECIPE:
				RecipeExtended recipe = system.getRecipe(ReportedId);
				r = new ReportRecipe(reporting.unextend(), justification, recipe.unextend());
				break;
			case USER:
				RegisteredUserExtended user = system.getUser(ReportedId);
				r = new ReportUser(reporting.unextend(), justification, user.unextend());
				break;
			default:
				return;
			}
			
			this.add(r, id);
		} catch(NumberFormatException e) {/*We don't add to the system*/}
	}

	@Override
	public String writeData(Report r) {
		MyStringJoiner sj = new MyStringJoiner();
		sj.add(r.getId());
		sj.add(r.getReporting().getId());
		sj.add(r.getJustification());
		if (r instanceof ReportRecipe) {
			sj.add(RECIPE);
			sj.add(((ReportRecipe) r).getReportedRecipe().getId());
		}
		else if (r instanceof ReportUser) {
			sj.add(USER);
			sj.add(((ReportUser) r).getReportedUser().getId());
		}
		else if (r instanceof ReportComment) {
			sj.add(COMMENT);
			sj.add(((ReportComment) r).getReportedComment().getId());
		}
		else if (r instanceof ReportQuestion) {
			sj.add(QUESTION);
			sj.add(((ReportQuestion) r).getReportedQuestion().getId());
		}
			
		return sj.toString();
	}
}