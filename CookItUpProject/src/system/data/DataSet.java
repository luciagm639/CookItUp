package system.data;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import system.MySystem;

public abstract class DataSet<E extends Data<E>> extends TreeSet<E> {
	
	private final String fileName;
	
	public DataSet(String fileName) {
		this.fileName = fileName;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public abstract void readData(MySystem system, String text);
	
	public abstract String writeData(E element);
		
	public int nextId() {
		int i = 1;
		for (E element : this) {
			if (i < element.getId())
				return i;
			else if (i == element.getId())
				i++;
		}
		return i;
	}
	
	public E get(int id) {
		for (E element : this) {
			if (id == element.getId())
				return element;
			else if (id < element.getId())
				return null;
		}
		return null;
	}
	
	public E addAndGet(E e) {
		for (E element : this) {
			if (e.equals(element))
				return element;
		}
		add(e);
		return e;
	}
	
	@Override
	public boolean add(E e) {
		if (e.getId() == -1) {
			e.setId(this);
		}
		else if (get(e.getId()) != null) {
			return false;//NO SE PUEDEN AÑADIR ELEMENTOS CON IDS IGUALES
		}
		return super.add(e);
	}
	
	public boolean add(E e, int id) {
		if (get(e.getId()) == null) {
			e.setId(this, id);
		}
		else {
			System.err.println("Duplicate ids for " + e.getClass());
		}
		return super.add(e);
	}
	
	public String[] split(String text) {
		return text.split("\t");
	}
	
	public List<E> toList() {
		return new ArrayList<>(this);
	}
}
