package user;

import java.awt.Image;
import java.util.List;
import java.util.Set;

import gson.ClientSystem;
import recipe.*;
import report.*;
import system.data.Fridge;
import system.data.IngredientsList;

public class RegisteredUserInterface extends UserInterface {
	
	private RegisteredUser reg;
	
	public RegisteredUserInterface (RegisteredUser ru, ClientSystem system) {
		super(system);
		this.reg = ru;
	}
	
	public UserInterface logOut() {
		System.out.println("The account has been logged out.");
		UserInterface ui = new UserInterface(system);
		return ui;
	}
	
	public Recipe createRecipe(String title) {
		if (system.findRecipe(title) != null)
			return null;
		Recipe r = new Recipe(title, 0, reg);
		system.addRecipe(r);
		reg.obtainChips(RegisteredUser.CREATE_NEW_RECIPE);
		return r;
	}
	
	public void deleteRecipe(Recipe recipe) {
		if (recipe.checkOwner(reg)) {
			system.removeRecipe(recipe);
		}
	}
	
	public boolean addStep (int time, String desc, int order, Recipe recipe) {
		boolean success = false;
		if (recipe.checkOwner(reg)) {
			system.addStep(new Step(time, desc), recipe, order);
			success = true;
		}
		return success;
	}
	
	public boolean replaceStep(int time, String desc, int order, Recipe recipe) {
		boolean success = false;
		if (recipe.checkOwner(reg)) {
			if (order >= 0) {
				system.replaceStep(new Step(time, desc), recipe, order);
				success = true;
			}
		}
		return success;
	}
	
	public boolean deleteStep(int index, Recipe recipe) {
		boolean success = false;
		if (recipe.checkOwner(reg)) {
			recipe.deleteStep(index);
			success = true;
		}
		return success;
	}
	
	public boolean addIngredient (String name, Recipe recipe) {
		boolean success = false;
		if (recipe.checkOwner(reg)) {
			Ingredient ing = system.addIngredient(new Ingredient(name));
			recipe.addIngredient(ing);
			success = true;
		}
		return success;
	}
	
	public boolean deleteIngredient (String name, Recipe recipe) {
		boolean success = false;
		if (recipe.checkOwner(reg)) {
			Ingredient ing = new Ingredient(name);
			recipe.deleteIngredient(ing);
			success = true;
		}
		return success;
	}
	
	//TODO test
	public void addIngredientToFridge(String name) {
		Ingredient i = system.addIngredient(new Ingredient(name));
		reg.addIngredientToFridge(i);
	}
	
	//TODO test
	public void removeIngredientFromFridge(Ingredient i) {
		reg.deleteIngredientFromFridge(i);
	}
	
	//TODO test
	public List<Recipe> lookForRecipe(boolean useFridge, String name, boolean onlyFollowing) {
		Set<Ingredient> fridge = null;
		List<RegisteredUser> following = null;
		List<RegisteredUser> blocked = reg.getBlockList();
		if (useFridge)
			fridge = reg.getFridge();
		if (onlyFollowing)
			following = reg.getFollowList();
		return system.filter(fridge, following, blocked, name);
			
	}
	
	public boolean askQuestion(String textQuestion, Recipe recipe) {
		Question question = new Question(reg, textQuestion, recipe);
		reg.obtainChips(RegisteredUser.QUESTION_OR_COMMENT);
		return system.addQuestion(question);
	}
	
	public void makeComment(String textComment, Recipe recipe) {
		Comment comment = new Comment(reg, textComment, recipe);
		system.addComment(comment);
		reg.obtainChips(RegisteredUser.QUESTION_OR_COMMENT);
	}
	
	public void reportRecipe(String justification, Recipe recipe) {
		Report report = new ReportRecipe(reg, justification, recipe);
		system.addReport(report);
	}
	
	public void reportComment(String justification, Comment comment) {
		Report report = new ReportComment(reg, justification, comment);
		system.addReport(report);
	}
	
	public void reportQuestion(String justification, Question question) {
		Report report = new ReportQuestion(reg, justification, question);
		system.addReport(report);
	}
	
	public void reportUser(String justification, RegisteredUser User) {
		Report report = new ReportUser(reg, justification, User);
		system.addReport(report);
	}
	
	public void deleteOwnAccount() {
		system.removeUser(this.reg);
	}

	public String showProfile() {
		return reg.showProfile();
	}
	
	public String showUserRecipes() {
		return system.showUserRecipes(reg);
	}
	
	public List<Recipe> getUserRecipes() {
		return system.getUserRecipes(reg);
	}
	
	public boolean block(RegisteredUser us2) {
		return reg.block(us2);
	}

	public boolean isBlocked(RegisteredUser us2) {
		return reg.isBlocked(us2);
	}

	public boolean unblock(RegisteredUser us1) {
		return reg.unblock(us1);
	}

	public boolean follow(RegisteredUser us2) {
		return reg.follow(us2);
	}

	public boolean isFollowed(RegisteredUser us2) {
		return reg.isFollowed(us2);
	}

	public boolean unfollow(RegisteredUser us1) {
		return reg.unfollow(us1);
	}

	public int spendChips(int i) {
		return reg.spendChips(i);
	}

	public RegisteredUser getUser() {
		return reg;
	}

	public void upLoadReview(Review review) {
		system.addReview(review);
	}
	
	/**
	 * 
	 * @param r
	 * @return toStringExtended of the recipe with the same id
	 */
	public String getUpdated(Recipe r) {
		// TODO Auto-generated method stub
		return null;
	}
}
