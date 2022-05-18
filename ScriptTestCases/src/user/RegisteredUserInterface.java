package user;

import java.awt.Image;
import java.util.List;

import recipe.*;
import report.*;
import system.MySystem;

public class RegisteredUserInterface extends UserInterface {
	
	private RegisteredUser reg;
	
	public RegisteredUserInterface (RegisteredUser ru, MySystem system) {
		super(system);
		this.reg = ru;
	}
	
	public UserInterface logOut() {
		System.out.println("The account has been logged out.");
		UserInterface ui = new UserInterface(system);
		return ui;
	}
	
	public void uploadPhoto(Image ph, Recipe r) {
		RecipePhoto photo = new RecipePhoto(ph,reg);
		r.addRecipePhoto (photo);
		reg.obtainChips(2);
	}
	
	public Recipe createRecipe(String title) {
		if (system.findRecipe(title) != null)
			return null;
		Recipe r = new Recipe(title, reg);
		system.addRecipe(r);
		reg.addRecipe(r);
		return r;
	}
	
	public void deleteRecipe(Recipe recipe) {
		if (recipe.checkOwner(reg)) {
			system.removeRecipe(recipe);
			reg.deleteRecipe(recipe);
		}
	}
	
	public boolean addStep (int time, String desc, int order, Recipe recipe) {
		boolean success = false;
		if (recipe.checkOwner(reg)) {
			recipe.addStep(new Step(time, desc), order);
			success = true;
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
			Ingredient ing = new Ingredient(name);
			system.addIngredient(ing);
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
	
	public void askQuestion(String textQuestion, Recipe recipe) {
		Question question = new Question(reg, textQuestion);
		recipe.addQuestion(question);
	}
	
	public void makeComment(String textComment, Recipe recipe) {
		Comment comment = new Comment(reg, textComment);
		recipe.addComment(comment);
	}
	
	public void reportRecipe(String justification, Recipe recipe) {
		Report report = new ReportRecipe(reg, justification, recipe);
		system.addReport(report);
	}
	
	public void reportMessage(String justification, Message message) {
		Report report = new ReportMessage(reg, justification, message);
		system.addReport(report);
	}
	
	public void reportUser(String justification, RegisteredUser User) {
		Report report = new ReportUser(reg, justification, User);
		system.addReport(report);
	}
	
	//TODO
	public UserInterface deleteOwnAccount() {	
		return new UserInterface(system);
	}

	public String showProfile() {
		return reg.showProfile();
	}
	
	public String showUserRecipes() {
		return system.showRecipes(reg.getRecipesList());
	}
	
	public List<Recipe> getUserRecipes() {
		return reg.getRecipesList();
	}
}
