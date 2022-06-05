package menu.InitialMenu.RegisteredUser.Profile.ModifyRecipe;

import java.util.Scanner;

import menu.Interface;
import menu.Menu;
import menu.Option;
import recipe.Recipe;
import user.RegisteredUserInterface;

public class AddStepOption implements Option {
	String text = "Add step";
	Recipe recipe;

	public AddStepOption(Recipe r) {
		this.recipe = r;
	}
	
	@Override
	public void executeOption(Interface inter, Scanner lector, Menu prevMenu) {
		RegisteredUserInterface regInterface = Interface.toRegisteredUserInterface(inter);
		System.out.println("In which position do you want to add the step? (0 for last step)");
		int stepPos = Integer.parseInt(lector.nextLine());
		
		System.out.println("Write a small description");
		String cadena = lector.nextLine();
	
		System.out.println("Write its time in this step");
		int time = Integer.parseInt(lector.nextLine());
		
		regInterface.addStep(time, cadena, stepPos-1, recipe);
	}

	@Override
	public String getText() {
		return text;
	}
}
