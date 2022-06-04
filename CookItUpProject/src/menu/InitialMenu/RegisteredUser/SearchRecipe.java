package menu.InitialMenu.RegisteredUser;

import java.util.Scanner;


import menu.GoBack;
import menu.Interface;
import menu.Menu;
import menu.Option;
import menu.Show5;
import menu.InitialMenu.RegisteredUser.Search.SearchByFridge;
import menu.InitialMenu.RegisteredUser.Search.SearchByIngredient;
import menu.InitialMenu.RegisteredUser.Search.SearchByName;
import recipe.Recipe;
import user.RegisteredUserInterface;

public class SearchRecipe implements Option {
	
	String text = "Search recipes";

	@Override
	public void executeOption(Interface inter, Scanner lector, Menu prevMenu) {
		RegisteredUserInterface regInterface = Interface.toRegisteredUserInterface(inter);
		Menu menuSearch = new Menu("How do you want to search the recipe?", regInterface);
		menuSearch.addOption(new SearchByFridge());
		menuSearch.addOption(new SearchByName());
		menuSearch.addOption(new SearchByIngredient());
		menuSearch.addOption(new GoBack(prevMenu));
		menuSearch.drawMenu();
		
	}

	@Override
	public String getText() {
		return text;
	}
}
