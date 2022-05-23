package system.data;

import java.util.List;

import recipe.Recipe;
import recipe.Step;
import system.MySystem;

public class RecipeStepTable extends OrderedTable<Recipe, Step> {

	public RecipeStepTable() {
		super("RecipeStepTable.txt");
	}
	
	public RecipeStepTable(MySystem system) {
		super("RecipeStepTable.txt");
		getData(system);
	}

	@Override
	public void readData(MySystem system, String text) {
		MyStringSplitter sp = new MyStringSplitter(text);
		Recipe recipe = system.getRecipe(sp.nextInt());
		Step step = system.getStep(sp.nextInt());
		
		recipe.addStep(step);
	}

	@Override
	public void getData(MySystem system) {
		for (Recipe r : system.getAllRecipes()) {
			List<Step> steps = r.getStepsList();
			for(int i = 0; i < steps.size(); i++) {
				add(new Triple<Recipe, Step>(r, steps.get(i), i));
			}
		}
	}

}
