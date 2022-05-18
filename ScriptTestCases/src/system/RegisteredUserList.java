package system;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import user.RegisteredUser;

public class RegisteredUserList {
	
	private Set<RegisteredUser> registeredUserList;
	
	public RegisteredUserList() {
		registeredUserList = new HashSet<RegisteredUser>();
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
	
	public boolean contains(RegisteredUser us) {
		return registeredUserList.contains(us);
	}
	
	//TODO
	public int nextId() {
		return registeredUserList.size();
	}
}
