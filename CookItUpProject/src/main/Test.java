package main;

import administrator.AdministratorInterface;
import recipe.Recipe;
import system.MySystem;
import user.RegisteredUser;
import user.RegisteredUserInterface;
import user.UserInterface;

public class Test extends MySystem {
	
	public static void main(String[] args) {
		Test system = new Test();
		//system.fillSystemTesting();
		System.out.println(system.getAllRecipes().get(0).toStringExtended());
		
		system.close();
	}
	
	public void fillSystemTesting() {
		
		close();
		//Create new accounts
		UserInterface ui = new UserInterface(this);
		addUser(new RegisteredUser("[deleted]", 0, null, 0, false));
		RegisteredUserInterface ius1 = ui.registerNewAccount("Alfonso", "password");
		RegisteredUserInterface ius2 = ui.registerNewAccount("Inbal", "password");
		RegisteredUserInterface ius3 = ui.registerNewAccount("Jordan", "password");
		RegisteredUserInterface ius4 = ui.registerNewAccount("Jorge", "password");
		
		Recipe recipe1 = ius4.createRecipe("Tortilla de papa");
		ius4.addIngredient("Patata", recipe1);
		ius4.addIngredient("Huevo", recipe1);
		
		Recipe recipe2 = ius4.createRecipe("Huevo frito");
		ius4.addIngredient("Huevo", recipe2);
		
		ius1.makeComment("Esta bueno pero le pondria mas tiempo de coccion", recipe1);
		
		ius1.askQuestion("¿De qué tamaño son los huevos que usas?", recipe1);
		
		ius4.addStep(10, "Batir los huevos", -1,  recipe1);
		ius4.addStep(10, "Batir los huevos", -1,  recipe2);
		ius4.addStep(30, "Cortar la patata", -1,  recipe1);
		
		ius1.reportRecipe("Todos saben hacer un huevo frito", recipe2);
		ius3.reportUser("Me cae mal", ius2.getUser());
		ius4.reportComment("Es una tonteria", getComment(1));
		
		AdministratorInterface iadm = ui.registerNewAdminAccount("Administrator", "AdminPassword");
		
	}
	
}
