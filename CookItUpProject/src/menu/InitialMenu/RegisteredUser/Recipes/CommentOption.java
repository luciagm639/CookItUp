package menu.InitialMenu.RegisteredUser.Recipes;

import java.util.Scanner;

import menu.Interface;
import menu.Menu;
import menu.Option;
import recipe.Recipe;
import user.RegisteredUserInterface;

public class CommentOption implements Option {
	String text = "Comment";
	Recipe recipe;

	public CommentOption(Recipe r) {
		this.recipe = r;
	}

	@Override
	public void executeOption(Interface inter, Scanner lector, Menu prevMenu) {
		RegisteredUserInterface regInterface = Interface.toRegisteredUserInterface(inter);
		String cadena = lector.nextLine();
		regInterface.makeComment(cadena,recipe);
	}

	@Override
	public String getText() {
		return text;
	}

}
