package User;

import java.awt.Image;

import Recipe.Recipe;
import Recipe.RecipePhoto;

public class RegisteredUserInterface {
	private RegisteredUser reg;
	
	
	public RegisteredUserInterface (RegisteredUser ru) {
		this.reg=ru;
	}
	public UserInterface logOut() {
		System.out.println("The account has been logged out.");
		UserInterface ui = new UserInterface();
		return ui;
	}
	public void uploadPhoto(Image ph, Recipe r) {
		RecipePhoto photo = new RecipePhoto(ph,reg);
		r.addRecipePhoto (photo);
		reg.obtainChips(2);
	}
	public void createRecipe(String title) {
		
	}
	
	public void addStep (int time, String desc, int order, Recipe recipe) {
		recipe.checkOwner(reg);
	}
	
	
}
