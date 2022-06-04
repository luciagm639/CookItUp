package menu.InitialMenu.RegisteredUser.Profile.ModifyRecipe;

import java.util.Scanner;

import menu.Interface;
import menu.Menu;
import menu.Option;
import recipe.Recipe;
import user.RegisteredUserInterface;

public class DeleteRecipeOption implements Option {
	String text = "Delete this recipe";
	Recipe recipe;
	Menu prev;

	public DeleteRecipeOption(Recipe r, Menu prev) {
		this.recipe = r;
		this.prev = prev;
	}

	@Override
	public void executeOption(Interface inter, Scanner lector, Menu prevMenu) {
		RegisteredUserInterface regInterface = Interface.toRegisteredUserInterface(inter);
		regInterface.deleteRecipe(recipe);
		System.out.println("You deleted the recipe");
		prev.drawMenu();
	}

	@Override
	public String getText() {
		return text;
	}

}
