package menu;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import user.RegisteredUserInterface;

public abstract class Show5<E> implements Option {
	
	private PageList<E> pageList;

	private String text;
	private Menu prevMenu;
	
	//First time
	public Show5(String text) {
		super();
		this.pageList = new PageList<>();
		this.pageList.setPage(0);
		
		this.text = text;
		this.prevMenu = null;
	}
	
	public void setPrevMenu(Menu prevMenu) {
		if (this.getPrevMenu() == null)
			this.prevMenu = prevMenu;
	}
	
	public void setList(List<E> list) {
		this.pageList.setList(list);
	}
	
	public void setList(Set<E> list) {
		this.pageList.setList(new LinkedList<>(list));
	}

	protected Show5(Show5<E> s, boolean isPrev) {
		super();
		this.pageList = s.pageList.copy();
		this.prevMenu = s.getPrevMenu();
		if (isPrev) {
			text = "See previous page";
			this.pageList.setPage(s.pageList.getPage()-1);
		}
		else {
			text = "See next page";
			this.pageList.setPage(s.pageList.getPage()+1);
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		int cont = 1;
		E actual = pageList.get(cont);
		while (actual != null && cont <= 5) {
			sb.append("#" + cont + "\n");
			sb.append(actual);
			cont++;
			actual = pageList.get(cont);
		}
		return sb.toString();
	}
	
	protected void execute(Interface inter, Scanner lector, int pagina, Show5<E> prevPage, Show5<E> nextPage, List<Option> options) {
		
		Menu ListMenu = new Menu("What do you want to do?", inter, this);
		if (prevPage != null)
			ListMenu.addOption(prevPage);
		if (nextPage != null)
			ListMenu.addOption(nextPage);
		for (Option o : options)
			ListMenu.addOption(o);
		ListMenu.addOption(new GoBack(getPrevMenu()));
		ListMenu.drawMenu();
		
	}
	
//	public int getPage() {
//		return getPagina();
//	}
	
	@Override
	public String getText() {
		return text;
	}
	
	public boolean hasPrevPage() {
		return pageList.hasPrevPage();
	}
	
	public boolean hasNextPage() {
		return pageList.hasNextPage();
	}
	
//	public int getPagina() {
//		return pagina;
//	}

	public Menu getPrevMenu() {
		return prevMenu;
	}

	public PageList<E> getPageList() {
		return pageList;
	}

//	public List<E> getList() {
//		return list;
//	}
	
	
}