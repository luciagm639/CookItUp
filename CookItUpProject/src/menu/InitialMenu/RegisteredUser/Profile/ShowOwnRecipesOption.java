package menu.InitialMenu.RegisteredUser.Profile;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import menu.GoBack;
import menu.Interface;
import menu.Menu;
import menu.Option;
import menu.Show5;
import menu.InitialMenu.RegisteredUser.ShowAllRecipesOption;
import menu.InitialMenu.RegisteredUser.Recipes.SelectRecipe;
import recipe.Recipe;
import user.RegisteredUserInterface;

public class ShowOwnRecipesOption extends Show5<Recipe> {
	
	public ShowOwnRecipesOption() {
		super("See own recipes");
	}
	
	public ShowOwnRecipesOption(ShowOwnRecipesOption s, boolean isPrev) {
		super(s, isPrev);
	}

	@Override
	public void executeOption(Interface inter, Scanner lector, Menu prevMenu) {
		RegisteredUserInterface regInterface = Interface.toRegisteredUserInterface(inter);
		setList(regInterface.getUserRecipes());
		setPrevMenu(prevMenu);
		
		ShowOwnRecipesOption prevPage = null;
		if (hasPrevPage())
			prevPage = new ShowOwnRecipesOption(this, true);
		ShowOwnRecipesOption nextPage = null;
		if (hasNextPage())
			nextPage = new ShowOwnRecipesOption(this, false);
		List<Option> options = new LinkedList<>();
		options.add(new ModifyRecipeOption(this));
		execute(inter, lector, 0, prevPage, nextPage, options);
	}
}
