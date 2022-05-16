package Test;

import System.MySystem;
import System.RecipeList;

public class Test {

	public static void main(String[] args) {
		MySystem sys = new MySystem();
		
		System.out.println(sys.showRecipes(sys.getAllRecipes()));
	}

}
