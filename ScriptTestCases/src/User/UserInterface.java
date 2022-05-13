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
	

}
