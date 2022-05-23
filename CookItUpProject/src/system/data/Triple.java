package system.data;

public class Triple<F extends Data<F>, S extends Data<S>> implements Comparable<Triple<F, S>>{
	
	private F first;
	private S second;
	private int cardinal;
	
	
	public Triple(F first, S second, int cardinal) {
		this.first = first;
		this.second = second;
		this.cardinal = cardinal;
	}

	public F getFirst() {
		return first;
	}


	public S getSecond() {
		return second;
	}


	public int getCardinal() {
		return cardinal;
	}


	@Override
	public int compareTo(Triple<F, S> o) {
		if (first.compareTo(o.first) == 0) {
			return cardinal - o.cardinal;
		}
		return first.compareTo(o.first);
	}

}
