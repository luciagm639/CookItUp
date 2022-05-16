package Recipe;

public class Ingredient {
	
	String name;
	
	public Ingredient(String name) {
		this.name = name;
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
