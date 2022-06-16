package system;

import java.util.ArrayList;
import java.util.List;

import recipe.Comment;
import recipe.Question;
import recipe.Recipe;
import report.Report;
import user.RegisteredUser;

public class RegisteredUserExtended extends RegisteredUser {
	
	private List<Recipe> recipesList = new ArrayList<Recipe>();
	private List<Report> reportsList = new ArrayList<>();
	private List<Comment> commentsList = new ArrayList<>();
	private List<Question> questionsList = new ArrayList<>();
	
	
	public RegisteredUserExtended(String name, int id, String password, int chips, boolean status) {
		super(name,id,password,chips,status);
	}
	
	public RegisteredUserExtended(RegisteredUser user) {
		this(user.getName(), user.getId(), user.getPassword(), user.getChips(), user.getStatus());
	}
	
	/*Recipes*/
	public List<Recipe> getRecipesList() {
		return recipesList;
	}

	public boolean addRecipe(Recipe recipe) {
		return recipesList.add(recipe);	
	}
	
	public boolean deleteRecipe(Recipe recipe) {
		return recipesList.remove(recipe);
	}
	
	/*Questions*/
	public List<Question> getQuestionList() {
		return questionsList;
	}
	
	public boolean addQuestion(Question q) {
		return questionsList.add(q);
	}
	
	/*Comments*/
	public List<Comment> getCommentList() {
		return commentsList;
	}
	
	public boolean addComment(Comment c) {
		return commentsList.add(c);
	}
	
	public List<Report> getReportsList() {
		return reportsList;
	}
}
