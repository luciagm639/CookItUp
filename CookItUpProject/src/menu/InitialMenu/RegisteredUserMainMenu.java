package menu.InitialMenu;

import menu.ExitOption;
import menu.Menu;
import menu.InitialMenu.RegisteredUser.CreateRecipeOption;
import menu.InitialMenu.RegisteredUser.SearchRecipe;
import menu.InitialMenu.RegisteredUser.ShowAllRecipesOption;
import menu.InitialMenu.RegisteredUser.ShowProfileOption;
import user.RegisteredUserInterface;
import user.UserInterface;

public class RegisteredUserMainMenu {
	
	public RegisteredUserMainMenu() {
		RegisteredUserInterface regInterface = logIn();
		
		Menu menu = new Menu("Main Menu", regInterface);
		menu.addOption(new ShowAllRecipesOption());
		menu.addOption(new ShowProfileOption());
		menu.addOption(new CreateRecipeOption());
		menu.addOption(new SearchRecipe());
		menu.addOption(new ExitOption());
		
		menu.drawMenu();
	}
	
	public RegisteredUserInterface logIn() {
		//TODO
		return new UserInterface().logIn("Javi", "contrasena");
	}
}
