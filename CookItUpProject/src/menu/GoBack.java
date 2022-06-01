package menu;

import java.util.Scanner;

import user.RegisteredUserInterface;

public class GoBack implements Option {
	String text = "Go back.";
	Menu previousMenu;
	
	public GoBack(Menu previousMenu) {
		this.previousMenu = previousMenu;
	}

	@Override
	public void exucuteOption(RegisteredUserInterface regInterface, Scanner lector, Menu prevMenu) {
		previousMenu.drawMenu();
	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return text;
	}

}
