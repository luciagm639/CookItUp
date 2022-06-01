package menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import user.RegisteredUserInterface;

public class Menu {
	private String titulo;
	private List<Option> optionList = new ArrayList<Option>();;
	RegisteredUserInterface regInterface;
	public Menu(String titulo, RegisteredUserInterface regInterface) {
		this.titulo = titulo;
		this.regInterface = regInterface;
	}
	
	public void drawMenu() {
		System.out.println("-------------------");
		System.out.println(titulo);
		for(int i = 0; i < optionList.size(); i++) {
			System.out.println((i+1) + ". " + optionList.get(i).getText());
		}
		selectOption();
		
	}
	
	public void addOption(Option option) {
		optionList.add(option);
	}
	
	public void selectOption() {
		Scanner in = new Scanner(System.in);
		try {
			int valor = Integer.parseInt(in.nextLine());
			optionList.get(valor -1).exucuteOption(regInterface, in, this);
			drawMenu();
		}
		catch(Exception e ) {
	       
			System.err.println("Incorrect Input, start again");
			drawMenu();
			
		}

	}
	
}
