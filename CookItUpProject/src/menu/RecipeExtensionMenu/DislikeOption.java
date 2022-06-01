package menu.RecipeExtensionMenu;

import java.util.Scanner;

import menu.Menu;
import menu.Option;
import user.RegisteredUserInterface;

public class DislikeOption implements Option {
	String text = "Dislike";
	int RecipeNumber = 0;
	
	public DislikeOption(int RecipeNumber) {
		this.RecipeNumber = RecipeNumber;
	}
	public void setRecipeNumber(int recipeNumber) {
		RecipeNumber = recipeNumber;
	}
	@Override
	public void exucuteOption(RegisteredUserInterface regInterface, Scanner lector, Menu prevMenu) {
		System.err.println("Need to be implemented, this is a beta");

	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return text;
	}

}
