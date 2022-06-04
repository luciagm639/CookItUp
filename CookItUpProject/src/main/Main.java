package main;

import java.util.Scanner;

import menu.InitialMenu.AdministratorMainMenu;
import menu.InitialMenu.RegisteredUserMainMenu;
import menu.InitialMenu.UnregisteredUserMainMenu;
public class Main {

	public static void main(String[] args) {
		//UserInterface userInterface = new UserInterface();
		//RegisteredUserInterface regInterface = userInterface.logIn("Javi", "contrasena");
		Scanner lector = new Scanner(System.in);
		
		/*
		Menu MenuRegisteredUser = new Menu("Menu Principal", regInterface);
		Menu MenuUnRegisteredUser = new Menu("Principal Menu", regInterface);
		MenuRegisteredUser.addOption(new ShowAllRecipesOption(1));
		MenuRegisteredUser.addOption(new ShowProfileOption());
		MenuRegisteredUser.addOption(new CreateRecipeOption());
		//MenuRegisteredUser.addOption(new SearchRecipe());
		MenuRegisteredUser.addOption(new ExitOption());
		MenuUnRegisteredUser.addOption(new ShowAllRecipesOption(0));
		MenuUnRegisteredUser.addOption(new ExitOption());
		*/
		
		System.out.println("Welcome to CookItUp,how would you like to join us??");
		System.out.println("1. As a registered user");
		System.out.println("2. As an unregistered user");
		System.out.println("3. As an administrator");
		
		int valor = Integer.parseInt(lector.nextLine());
		//lector.close();
		
		switch (valor) {
		
		case 1:
			new RegisteredUserMainMenu();
			break;
		case 2:
			new UnregisteredUserMainMenu();
			break;
		case 3:
			new AdministratorMainMenu();
			break;
			
		default :
			System.out.println("a");
		}
	}

}
