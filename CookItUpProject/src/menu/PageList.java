package menu;

import java.util.List;

public class PageList<E> {
	
	private int page;
	private List<E> list;
	
	public PageList() {
		page = -1;
	}
	
	public int getPage() {
		return page;
	}
	
	public void setPageAndList(int page, List<E> list) {
		this.page = page;
		this.list = list;
	}
	
	public void setPage(int page) {
		this.page = page;
	}
	
	public void setList(List<E> list) {
		this.list = list;
	}
	
	public E get(int index) {
		E e = null;
		int numShown = list.size() - page*5;
		if (index >= 1 && index <= numShown) {
			e = list.get(page*5+index-1);
		}
		return e;
	}
	
	public int size() {
		return this.list.size();
	}
	

	public boolean hasPrevPage() {
		return page > 0;
	}
	
	public boolean hasNextPage() {
		return list.size() > (page+1)*5;
	}
	
	public PageList<E> copy() {
		PageList<E> clone = new PageList<>();
		clone.setPageAndList(page, list);
		return clone;
	}
}
