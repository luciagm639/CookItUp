package menu.InitialMenu.UnregisteredUser;

import java.util.Scanner;

import menu.GoBack;
import menu.Interface;
import menu.Menu;
import menu.Select;
import menu.Show5;
import recipe.Recipe;
import user.RegisteredUserInterface;

public class SelectRecipe extends Select<Recipe> {
	
	private Recipe r;

	public SelectRecipe(Show5<Recipe> s) {
		super(s, "Select a recipe to view details");
	}

	@Override
	public void executeOption(Interface inter, Scanner lector, Menu prevMenu) {
		RegisteredUserInterface regInterface = Interface.toRegisteredUserInterface(inter);
		r = select(lector);
		
		Menu RecipeMenu = new Menu("What do you want to do with this recipe:", regInterface, this);
		//TODO add more
		RecipeMenu.addOption(new GoBack(prevMenu));
		RecipeMenu.drawMenu();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("You have selected this recipe:\n");
		sb.append(r.toStringExtended());
		return sb.toString();
	}
}
