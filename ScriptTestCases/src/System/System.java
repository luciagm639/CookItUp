package System;

import java.util.ArrayList;
import java.util.List;

import Recipe.Recipe;
import User.RegisteredUser;
import User.RegisteredUserList;

public class System {
	
	List<Recipe> recipesList;
	RegisteredUserList userList;
	
	public System() {
		//deberia acceder a los datos
		recipesList = new ArrayList<Recipe>();
		userList = new RegisteredUserList();
	}
	
	public void addUser(RegisteredUser user) {
		userList.addToList(user);
	}

}
