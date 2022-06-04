package menu.InitialMenu.RegisteredUser.Profile;

import java.util.Scanner;

import menu.GoBack;
import menu.Interface;
import menu.Menu;
import menu.Select;
import menu.InitialMenu.RegisteredUser.Profile.ModifyRecipe.AddStepOption;
import menu.InitialMenu.RegisteredUser.Profile.ModifyRecipe.DeleteRecipeOption;
import menu.InitialMenu.RegisteredUser.Profile.ModifyRecipe.DeleteStepOption;
import menu.InitialMenu.RegisteredUser.Profile.ModifyRecipe.ReplaceStepOption;
import recipe.Recipe;
import user.RegisteredUserInterface;

public class ModifyRecipeOption extends Select<Recipe> {
	
	private Recipe r;
	
	public ModifyRecipeOption(ShowOwnRecipesOption showOwnRecipesOption) {
		super(showOwnRecipesOption, "Modify one of this recipes");
	}

	@Override
	public void executeOption(Interface inter, Scanner lector, Menu prevMenu) {
		RegisteredUserInterface regInterface = Interface.toRegisteredUserInterface(inter);
		r = select(lector);

		Menu menuOwnRecipe = new Menu("What would you like to do with recipe", regInterface, this);
		menuOwnRecipe.addOption(new DeleteRecipeOption(r, prevMenu));
		menuOwnRecipe.addOption(new DeleteStepOption(r));
		menuOwnRecipe.addOption(new AddStepOption(r));
		menuOwnRecipe.addOption(new ReplaceStepOption(r));
		menuOwnRecipe.addOption(new GoBack(prevMenu));
		menuOwnRecipe.drawMenu();
		
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("You have selected this recipe:\n");
		sb.append(r.toStringExtended());
		return sb.toString();
	}
}
