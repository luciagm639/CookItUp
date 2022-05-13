package User;

import java.util.ArrayList;
import java.util.List;

public class RegisteredUserList {
	
	List<RegisteredUser> registeredUserList = new ArrayList<RegisteredUser>();
	
	RegisteredUser findUser(String name) {
		for (RegisteredUser user : registeredUserList) {
			if (user.name.equals(name)) {
				return user;
			}
		}
		return null;
	}

	public void addToList(RegisteredUser user) {
		registeredUserList.add(user);		
	}
}
