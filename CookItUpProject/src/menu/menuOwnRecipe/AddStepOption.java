package menu.menuOwnRecipe;

import java.util.Scanner;

import menu.Menu;
import menu.Option;
import user.RegisteredUserInterface;

public class AddStepOption implements Option{
	String text = "Add step";
	int RecipeNumber = 0;

	public AddStepOption(int RecipeNumber) {
		this.RecipeNumber = RecipeNumber;
	}
	@Override
	public void exucuteOption(RegisteredUserInterface regInterface, Scanner lector, Menu prevMenu) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return text;
	}

}
