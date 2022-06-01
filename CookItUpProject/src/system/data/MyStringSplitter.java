package system.data;

class MyStringSplitter {
	
	int pos;
	String[] strings;
	
	MyStringSplitter(String text) {
		pos = 0;
		strings = text.split("\t");
	}
	
	int nextInt() {
		int res = Integer.parseInt(strings[pos]);
		pos++;
		return res;
	}
	
	String next() {
		String res = strings[pos];
		pos++;
		return res;
	}
  
	public boolean nextBoolean() {
		boolean res = Boolean.parseBoolean(strings[pos]);
		pos++;
		return res;
	}
}
