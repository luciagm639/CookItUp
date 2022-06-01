package menu.RecipeExtensionMenu;

import java.util.Scanner;

import menu.Menu;
import menu.Option;
import user.RegisteredUserInterface;

public class LikeOption implements Option {
	String text = "Like";
	int RecipeNumber = 0;
	
	public LikeOption(int RecipeNumber) {
		this.RecipeNumber = RecipeNumber;
	}
	
	@Override
	public void exucuteOption(RegisteredUserInterface regInterface, Scanner lector, Menu prevMenu) {
		System.err.println("Need to be implemented, this is a beta");
		
	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return text;
	}

}
