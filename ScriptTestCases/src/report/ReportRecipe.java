package report;

import recipe.Recipe;
import user.RegisteredUser;

public class ReportRecipe extends Report {
	
	private Recipe reportedRecipe;
	
	public ReportRecipe(RegisteredUser reporting, String justification, Recipe reportedRecipe) {
		super(reporting, justification);
		this.reportedRecipe = reportedRecipe;
	}

	public Recipe getReportedRecipe() {
		return reportedRecipe;
	}
}
