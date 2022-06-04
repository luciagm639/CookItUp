package menu.InitialMenu.RegisteredUser.Profile.ModifyRecipe;

import java.util.Scanner;

import menu.Interface;
import menu.Menu;
import menu.Option;
import recipe.Recipe;
import user.RegisteredUserInterface;

public class DeleteStepOption implements Option {
	String text = "Delete step";
	Recipe recipe;

	public DeleteStepOption(Recipe r) {
		this.recipe = r;
	}
	
	@Override
	public void executeOption(Interface inter, Scanner lector, Menu prevMenu) {
		RegisteredUserInterface regInterface = Interface.toRegisteredUserInterface(inter);
		System.out.println("What step do you want to delete?");

		int num = Integer.parseInt(lector.nextLine()) ;

		regInterface.deleteStep(num-1, recipe);
	}

	@Override
	public String getText() {
		return text;
	}

}
