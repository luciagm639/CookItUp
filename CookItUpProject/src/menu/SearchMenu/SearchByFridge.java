package menu.SearchMenu;

import java.util.List;
import java.util.Scanner;
import menu.Menu;
import menu.Option;
import recipe.Recipe;
import user.RegisteredUserInterface;

public class SearchByFridge implements Option {
	String text = "Search recipes you can do with your fridge's ingredients";
	@Override
	public void exucuteOption(RegisteredUserInterface regInterface, Scanner lector, Menu prevMenu) {
		int i = 0;
		int pagina = -1;
		System.out.println("Do you want to see nonfollowed user recipes");
		System.out.println("Y/N");
		Boolean followed = false;
		if (lector.nextLine().equalsIgnoreCase("y")) {
			followed = true;
		}
		//i = show5Recipe(i,regInterface.lookForRecipe(true,null,false),pagina);
		

	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return text;
	}

}
