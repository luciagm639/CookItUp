package recipe;

import system.data.Data;

public class Ingredient extends Data<Ingredient> {
	
	private String name;
	
	public Ingredient(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	@Override
	public boolean equals(Object o) {
		return (o instanceof Ingredient) && (((Ingredient) o).name.equals(name));
	}
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}
}
