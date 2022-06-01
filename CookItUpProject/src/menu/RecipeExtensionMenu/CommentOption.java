package menu.RecipeExtensionMenu;

import java.util.Scanner;

import menu.Menu;
import menu.Option;
import user.RegisteredUserInterface;

public class CommentOption implements Option {
	String text = "Comment";
	int RecipeNumber = 0;

	public CommentOption(int RecipeNumber) {
		this.RecipeNumber = RecipeNumber;
	}

	@Override
	public void exucuteOption(RegisteredUserInterface regInterface, Scanner lector, Menu prevMenu) {
		String cadena = lector.nextLine();
		regInterface.makeComment(cadena,regInterface.getAllRecipes().get(RecipeNumber - 1));

	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return text;
	}

}
