package menu;

import java.util.Scanner;

import user.RegisteredUserInterface;

public class ExitOption implements Option {
	String text = "Exit Program";
	@Override
	public void exucuteOption(RegisteredUserInterface regInterface, Scanner lector, Menu prevMenu) {
		regInterface.close();
		System.exit(0);
		
	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return text;
	}
	

}
