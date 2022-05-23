package system.data;

import java.util.TreeSet;

import system.MySystem;
public abstract class OrderedTable<F extends Data<F>, S extends Data<S>> extends TreeSet<Triple<F, S>>{
	
	private final String fileName;
	
	public OrderedTable(String fileName) {
		this.fileName = fileName;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public abstract void readData(MySystem system, String text);
	
	public String writeData(F first, S second, int cardinal) {
		MyStringJoiner sj = new MyStringJoiner();
		sj.add(first.getId());
		sj.add(second.getId());
		sj.add(cardinal);
		
		return sj.toString();
	}
	
	public abstract void getData(MySystem system);
	
	public String[] split(String text) {
		return text.split("\t");
	}
}
