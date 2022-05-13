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
	}
}
