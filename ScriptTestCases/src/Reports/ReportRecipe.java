package Reports;

import Recipe.Recipe;
import User.RegisteredUser;

public class ReportRecipe extends Report {
	
	Recipe reportedRecipe;
	
	public ReportRecipe(RegisteredUser reporting, String justification, Recipe reportedRecipe) {
		super(reporting, justification);
		this.reportedRecipe = reportedRecipe;
	}
}
