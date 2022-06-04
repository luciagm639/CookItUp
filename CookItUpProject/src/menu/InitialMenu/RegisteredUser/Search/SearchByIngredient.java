package menu.InitialMenu.RegisteredUser.Search;

import java.util.Scanner;

import menu.Interface;
import menu.Menu;
import menu.Option;
import user.RegisteredUserInterface;

public class SearchByIngredient implements Option {

	@Override
	public void executeOption(Interface inter, Scanner lector, Menu prevMenu) {
		RegisteredUserInterface regInterface = Interface.toRegisteredUserInterface(inter);
		int i = 0;
		int pagina = -1;
		System.err.println("To finish");
		//i = show5Recipe(i,regInterface.lookForRecipe(true,null,false),pagina);

	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return null;
	}

}
