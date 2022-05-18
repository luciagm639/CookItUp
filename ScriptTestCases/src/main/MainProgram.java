package main;

import user.RegisteredUser;
import user.RegisteredUserInterface;
import user.UserInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import recipe.Ingredient;
import recipe.Recipe;
import recipe.Step;
import system.MySystem;

public class MainProgram {

	public static void main(String[] args) {
		UserInterface userInterface = new UserInterface();
		
		RegisteredUserInterface regInterface = userInterface.registerNewAccount("Javi", "contrasena");
		int valor;
		String cadena;
		Scanner lector = new Scanner(System.in);
		
		System.out.println("Welcome to CookItUp");
		while(true) {
			System.out.println("Press 1 if you want to see all the recipes press 2 if you want to see your profile o pulsa 3 if u want to create a recipe");
			
			valor = Integer.parseInt(lector.nextLine());
			
			switch(valor) {
			case 1 : 
				System.out.println(regInterface.showAllRecipes());
				break;
			case 2 :
				System.out.println(regInterface.showProfile());
				System.out.println("Para ver sus recetas pulse 1");
				System.out.println("Para volver atras pulse 2");
				valor = Integer.parseInt(lector.nextLine());
				switch(valor) {
				case 1 : 
					System.out.println(regInterface.showUserRecipes());
					break;
				case 2 :
					break;
				}
				break;
			case 3 :
				
				System.out.println("Escriba el nombre de la receta");
				cadena = lector.nextLine();
				
				Recipe recipe = regInterface.createRecipe(cadena);

				while(recipe == null) {
					System.out.println("This name is already in use");
					cadena = lector.nextLine();
					recipe = regInterface.createRecipe(cadena);
				}
				System.out.println("Now write down the ingredients , write 0 to finish this step");
				cadena = lector.nextLine();
				
				while(!cadena.equals("0")) {//HASTA AQUI
					Ingredient ingredient = new Ingredient(cadena);
					recipe.addIngredient(ingredient);
					cadena = lector.nextLine();
				}
				System.out.println("Now write down the steps you need to follow.");
				System.out.println("Write a small description");
				cadena = lector.nextLine();
		
				System.out.println("Write its time in this step");
			    valor = Integer.parseInt(lector.nextLine());
				System.out.println(valor);
				Step step = new Step(valor, cadena);
				recipe.addStep(step);
				System.out.println("Add an other step Y/N");
				cadena = lector.nextLine();
				while(cadena.equalsIgnoreCase("y")) {
					
					System.out.println("Write a small description");
					cadena = lector.nextLine();
				
					System.out.println("Write its time in this step");
					valor = Integer.parseInt(lector.nextLine());
					step = new Step(valor, cadena);
					recipe.addStep(step);
					
					System.out.println("Add an other step Y/N");
					cadena = lector.nextLine();
					
				}
			}
		
		}
		
	}
	
	
}