package menu.InitialMenu.RegisteredUser.Recipes;

import java.util.Scanner;

import menu.Interface;
import menu.Menu;
import menu.Option;
import recipe.Recipe;
import user.RegisteredUserInterface;

public class LikeOption implements Option {
	String text = "Like";
	Recipe recipe;
	
	public LikeOption(Recipe r) {
		this.recipe = r;
	}
	
	@Override
	public void executeOption(Interface inter, Scanner lector, Menu prevMenu) {
		System.err.println("Need to be implemented, this is a beta");
		
	}

	@Override
	public String getText() {
		return text;
	}

}
