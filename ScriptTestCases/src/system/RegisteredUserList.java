package system;

import java.util.ArrayList;
import java.util.List;

import user.RegisteredUser;

public class RegisteredUserList {
	
	private List<RegisteredUser> registeredUserList;
	
	public RegisteredUserList() {
		registeredUserList = new ArrayList<RegisteredUser>();
	}

	public void add(RegisteredUser user) {
		registeredUserList.add(user);		
	}

	public void remove(RegisteredUser user) {
		registeredUserList.remove(user);
		
	}

	public RegisteredUser getUser(int id) {
		for (RegisteredUser user : registeredUserList) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}

	public RegisteredUser findUser(String name) {
		for (RegisteredUser user : registeredUserList) {
			if (user.getName().equals(name)) {
				return user;
			}
		}
		return null;
	}
}
