package menu.InitialMenu.RegisteredUser;

import java.util.Scanner;

import menu.GoBack;
import menu.Interface;
import menu.Menu;
import menu.Option;
import menu.InitialMenu.RegisteredUser.Profile.ShowOwnRecipesOption;
import user.RegisteredUserInterface;

public class ShowProfileOption implements Option {
	String text = "See own profile";
	private RegisteredUserInterface regInterface;
	
	@Override
	public void executeOption(Interface inter, Scanner lector, Menu prevMenu) {
		regInterface = Interface.toRegisteredUserInterface(inter);
		
		Menu menuProfile = new Menu("What do you want to do with your profile?", regInterface, this);
		menuProfile.addOption(new ShowOwnRecipesOption());
		menuProfile.addOption(new GoBack(prevMenu));
		menuProfile.drawMenu();

	}

	@Override
	public String getText() {
		return text;
	}
	
	@Override
	public String toString() {
		return regInterface.showProfile();
	}
}
