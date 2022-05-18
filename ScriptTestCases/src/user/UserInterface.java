package user;

import java.util.ArrayList;
import java.util.List;

import recipe.Recipe;
import system.MySystem;

public class UserInterface {
	
	MySystem system;
	
	public UserInterface() {
		this.system = new MySystem();
	}
	
	public UserInterface(MySystem system) {
		this.system = system;
	}
	
	public RegisteredUserInterface logIn(String name, String password) {
		RegisteredUser user = system.findUser(name);
		if (user == null) {
			System.out.println("Name was not found");
		}
		if (user.getPassword().equals(password)) {
			System.out.println("You logged in");
			return new RegisteredUserInterface(user, system);
		}
		return null;
	}
	
	public RegisteredUserInterface registerNewAccount(String name, String password) {
		RegisteredUser user = system.findUser(name);
		if (user == null) {
			if (validatePassword(password)) {
				user = new RegisteredUser(name, system.nextUserId(), password);
				system.addUser(user);
				System.out.println("You have successfully created a new account");
				return new RegisteredUserInterface(user, system);
			}
			else {
				System.out.println("Password is not safe enough");
			}
		}
		else {
			System.out.println("The requested user name is already in use");
		}
		return null;
	}
	
	public boolean validatePassword(String name) {
		//8 caracteres minimo
		return name.length() >= 8;
	}
	
	public String showAllRecipes() {
		return system.showRecipes(system.getAllRecipes());
	}
	
	public List<Recipe> getAllRecipes() {
		return system.getAllRecipes();
	}
}
