package main;

import user.RegisteredUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import recipe.Ingredient;
import system.MySystem;

public class prevMain {

	public static void main(String[] args) {
		MySystem Mysystem = new MySystem();
		RegisteredUser ourUser = new RegisteredUser("Javi",0 ,"contrasena");
		Mysystem.addUser(ourUser);
		
		System.out.println("Welcome to CookItUp");
		while(true) {
			System.out.println("Press 1 if you want to see all the recipes press 2 if you want to see your profile o pulsa 3 si quiere crear una nueva receta");
			Scanner lector = new Scanner(System.in);
			int valor = lector.nextInt();
			
			switch(valor) {
			case 1 : 
				System.out.println(Mysystem.showRecipes(Mysystem.getAllRecipes()));
				break;
			case 2 :
				System.out.println(ourUser.showProfile());
				System.out.println("Para ver sus recetas pulse 1");
				System.out.println("Para volver atras pulse 2");
				valor = lector.nextInt();
				switch(valor) {
				case 1 : 
					System.out.println(Mysystem.showRecipes(ourUser.getRecipesList()));
					break;
				case 2 :
					break;
				}
				break;
			case 3 :
				lector.nextLine();
				System.out.println("Escriba el nombre de la receta");
				String name = lector.nextLine();
				while(Mysystem.findRecipe(name) != null) {
					System.out.println("This name is already in use");
					name = lector.next();
				}
				List<Ingredient> ingredientsList = new ArrayList<>();
				System.out.println("Now write down the ingredients , write 0 to finish this step");
				name = lector.next();
				while(!name.equals("0")) {
					Ingredient ingredient = new Ingredient(name);
					ingredientsList.add(ingredient);
					name = lector.next();
				}
			}
		}
	}
}
