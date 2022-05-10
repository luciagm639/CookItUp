package Reports;

import Recipe.RecipePhoto;
import User.RegisteredUser;

public class ReportPhoto extends Report {
	
	RecipePhoto reportedPhoto;
	
	public ReportPhoto(RegisteredUser reporting, String justification, RecipePhoto reportedPhoto) {
		super(reporting, justification);
		this.reportedPhoto = reportedPhoto;
	}
}
