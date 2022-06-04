package menu.InitialMenu.RegisteredUser.Recipes;

import java.util.Scanner;

import menu.Interface;
import menu.Menu;
import menu.Option;
import recipe.Recipe;
import user.RegisteredUserInterface;

public class QuestionOption implements Option {
	String text = "Ask a question";
	Recipe recipe;
	
	public QuestionOption(Recipe r) {
		this.recipe = r;
	}

	@Override
	public void executeOption(Interface inter, Scanner lector, Menu prevMenu) {
		RegisteredUserInterface regInterface = Interface.toRegisteredUserInterface(inter);
		String cadena = lector.nextLine();
		regInterface.askQuestion(cadena,recipe);

	}

	@Override
	public String getText() {
		return text;
	}

}
