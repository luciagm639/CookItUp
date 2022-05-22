package system.data;

import java.util.StringJoiner;

class MyStringJoiner {
	
	private StringJoiner sj;
	
	MyStringJoiner() {
		sj = new StringJoiner("\t", "", "\n");
	}

	void add(String s) {
		sj.add(s);
	}
	
	void add(int i) {
		sj.add(Integer.toString(i));
	}
	
	void add(boolean b) {
		sj.add(Boolean.toString(b));
	}
	
	@Override
	public String toString() {
		return sj.toString();
	}
}
