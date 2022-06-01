package menu.menuOwnRecipe;

import java.util.Scanner;

import menu.Menu;
import menu.Option;
import user.RegisteredUserInterface;

public class DeleteRecipeOption implements Option{
	String text = "Delete this recipe";
	int RecipeNumber = 0;

	public DeleteRecipeOption(int RecipeNumber) {
		this.RecipeNumber = RecipeNumber;
	}

	@Override
	public void exucuteOption(RegisteredUserInterface regInterface, Scanner lector, Menu prevMenu) {
		regInterface.deleteRecipe(regInterface.getUserRecipes().get(RecipeNumber-1));
		
	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return text;
	}

}
