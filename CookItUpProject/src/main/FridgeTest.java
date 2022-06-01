package main;

import user.RegisteredUserInterface;
import user.UserInterface;

public class FridgeTest {

	public static void main(String[] args) {
		UserInterface ui = new UserInterface();
		
		RegisteredUserInterface ri = ui.logIn("Inbal", "password");
		
		//ri.addIngredientToFridge("Huevo");
		
		System.out.println(ri.recipesWithIngredientsFromFridge());
		
		ri.close();
	}

}
