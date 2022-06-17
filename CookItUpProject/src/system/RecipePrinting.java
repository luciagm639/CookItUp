package system;

import java.util.LinkedList;
import java.util.List;

import recipe.Recipe;

public class RecipePrinting extends Recipe {
	
	private String toString;
	private String toStringExtended;
	
	public RecipePrinting(RecipeExtended r) {
		super(r);
		toString = r.toString();
		toStringExtended = r.toStringExtended();
	}
	
	@Override
	public String toString() {
		return toString;
	}

	public String toStringExtended() {
		return toStringExtended;
	}

	public static List<RecipePrinting> parse(List<RecipeExtended> list) {
		List<RecipePrinting> res = new LinkedList<>();
		for(RecipeExtended r : list) {
			res.add(new RecipePrinting(r));
		}
		return res;		
	}
	
}
