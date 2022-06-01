package menu.menuOwnRecipe;

import java.util.Scanner;

import menu.Menu;
import menu.Option;
import user.RegisteredUserInterface;

public class DeleteStepOption implements Option{
	String text = "Delete step";
	int RecipeNumber = 0;

	public DeleteStepOption(int RecipeNumber) {
		this.RecipeNumber = RecipeNumber;
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
