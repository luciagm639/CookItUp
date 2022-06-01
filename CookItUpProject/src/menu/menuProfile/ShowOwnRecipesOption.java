package menu.menuProfile;

import java.util.Scanner;

import menu.GoBack;
import menu.Menu;
import menu.Show5Recipes;
import menu.RecipeListMenu.ModifyRecipeOption;
import menu.RecipeListMenu.NextPageOption;
import user.RegisteredUserInterface;

public class ShowOwnRecipesOption extends Show5Recipes{
	String text = "See own recipes";
	@Override
	public void exucuteOption(RegisteredUserInterface regInterface, Scanner lector, Menu prevMenu) {
		int i=0;
		int pagina = -1;
		if(regInterface.getUserRecipes().size()>0) {
			i = show5Recipe(i, regInterface.getUserRecipes(),pagina);
			pagina++;
			Menu RecipeListMenu = new Menu("What do you want to do", regInterface);
			RecipeListMenu.addOption(new ModifyRecipeOption(i, pagina));
			RecipeListMenu.addOption(new NextPageOption(i, pagina, 1));
			RecipeListMenu.addOption(new GoBack(prevMenu));
			RecipeListMenu.drawMenu();
		} else {
			System.out.println("You dont have recipes");
		}
	}
		
	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return text;
	}

}
