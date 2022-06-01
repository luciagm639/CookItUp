package menu.PrincipalMenu;

import java.util.Scanner;

import menu.Menu;
import menu.Option;
import recipe.Recipe;
import user.RegisteredUserInterface;

public class CreateRecipeOption implements Option {
	String texto = "Create recipe";
	
	@Override
	public void exucuteOption(RegisteredUserInterface regInterface, Scanner lector, Menu prevMenu) {
		int cont = 0;
		System.out.println("Escriba el nombre de la receta");
		String cadena = lector.nextLine();
		
		Recipe recipe = regInterface.createRecipe(cadena);

		while(recipe == null) {
			System.out.println("This name is already in use");
			cadena = lector.nextLine();
			recipe = regInterface.createRecipe(cadena);
		}
		System.out.println("Now write down the ingredients , write 0 to finish this step");
		cadena = lector.nextLine();
		
		while(!cadena.equals("0")) {//HASTA AQUI
			regInterface.addIngredient(cadena, recipe);
			cadena = lector.nextLine();
		}
		System.out.println("Now write down the steps you need to follow.");
		System.out.println("Write a small description");
		cadena = lector.nextLine();
		System.out.println("Write its time in this step");
	    int valor = Integer.parseInt(lector.nextLine());
		regInterface.addStep(valor, cadena, cont, recipe);

		
		do {
			System.out.println("Add an other step Y/N");
			cadena = lector.nextLine();
			if (!cadena.equalsIgnoreCase("y")  && !cadena.equalsIgnoreCase("n")){
				System.err.println("Invalid input,try again");
			}
		}while(!cadena.equalsIgnoreCase("y") && !cadena.equalsIgnoreCase("n"));

		
		while(cadena.equalsIgnoreCase("y")) {
			
			System.out.println("Write a small description");
			cadena = lector.nextLine();
		
			System.out.println("Write its time in this step");
			valor = Integer.parseInt(lector.nextLine());
			regInterface.addStep(valor, cadena, cont, recipe);
			
			do {
				System.out.println("Add an other step Y/N");
				cadena = lector.nextLine();
				if (!cadena.equalsIgnoreCase("y") && !cadena.equalsIgnoreCase("n")){
					System.err.println("Invalid input,try again");
				}
			}while(!cadena.equalsIgnoreCase("y") && !cadena.equalsIgnoreCase("n"));


			
		}
		
	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return texto;
	}
	
}
