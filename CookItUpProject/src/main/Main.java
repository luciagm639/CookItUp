package main;

import java.util.Scanner;

import menu.InitialMenu.AdministratorMainMenu;
import menu.InitialMenu.RegisteredUserMainMenu;
import menu.InitialMenu.UnregisteredUserMainMenu;
public class Main {

	public static void main(String[] args) {
		
		Scanner lector = new Scanner(System.in);
		
		System.out.println("Welcome to CookItUp,how would you like to join us?");
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
