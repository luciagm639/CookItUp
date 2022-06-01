package system.data;

import recipe.Ingredient;
import recipe.Recipe;
import system.MySystem;

public class RecipeIngredientTable extends Table<Recipe, Ingredient> {
	
	public RecipeIngredientTable() {
		super("RecipeIngredientTable.txt");
	}
	
	public RecipeIngredientTable(MySystem system) {
		super("RecipeIngredientTable.txt");
		getData(system);
	}

	@Override
	public void readData(MySystem system, String text) {
		MyStringSplitter sp = new MyStringSplitter(text);
		Recipe recipe = system.getRecipe(sp.nextInt());
		Ingredient ingredient = system.getIngredient(sp.nextInt());
		
		recipe.addIngredient(ingredient);
	}

	@Override
	public void getData(MySystem system) {
		for (Recipe r : system.getAllRecipes()) {
			for (Ingredient i : r.getIngredientsList()) {
				add(new Tuple<>(r, i));
			}
		}
	}
	
}