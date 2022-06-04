package menu.InitialMenu.RegisteredUser;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import menu.Interface;
import menu.Menu;
import menu.Option;
import menu.Show5;
import menu.InitialMenu.RegisteredUser.Recipes.SelectRecipe;
import user.RegisteredUserInterface;

import recipe.*;

public class ShowAllRecipesOption extends Show5<Recipe> {
	
	public ShowAllRecipesOption() {
		super("See all recipes");
	}
	
	protected ShowAllRecipesOption(ShowAllRecipesOption s, boolean isPrev) {
		super(s, isPrev);
	}

	@Override
	public void executeOption(Interface inter, Scanner lector, Menu prevMenu) {
		RegisteredUserInterface regInterface = Interface.toRegisteredUserInterface(inter);
		setList(regInterface.lookForRecipe(false, null, false));
		setPrevMenu(prevMenu);		
		
		ShowAllRecipesOption prevPage = null;
		if (hasPrevPage())
			prevPage = new ShowAllRecipesOption(this, true);
		ShowAllRecipesOption nextPage = null;
		if (hasNextPage())
			nextPage = new ShowAllRecipesOption(this, false);
		List<Option> options = new LinkedList<>();
		options.add(new SelectRecipe(this));
		execute(regInterface, lector, 0, prevPage, nextPage, options);
	}
}