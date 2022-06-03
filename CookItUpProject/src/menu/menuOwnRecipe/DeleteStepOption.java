package menu.menuOwnRecipe;

import java.util.Scanner;

import menu.Menu;
import menu.Option;
import recipe.Step;
import user.RegisteredUserInterface;

public class DeleteStepOption implements Option{
	String text = "Delete step";
	int RecipeNumber = 0;

	public DeleteStepOption(int RecipeNumber) {
		this.RecipeNumber = RecipeNumber;
	}
	
	@Override
	public void exucuteOption(RegisteredUserInterface regInterface, Scanner lector, Menu prevMenu) {
		System.out.println(regInterface.getUserRecipes().get(RecipeNumber-1).toString());
		System.out.println("Select the number of the step you want to delete");
		int index = Integer.parseInt(lector.nextLine());
		regInterface.deleteStep(index, regInterface.getUserRecipes().get(RecipeNumber-1));
		
	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return text;
	}

}
