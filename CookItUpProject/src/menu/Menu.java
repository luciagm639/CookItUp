package menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
	private String titulo;
	private List<Option> optionList = new ArrayList<Option>();
	private Option prevOption;
	Interface menuInterface;
	public Menu(String titulo, Interface menuInterface) {
		this.titulo = titulo;
		this.menuInterface = menuInterface;
	}
	
	public Menu(String titulo, Interface menuInterface, Option prevOption) {
		this.titulo = titulo;
		this.menuInterface = menuInterface;
		this.prevOption = prevOption;
	}
	
	public void drawMenu() {
		if (prevOption != null)
			System.out.println(prevOption);
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
			optionList.get(valor -1).executeOption(menuInterface, in, this);
			drawMenu();
		}
		catch(Exception e ) {
	       
			System.err.println("Incorrect Input, start again");
			drawMenu();
			
		}

	}
	
}
