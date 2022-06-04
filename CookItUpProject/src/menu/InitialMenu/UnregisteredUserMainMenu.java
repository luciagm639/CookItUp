package menu.InitialMenu;

import menu.ExitOption;
import menu.Menu;
import menu.InitialMenu.UnregisteredUser.ShowAllRecipesOption;
import user.UserInterface;

public class UnregisteredUserMainMenu {
	
	public UnregisteredUserMainMenu() {
		UserInterface uInterface = new UserInterface();
		
		Menu menu = new Menu("Main Menu", uInterface);
		
		menu.addOption(new ShowAllRecipesOption());
		menu.addOption(new ExitOption());
		
		menu.drawMenu();
	}
}
