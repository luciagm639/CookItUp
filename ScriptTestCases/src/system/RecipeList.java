package system;

import java.util.ArrayList;
import java.util.List;

import recipe.Recipe;
import user.RegisteredUser;

public class RecipeList {
	
	private List<Recipe> recipeList;
	
	public RecipeList() {
		recipeList = new ArrayList<Recipe>();
	}

	public void add(Recipe recipe) {
		recipeList.add(recipe);
	}

	public void remove(Recipe recipe) {
		recipeList.remove(recipe);
	}
	
	public boolean contains(Recipe recipe) {
		return recipeList.contains(recipe);
	}
	
	public List<Recipe> showRecipes() {
		return new ArrayList<>(recipeList);
	}

	public static String toString(List<Recipe> recipes) {
		StringBuilder sb = new StringBuilder();
		sb.append("Showing "+recipes.size()+" recipes.\n");
		for(int i=0;i<recipes.size();i++) {
			sb.append("\nRecipe #" + (i+1) + "\n");
			sb.append(recipes.get(i));
		}
		return sb.toString();
	}
	
	@Override
	public String toString() {
		return RecipeList.toString(recipeList);
	}

	public Recipe find(String name) {
		for (Recipe recipe : recipeList) {
			if (recipe.getName().equalsIgnoreCase(name)) {
				return recipe;
			}
		}
		return null;
	}
}
