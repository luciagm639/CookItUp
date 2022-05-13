package User;

import java.util.ArrayList;
import java.util.List;

import Recipe.Recipe;

public class UserInterface {
	
	List<Recipe> recipesList;
	RegisteredUserList userList;
	
	public UserInterface() {
		recipesList = new ArrayList<Recipe>();
		userList = new RegisteredUserList();
	}
	
	//tendria que ser la interfaz
	public RegisteredUser logIn(String name, String password) {
		RegisteredUser user = userList.findUser(name);
		if (user == null) {
			System.out.println("Name was not found");
		}
		if (user.getPassword().equals(password)) {
			System.out.println("You logged in");
		}
		else {
			user = null;
		}
		return user;
	}
	
	//tendria que ser la interfaz
	public RegisteredUser registerNewAccount(String name, String password) {
		RegisteredUser user = userList.findUser(name);
		if (user == null) {
			if (validatePassword(name)) {
				user = new RegisteredUser(name, password);
				userList.addToList(user);
				System.out.println("You have successfully created a new account");
				return user;
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
}
