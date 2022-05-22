package administrator;

import system.data.Data;

public class Administrator extends Data<Administrator> {
	
	String name;
	String pasword;
	
	public Administrator (String name, String pasword) {
		this.name = name;
		this.pasword = pasword;	
	}

	public String getName() {
		return name;
	}

	public String getPasword() {
		return pasword;
	}
}

