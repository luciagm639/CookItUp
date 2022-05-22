package system.data;

import recipe.Ingredient;
import system.MySystem;

public final class IngredientsList extends DataSet<Ingredient> {
	
	public IngredientsList() {
		super("Ingredients.txt");
	}

	@Override
	public void readData(MySystem system, String text) {
		MyStringSplitter sp = new MyStringSplitter(text);
		
		int id = sp.nextInt();
		String name = sp.next();
		
		Ingredient i = new Ingredient(name);			
		this.add(i, id);
	}

	@Override
	public String writeData(Ingredient ing) {
		MyStringJoiner sj = new MyStringJoiner();
		sj.add(ing.getId());
		sj.add(ing.getName());
		
		return sj.toString();
	}

	public Ingredient find(String name) {
		for (Ingredient ing : this) {
			if (name.equalsIgnoreCase(ing.getName())) {
				return ing;
			}
		}
		return null;
	}
	
}
