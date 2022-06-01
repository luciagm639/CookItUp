package menu.PrincipalMenu;

import java.util.Scanner;

import menu.GoBack;
import menu.Menu;
import menu.Show5Recipes;
import menu.menuProfile.ShowOwnRecipesOption;
import user.RegisteredUserInterface;

public class ShowProfileOption extends Show5Recipes {
	String text = "See own profile";
	
	
	@Override
	public void exucuteOption(RegisteredUserInterface regInterface, Scanner lector, Menu prevMenu) {
		System.out.println(regInterface.showProfile());
		Menu menuProfile = new Menu("What do you want to do with your profile?", regInterface);
		menuProfile.addOption(new ShowOwnRecipesOption());
		menuProfile.addOption(new GoBack(prevMenu));
		menuProfile.drawMenu();

	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return text;
	}

}
