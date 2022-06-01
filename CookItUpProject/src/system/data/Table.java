package system.data;

import java.util.HashSet;

import system.MySystem;

public abstract class Table<F extends Data<F>, S extends Data<S>> extends HashSet<Tuple<F, S>>{
	
	private final String fileName;
	
	public Table(String fileName) {
		this.fileName = fileName;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public abstract void readData(MySystem system, String text);
	
	public String writeData(F first, S second) {
		MyStringJoiner sj = new MyStringJoiner();
		sj.add(first.getId());
		sj.add(second.getId());
		
		return sj.toString();
	}
	
	public abstract void getData(MySystem system);
	
	public String[] split(String text) {
		return text.split("\t");
	}
	
	public void add(F first, S second) {
		add(new Tuple<F, S>(first, second));
	}
}
