package report;

import recipe.RecipePhoto;
import user.RegisteredUser;

public class ReportPhoto extends Report {
	
	private RecipePhoto reportedPhoto;
	
	public ReportPhoto(RegisteredUser reporting, String justification, RecipePhoto reportedPhoto) {
		super(reporting, justification);
		this.reportedPhoto = reportedPhoto;
	}

	public RecipePhoto getReportedPhoto() {
		return reportedPhoto;
	}
}
