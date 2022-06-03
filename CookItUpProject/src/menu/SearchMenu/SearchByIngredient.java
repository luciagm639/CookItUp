package menu.SearchMenu;

import java.util.Scanner;

import menu.Menu;
import menu.Option;
import user.RegisteredUserInterface;

public class SearchByIngredient implements Option {

	@Override
	public void exucuteOption(RegisteredUserInterface regInterface, Scanner lector, Menu prevMenu) {
		int i = 0;
		int pagina = -1;
		System.err.println("To finish");
		i = show5Recipe(i,regInterface.lookForRecipe(true,null,false),pagina);

	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return null;
	}

}
