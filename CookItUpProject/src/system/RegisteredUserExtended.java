package system;

import java.util.ArrayList;
import java.util.List;

import recipe.Comment;
import recipe.Question;
import recipe.Recipe;
import report.Report;
import user.RegisteredUser;

public class RegisteredUserExtended extends RegisteredUser {
	
	private List<RecipeExtended> recipesList = new ArrayList<RecipeExtended>();
	private List<Report> reportsList = new ArrayList<>();
	private List<Comment> commentsList = new ArrayList<>();
	private List<Question> questionsList = new ArrayList<>();

	private List<RegisteredUser> blockList = new ArrayList<RegisteredUser>();
	private List<RegisteredUser> followList = new ArrayList<RegisteredUser>();
	
	public RegisteredUserExtended(String name, int id, String password, int chips, boolean status) {
		super(name,id,password,chips,status);
	}
	
	public RegisteredUserExtended(RegisteredUser user) {
		this(user.getName(), user.getId(), user.getPassword(), user.getChips(), user.getStatus());
	}
	
	/*Recipes*/
	public List<RecipeExtended> getRecipesList() {
		return recipesList;
	}

	public boolean addRecipe(RecipeExtended recipe) {
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
	
	/*Block list*/
	public boolean block(RegisteredUser user) {
		if (this.blockList.contains(user)) {
			System.err.println("ERROR: The user cannot block another user already blocked");
			return false;
		} else {
			return this.blockList.add(user);
		}
	}
	
	public boolean unblock(RegisteredUser user) {
		if (this.blockList.contains(user)) {
			return this.blockList.remove(user);
		} else {
			System.err.println("ERROR: The user cannot unblock another user not previously blocked");
			return false;
		}
	}
	
	public boolean isBlocked(RegisteredUser user) {
		return blockList.contains(user);
	}
	
	/*Follow list*/
	public boolean follow(RegisteredUser user) {
		if (this.followList.contains(user)) {
			System.err.println("ERROR: The user cannot follow another user already followed");
			return false;
		} else {
			return this.followList.add(user);
		}
	}
	
	public boolean unfollow(RegisteredUser user) {
		if (this.followList.contains(user)) {
			return this.followList.remove(user);
		} else {
			System.err.println("ERROR: The user cannot unfollow a user not previously followed");
			return false;
		}
	}
	
	public boolean isFollowed(RegisteredUser user) {
		return followList.contains(user);
	}
	
	
	public List<Report> getReportsList() {
		return reportsList;
	}

	public List<RegisteredUser> getBlockList() {
		return blockList;
	}

	public List<RegisteredUser> getFollowList() {
		return followList;
	}

	public RegisteredUser unextend() {
		return new RegisteredUser(this);
	}
}
