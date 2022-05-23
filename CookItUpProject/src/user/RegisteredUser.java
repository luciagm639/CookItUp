package user;

import java.util.ArrayList;
import java.util.List;

import recipe.Comment;
import recipe.Question;
import recipe.Recipe;
import report.Report;
import system.data.Data;

public class RegisteredUser extends Data<RegisteredUser> {

	private String name;
	private String password;
	private List<RegisteredUser> blockList = new ArrayList<RegisteredUser>();
	private List<RegisteredUser> followList = new ArrayList<RegisteredUser>();
	private List<Recipe> recipesList = new ArrayList<Recipe>();
	private List<Report> reportsList = new ArrayList<>();
	private List<Comment> commentsList = new ArrayList<>();
	private List<Question> questionsList = new ArrayList<>();
	private int chips;
	private boolean status;
  
	
	public RegisteredUser(String name, int id, String password, int chips, boolean status) {
		super(id);
		this.name = name;
		this.password = password;
		this.chips = chips;
		this.status = status;
	}
	
	public RegisteredUser(String name, int id, String password) {
		this(name, id, password, 0, false);
		this.name = name;
		this.password = password;
	}
	
	public RegisteredUser(String name, String password) {
		this.name = name;
		this.password = password;
		this.chips = 0;
		this.status = false;
	}
	  
	@Override
	public String toString() {
	  return name;
	}
	  
	public String showProfile() {
		StringBuilder sb = new StringBuilder();
		sb.append("Showing user " + this.getId() + ":\n");
		sb.append("Name: " + this.name + "\n");
		sb.append("Chips : " + this.getChips() + "\n");
		return sb.toString();	
	}

	public String getName() {
		return name;
   }

	public String getPassword() {
		return password;
	}

	public List<RegisteredUser> getBlockList() {
		return blockList;
	}

	public List<RegisteredUser> getFollowList() {
		return followList;
	}
	
	/*Recipes*/
	public List<Recipe> getRecipesList() {
		return recipesList;
	}
	
	public void addRecipe(Recipe recipe) {
		recipesList.add(recipe);	
	}
	
	public void deleteRecipe(Recipe recipe) {
		recipesList.remove(recipe);
	}

	public List<Report> getReportsList() {
		return reportsList;
	}

	public boolean getStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	/*Block list*/
	public void block(RegisteredUser user) {
		if (this.blockList.contains(user)) {
			throw new RuntimeException("ERROR: The user cannot block another user already blocked");
		} else {
			this.blockList.add(user);
		}
	}
	
	public void unblock(RegisteredUser user) {
		if (this.blockList.contains(user)) {
			this.blockList.remove(user);
		} else {
			throw new RuntimeException("ERROR: The user cannot unblock another user not previously blocked");
		}
	}
	
	public boolean isBlocked(RegisteredUser user) {
		return blockList.contains(user);
	}
	
	/*Follow list*/
	public void follow(RegisteredUser user) {
		if (this.followList.contains(user)) {
			throw new RuntimeException("ERROR: The user cannot follow another user already followed");
		} else {
			this.followList.add(user);
		}
	}
	
	public void unfollow(RegisteredUser user) {
		if (this.followList.contains(user)) {
			this.followList.remove(user);
		} else {
			throw new RuntimeException("ERROR: The user cannot unfollow a user not previously followed");
		}
	}
	
	public boolean isFollowed(RegisteredUser user) {
		return followList.contains(user);
	}
	
	/*Chips*/
	public static final int CREATE_NEW_RECIPE = 1;
	public static final int QUESTION_OR_COMMENT = 0;
	public void obtainChips(int i) {
		if (i == CREATE_NEW_RECIPE) {
			setChips(getChips() + 20);
		} else {
			setChips(getChips() + 10);
		}
	}
	
	public int spendChips(int amount) {
		if (this.getChips() - amount >= 0) {
			this.setChips(this.getChips() - amount);
			return this.getChips();
		} else {
			throw new RuntimeException("ERROR: The user cannot spend more chips that it has");
		}
	}

	public int getChips() {
		return chips;
	}

	public void setChips(int chips) {
		this.chips = chips;
	}
	
	//TODO add and get for comment and question
	
	//Por ahora no vamos a usar las fotos
	/*
	public void uploadRecipePhoto(Recipe recipe, RecipePhoto photo) {
		if (recipe.getPhotosList() == null) {
			List<RecipePhoto> newList = new ArrayList<>();
			newList.add(photo);
			recipe.setPhotosList(newList);
		} else {
			if (recipe.getPhotosList().contains(photo)) {
				throw new RuntimeException("ERROR: The user cannot upload the same recipe photo");
			} else {
				recipe.getPhotosList().add(photo);
			}
		}
	}

	public void deleteRecipePhoto(Recipe recipe, RecipePhoto photo) {
		if (recipe.getPhotosList() == null || !recipe.getPhotosList().contains(photo)) {
			throw new RuntimeException("ERROR: The user cannot delete a recipe photo not previously done");
		} else {
			recipe.getPhotosList().remove(photo);
		}
	}
	
	*/
}