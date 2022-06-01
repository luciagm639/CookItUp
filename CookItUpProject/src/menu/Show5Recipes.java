package menu;

import java.util.List;

import recipe.Recipe;

public abstract class Show5Recipes  implements Option  {
	public int show5Recipe(int i,List<Recipe> recipes,int pagina) {
		boolean seguir = true;
		int cont = 1;
		int z = i;
		while(z < i+5 && seguir == true) {
			if(recipes.size()>z) {
				System.out.println("#" + cont);
				System.out.println(recipes.get(z).toString());
				z++;
				cont ++;
			}
			else { 
				seguir = false;
			}
		}
		return z;
		
	}
}
