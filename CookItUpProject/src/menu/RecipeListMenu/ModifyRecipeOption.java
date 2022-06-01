package menu.RecipeListMenu;

import java.util.Scanner;

import menu.GoBack;
import menu.Menu;
import menu.Option;
import menu.menuOwnRecipe.AddStepOption;
import menu.menuOwnRecipe.DeleteRecipeOption;
import menu.menuOwnRecipe.DeleteStepOption;
import user.RegisteredUserInterface;

public class ModifyRecipeOption implements Option {
	String text = "Modify one of this recipes";
	int pagina;
	int i;
	
	public ModifyRecipeOption(int i, int pagina) {
		this.i = i;
		this.pagina = pagina;
	}

	@Override
	public void exucuteOption(RegisteredUserInterface regInterface, Scanner lector, Menu prevMenu) {
		System.out.println("Select one recipe pressing it number");
		int valor = Integer.parseInt(lector.nextLine());
		if(valor <= i-(5*pagina)) {
			regInterface.getUserRecipes().get(valor-1).toString();
			Menu menuOwnRecipe = new Menu("What would you like to do with recipe", regInterface);
			menuOwnRecipe.addOption(new DeleteRecipeOption(valor));
			menuOwnRecipe.addOption(new DeleteStepOption(valor));
			menuOwnRecipe.addOption(new AddStepOption(valor));
			menuOwnRecipe.addOption(new GoBack(prevMenu));
			menuOwnRecipe.drawMenu();
		}else {
			
		}
	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return text;
	}

}
