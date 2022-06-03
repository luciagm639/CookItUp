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
		String text;
		System.out.println("Write a small description");
		text = lector.nextLine();
		System.out.println("Write its time in this step");
	    int valor = Integer.parseInt(lector.nextLine());
		regInterface.addStep(valor, text, regInterface.getUserRecipes().get(RecipeNumber-1).getStepsList().size(), regInterface.getUserRecipes().get(RecipeNumber-1));
	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return text;
	}

}
