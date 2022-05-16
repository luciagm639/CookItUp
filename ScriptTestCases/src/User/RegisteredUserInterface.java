package User;

import java.awt.Image;

import Recipe.Recipe;
import Recipe.RecipePhoto;
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
	}
}
