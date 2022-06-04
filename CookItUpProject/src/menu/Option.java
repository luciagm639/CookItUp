package menu;

import java.util.Scanner;

import user.RegisteredUserInterface;

public interface Option {
	public  String text = "";
	public void executeOption(Interface menuInterface, Scanner lector, Menu prevMenu);
	public String getText();
}
