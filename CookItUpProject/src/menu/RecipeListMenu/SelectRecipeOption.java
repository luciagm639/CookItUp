package menu.RecipeListMenu;

import java.util.Scanner;

import menu.GoBack;
import menu.Menu;
import menu.Option;
import menu.RecipeExtensionMenu.CommentOption;
import menu.RecipeExtensionMenu.DislikeOption;
import menu.RecipeExtensionMenu.LikeOption;
import menu.RecipeExtensionMenu.QuestionOption;
import user.RegisteredUserInterface;

public class SelectRecipeOption implements Option {
	String text = "Select a recipe to view details";
	int pagina;
	int i;
	int userType;
	
	public SelectRecipeOption(int i, int pagina,int userType) {
		this.i = i;
		this.pagina = pagina;
		this.userType = userType;
		
		
	}

	@Override
	public void exucuteOption(RegisteredUserInterface regInterface, Scanner lector, Menu prevMenu) {
		System.out.println("Select one recipe pressing it number");
		int valor = Integer.parseInt(lector.nextLine());
		if(valor <= i-(5*pagina)) {
			regInterface.getAllRecipes().get(valor-1).toString();
			Menu RecipeExtensionMenu = new Menu("What do you want to do with this recipe:", regInterface);
			if(userType==1) {
				
				RecipeExtensionMenu.addOption(new LikeOption(valor));
				RecipeExtensionMenu.addOption(new DislikeOption(valor));
				RecipeExtensionMenu.addOption(new CommentOption(valor));

			}
			RecipeExtensionMenu.addOption(new QuestionOption(valor));
			RecipeExtensionMenu.addOption(new GoBack(prevMenu));
			RecipeExtensionMenu.drawMenu();

		}else {
			System.err.println("You selected a wrong recipe");
		}
	}


	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return text;
	}

}