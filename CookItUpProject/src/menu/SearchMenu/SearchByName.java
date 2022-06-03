package menu.SearchMenu;

import java.util.Scanner;

import menu.GoBack;
import menu.Menu;
import menu.Option;
import menu.RecipeListMenu.NextPageOption;
import menu.RecipeListMenu.SelectRecipeOption;
import user.RegisteredUserInterface;

public class SearchByName implements Option {
	String text = "Search recipes with a name";
	@Override
	public void exucuteOption(RegisteredUserInterface regInterface, Scanner lector, Menu prevMenu) {
		int i = 0;
		int pagina = -1;
		System.out.println("What is the name of the recipe you want to search");
		String text = lector.nextLine();
		System.out.println("Do you want to see nonfollowed user recipes");
		System.out.println("Y/N");
		Boolean followed = false;
		if (lector.nextLine().equalsIgnoreCase("y")) {
			followed = true;
		}
		i = show5Recipe(i,regInterface.lookForRecipe(false,text,followed),pagina);
		pagina++;
		Menu RecipeListMenu = new Menu("What do you want to do", regInterface);
		RecipeListMenu.addOption(new SelectRecipeOption(i, pagina,1));
		RecipeListMenu.addOption(new NextPageOption(i, pagina, 1));
		RecipeListMenu.addOption(new GoBack(prevMenu));
		RecipeListMenu.drawMenu();
	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return text;
	}

}
