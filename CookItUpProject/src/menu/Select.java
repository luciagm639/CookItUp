package menu;

import java.util.Scanner;

public abstract class Select<E> implements Option {
	
	private PageList<E> pageList;
	private String text;
	
	public Select(Show5<E> s, String text) {
		this.pageList = s.getPageList().copy();
		this.text = text;
	}
	
	public E select(Scanner lector) {
		System.out.println("Select one by pressing its number");
		int valor = Integer.parseInt(lector.nextLine());
		E e = pageList.get(valor);
		while (e == null) {
			System.err.println("Incorrect input");
			System.out.println("Select one by pressing its number");
			valor = Integer.parseInt(lector.nextLine());
			e = pageList.get(valor);
		}
		return e;
	}
	
	@Override
	public String getText() {
		return text;
	}
	
	public PageList<E> getPageList() {
		return pageList;
	}
}
