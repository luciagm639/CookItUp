package menu.InitialMenu.RegisteredUser.Recipes;

import java.util.Scanner;

import menu.Interface;
import menu.Menu;
import menu.Option;
import recipe.Recipe;
import recipe.Review;
import user.RegisteredUserInterface;

public class LikeOption implements Option {
	String text = "Like";
	Recipe recipe;
	
	public LikeOption(Recipe r) {
		this.recipe = r;
	}
	
	@Override
	public void executeOption(Interface inter, Scanner lector, Menu prevMenu) {
		RegisteredUserInterface regInterface = Interface.toRegisteredUserInterface(inter);
		Review review = new Review(regInterface.getUser(), true,recipe);
		regInterface.upLoadReview(review);

		
	}

	@Override
	public String getText() {
		return text;
	}

}
