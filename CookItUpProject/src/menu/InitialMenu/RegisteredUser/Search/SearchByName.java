package menu.InitialMenu.RegisteredUser.Search;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import menu.Interface;
import menu.Menu;
import menu.Option;
import menu.Show5;
import menu.InitialMenu.RegisteredUser.Recipes.SelectRecipe;
import recipe.Recipe;
import user.RegisteredUserInterface;

public class SearchByName extends Show5<Recipe> {
	
	public SearchByName() {
		super("Search recipes with a name");
	}
	
	protected SearchByName(SearchByName s, boolean isPrev) {
		super(s, isPrev);
	}

	@Override
	public void executeOption(Interface inter, Scanner lector, Menu prevMenu) {
		RegisteredUserInterface regInterface = Interface.toRegisteredUserInterface(inter);

		System.out.println("What is the name of the recipe you want to search");
		String text = lector.nextLine();
		System.out.println("Do you want to see nonfollowed user recipes");
		System.out.println("Y/N");
		Boolean followed = false;
		if (lector.nextLine().equalsIgnoreCase("y")) {
			followed = true;
		}
		
		setList(regInterface.lookForRecipe(false, text, followed));
		setPrevMenu(prevMenu);		
		
		SearchByName prevPage = null;
		if (hasPrevPage())
			prevPage = new SearchByName(this, true);
		SearchByName nextPage = null;
		if (hasNextPage())
			nextPage = new SearchByName(this, false);
		List<Option> options = new LinkedList<>();
		options.add(new SelectRecipe(this));
		execute(regInterface, lector, 0, prevPage, nextPage, options);
	}

}
