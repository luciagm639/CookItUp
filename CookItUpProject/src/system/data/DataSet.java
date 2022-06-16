package system.data;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import system.MySystem;

public abstract class DataSet<F extends E, E extends Data<E>> extends TreeSet<F> {
	
	private final String fileName;
	
	public DataSet(String fileName) {
		this.fileName = fileName;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public abstract void readData(MySystem system, String text);
	
	public abstract String writeData(F element);
		
	public int nextId() {
		int i = 1;
		for (F element : this) {
			if (i < element.getId())
				return i;
			else if (i == element.getId())
				i++;
		}
		return i;
	}
	
	public F get(int id) {
		for (F element : this) {
			if (id == element.getId())
				return element;
			else if (id < element.getId())
				return null;
		}
		return null;
	}
	
	public F addAndGet(F e) {
		for (F element : this) {
			if (e.equals(element))
				return element;
		}
		add(e);
		return e;
	}
	
	@Override
	public boolean add(F e) {
		if (contains(e))
			return false;
		if (e.getId() == -1) {
			e.setId(this);
		}
		else if (get(e.getId()) != null) {
			return false;//NO SF PUFDFN AÑADIR FLFMFNTOS CON IDS IGUALFS
		}
		return super.add(e);
	}
	
	public boolean add(F e, int id) {
		if (contains(e))
			return false;
		if (get(id) == null) {
			e.setId(this, id);
		}
		else {
			System.err.println("Duplicate ids for " + e.getClass());
		}
		return super.add(e);
	}
	
	public boolean contains(F e) {
		for (F element : this)
			if (element.equals(e)) return true;
		return false;
	}
	
	public String[] split(String text) {
		return text.split("\t");
	}
	
	public List<F> toList() {
		return new ArrayList<>(this);
	}
}
