package menu.RecipeExtensionMenu;

import java.util.Scanner;

import menu.Menu;
import menu.Option;
import user.RegisteredUserInterface;

public class QuestionOption implements Option {
	String text = "Adk a question";
	int RecipeNumber = 0;
	public QuestionOption(int RecipeNumber) {
		this.RecipeNumber = RecipeNumber;
	}

	@Override
	public void exucuteOption(RegisteredUserInterface regInterface, Scanner lector, Menu prevMenu) {
		String cadena1 = lector.nextLine();
		regInterface.askQuestion(cadena1,regInterface.getAllRecipes().get(RecipeNumber - 1));

	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return text;
	}

}
