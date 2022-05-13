package Recipe;

import java.util.ArrayList;
import java.util.List;

import Reports.Report;
import User.*;

public class Recipe {

	RegisteredUser user;
	String name;
	int priority;
	List<Comment> commentsList = new ArrayList<Comment>();
	List<Question> questionsList = new ArrayList<Question>();
	List<Ingredients> ingredientsList = new ArrayList<Ingredients>();
	List<RecipePhoto> photosList = new ArrayList<RecipePhoto>();
	List<Report> reportsList = new ArrayList<Report>();
	List<Step> stepsList = new ArrayList<Step>();

	public List<Report> getReportsList() {
		return reportsList;
	}

	public void setReportsList(List<Report> reportsList) {
		this.reportsList = reportsList;
	}

	public Recipe(String name, int priority, RegisteredUser user) {
		this.name = name;
		this.commentsList = null;
		this.ingredientsList = null;
		this.questionsList = null;
		this.photosList = null;
		this.reportsList = null;
		this.stepsList = null;
		this.priority = priority;
		this.user = user;
	}

	public RegisteredUser getUser() {
		return user;
	}

	public void setUser(RegisteredUser user) {
		this.user = user;
	}

	public List<RecipePhoto> getPhotosList() {
		return photosList;
	}

	public void setPhotosList(List<RecipePhoto> photosList) {
		this.photosList = photosList;
	}

	public List<Comment> getCommentsList() {
		return commentsList;
	}

	public List<Question> getQuestionsList() {
		return questionsList;
	}

	public List<Ingredients> getIngredientsList() {
		return ingredientsList;
	}

	public void setCommentsList(List<Comment> commentsList) {
		this.commentsList = commentsList;
	}

	public void setQuestionsList(List<Question> questionsList) {
		this.questionsList = questionsList;
	}

	public void setIngredientsList(List<Ingredients> ingredientsList) {
		this.ingredientsList = ingredientsList;
	}

	public List<Step> getStepsList() {
		return stepsList;
	}

	public void setStepsList(List<Step> stepsList) {
		this.stepsList = stepsList;
	}

	public void addRecipePhoto(RecipePhoto photo) {
		photosList.add(photo);
	}
}