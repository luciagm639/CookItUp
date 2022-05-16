package test;

import recipe.Recipe;
import system.MySystem;
import system.RecipeList;

public class Test {

	public static void main(String[] args) {
		MySystem sys = new MySystem();
		
		//System.out.println(sys.showRecipes(sys.getAllRecipes()));
		
		for (Recipe rec : sys.getAllRecipes()) {
			System.out.println(rec.toStringExtended());
		}
	}

}
