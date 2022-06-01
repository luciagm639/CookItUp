package menu.PrincipalMenu;

import java.util.Scanner;

import menu.GoBack;
import menu.Menu;
import menu.Show5Recipes;
import menu.RecipeListMenu.NextPageOption;
import menu.RecipeListMenu.SelectRecipeOption;
import user.RegisteredUserInterface;

public class ShowAllRecipesOption extends Show5Recipes {
	String text = "See all recepies";
	int j;
	
	//We have created this variable to see if it is a register user or it is not. If it is 1 it is a register user
	public ShowAllRecipesOption(int j) {
		super();
		this.j = j;
	}

	@Override
	public void exucuteOption(RegisteredUserInterface regInterface, Scanner lector, Menu prevMenu) {
		int i=0;
		int pagina = -1;
		if(regInterface.getAllRecipes().size()>0) {
			i = show5Recipe(i, regInterface.getAllRecipes(),pagina);
			pagina++;
			Menu RecipeListMenu = new Menu("What do you want to do", regInterface);
			RecipeListMenu.addOption(new SelectRecipeOption(i, pagina,this.j));
			RecipeListMenu.addOption(new NextPageOption(i, pagina, this.j));
			RecipeListMenu.addOption(new GoBack(prevMenu));
			RecipeListMenu.drawMenu();
		} else {
			System.err.println("There are no recipes in the system");
		}
	}

	@Override
	public String getText() {
		return text;
	}

}