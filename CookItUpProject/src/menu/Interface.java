package menu;

import user.RegisteredUserInterface;
import user.UserInterface;

public interface Interface {
	public void close();
	
	public static RegisteredUserInterface toRegisteredUserInterface(Interface inter) {
		RegisteredUserInterface regInter = null;
		if (inter instanceof RegisteredUserInterface)
			regInter = (RegisteredUserInterface) inter;
		return regInter;
	}
	
	public static UserInterface toUserInterface(Interface inter) {
		UserInterface userInter = null;
		if (inter instanceof UserInterface)
			userInter = (UserInterface) inter;
		return userInter;
	}
}
