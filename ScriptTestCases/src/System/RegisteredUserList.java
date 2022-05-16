package System;

import java.util.ArrayList;
import java.util.List;

import User.RegisteredUser;

public class RegisteredUserList {
	
	private List<RegisteredUser> registeredUserList;
	
	public RegisteredUserList() {
		registeredUserList = new ArrayList<RegisteredUser>();
	}
	
	public RegisteredUser findUser(String name) {
		for (RegisteredUser user : registeredUserList) {
			if (user.getName().equals(name)) {
				return user;
			}
		}
		return null;
	}

	public void addToList(RegisteredUser user) {
		registeredUserList.add(user);		
	}

	public void remove(RegisteredUser user) {
		registeredUserList.remove(user);
		
	}
}
