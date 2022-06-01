package menu.RecipeListMenu;

import java.util.Scanner;

import menu.GoBack;
import menu.Menu;
import menu.Option;
import menu.Show5Recipes;
import menu.PrincipalMenu.ShowAllRecipesOption;
import user.RegisteredUserInterface;

public class NextPageOption extends Show5Recipes {
	String text = "Next Page";
	int i; 
	int pagina;
	int UserType;
	Option NoMore = new ShowAllRecipesOption(UserType);
	

	public NextPageOption(int i, int pagina, int j) {
		this.i = i;
		this.pagina = pagina;
		UserType = j;
	}

	@Override
	public void exucuteOption(RegisteredUserInterface regInterface, Scanner lector, Menu prevMenu) {
		if(i < regInterface.getAllRecipes().size()) {
			i = show5Recipe(i, regInterface.getAllRecipes(), pagina);
			pagina++;
			Menu RecipeListMenu = new Menu("What do you want to do", regInterface);
			RecipeListMenu.addOption(new ModifyRecipeOption(i, pagina));
			RecipeListMenu.addOption(new NextPageOption(i, pagina, UserType));
			RecipeListMenu.addOption(new GoBack(prevMenu));
			RecipeListMenu.drawMenu();
		}else {
			System.out.println("There are no more recipes");
			NoMore.exucuteOption(regInterface, lector, prevMenu);
		}
	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return text;
	}

}
