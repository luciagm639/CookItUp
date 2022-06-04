package menu;

import java.util.Scanner;

import user.RegisteredUserInterface;

public class ExitOption implements Option {
	String text = "Exit Program";
	@Override
	public void executeOption(Interface regInterface, Scanner lector, Menu prevMenu) {
		regInterface.close();
		System.exit(0);
		
	}

	@Override
	public String getText() {
		return text;
	}
	

}
