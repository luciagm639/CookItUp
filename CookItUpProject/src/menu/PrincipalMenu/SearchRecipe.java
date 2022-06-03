package menu.PrincipalMenu;

import java.util.Scanner;

import menu.GoBack;
import menu.Menu;
import menu.Show5Recipes;
import menu.SearchMenu.SearchByFridge;
import menu.SearchMenu.SearchByIngredient;
import menu.SearchMenu.SearchByName;
import user.RegisteredUserInterface;

public class SearchRecipe extends Show5Recipes{
	String text = " Search recipe";
	@Override
	public void exucuteOption(RegisteredUserInterface regInterface, Scanner lector, Menu prevMenu) {
		Menu menuSearch = new Menu("How do you want to search the recipe?", regInterface);
		menuSearch.addOption(new SearchByFridge());
		menuSearch.addOption(new SearchByName());
		menuSearch.addOption(new SearchByIngredient());
		menuSearch.addOption(new GoBack(prevMenu));
		menuSearch.drawMenu();
		
	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return text;
	}

}
