package system.data;

public abstract class Data<E extends Data<E>> implements Comparable<E> {
	
	int id;
	
	public Data() {
		id = -1;
	}
	
	public Data(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
	public <F extends E> void setId(DataSet<F, E> ds) {
		id = ds.nextId();
	}
	
	public <F extends E> void setId(DataSet<F, E> ds, int id) {
		this.id = id;
	}
	
	@Override
	public int compareTo(E e) {
		return id - e.id;
	}
}
