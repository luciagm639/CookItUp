package system.data;

import java.util.List;

import recipe.Comment;
import recipe.Question;
import recipe.Recipe;
import system.MySystem;
import user.RegisteredUser;

public class RecipeList extends DataSet<Recipe> {
	
	public RecipeList() {
		super("Recipes.txt");
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
		return RecipeList.toString(toList());
	}

	public Recipe find(String name) {
		for (Recipe recipe : this) {
			if (recipe.getName().equalsIgnoreCase(name)) {
				return recipe;
			}
		}
		return null;
	}
	
	/*Comment*/
	public void setComment(Comment c) {
		if (contains(c.getRecipe())) {
			c.getRecipe().addComment(c);
		}
	}
	
	/*Question*/
	public void setQuestion(Question q) {
		if (contains(q.getRecipe())) {
			q.getRecipe().addQuestion(q);
		}
	}

	/*Data Saver*/	
	@Override
	public void readData(MySystem system, String text) {
		MyStringSplitter sp = new MyStringSplitter(text);
		
		int id = sp.nextInt();
		String name = sp.next();
		int priority = sp.nextInt();
		RegisteredUser user = system.getUser(sp.nextInt());
		
		Recipe r = new Recipe(name, priority, user);
		user.addRecipe(r);
		add(r, id);
	}
	
	@Override
	public String writeData(Recipe recipe) {
		MyStringJoiner sj = new MyStringJoiner();
		sj.add(recipe.getId());
		sj.add(recipe.getName());
		sj.add(recipe.getPriority());
		sj.add(recipe.getUser().getId());
		
		return sj.toString();
	}
}
