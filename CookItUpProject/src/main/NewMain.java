package main;

import java.util.Scanner;

import menu.ExitOption;
import menu.Menu;
import menu.PrincipalMenu.CreateRecipeOption;
import menu.PrincipalMenu.ShowAllRecipesOption;
import menu.PrincipalMenu.ShowProfileOption;
import user.RegisteredUserInterface;
import user.UserInterface;
public class NewMain {

	public static void main(String[] args) {
		UserInterface userInterface = new UserInterface();
		RegisteredUserInterface regInterface = userInterface.logIn("Javi", "contrasena");
		Scanner lector = new Scanner(System.in);
		Menu MenuRegisteredUser = new Menu("Menu Principal", regInterface);
		Menu MenuUnRegisteredUser = new Menu("Principal Menu", regInterface);
		MenuRegisteredUser.addOption(new ShowAllRecipesOption(1));
		MenuRegisteredUser.addOption(new ShowProfileOption());
		MenuRegisteredUser.addOption(new CreateRecipeOption());
		MenuRegisteredUser.addOption(new ExitOption());
		MenuUnRegisteredUser.addOption(new ShowAllRecipesOption(0));
		MenuUnRegisteredUser.addOption(new ExitOption());
		System.out.println("Welcome to CookItUp,how would you like to join us??");
		System.out.println("1. As a register user");
		System.out.println("2. As an unregistered user");
		System.out.println("3. As an administrator");
		
		int valor = Integer.parseInt(lector.nextLine());
		
		
		
		
		switch (valor) {
		
		case 1: 
			MenuRegisteredUser.drawMenu();
			break;
		case 2:
			MenuUnRegisteredUser.drawMenu();
			break;
		case 3:
		//	MenuAdministrator.drawMenu();
			break;
			
		default :
			System.out.println("a");
		}

			
			
		
	}

}
