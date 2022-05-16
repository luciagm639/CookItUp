package User;

import java.awt.Image;

import Recipe.Ingredient;
import Recipe.Recipe;
import Recipe.RecipePhoto;
import Recipe.Step;
import System.MySystem;

public class RegisteredUserInterface {
	
	private RegisteredUser reg;
	private MySystem system;
	
	public RegisteredUserInterface (RegisteredUser ru, MySystem system) {
		this.reg = ru;
		this.system = system;
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
		Recipe r = new Recipe(title, reg);
		system.addRecipe(r);
		return r;
	}
	
	public boolean addStep (int time, String desc, int order, Recipe recipe) {
		boolean success = false;
		if (recipe.checkOwner(reg)) {
			recipe.addStep(new Step(time, desc), order);
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
}
