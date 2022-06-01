package menu;

import java.util.Scanner;

import user.RegisteredUserInterface;

public interface Option {
	public  String text = "";
	public void exucuteOption(RegisteredUserInterface regInterface, Scanner lector, Menu prevMenu);
	public String getText();
}
