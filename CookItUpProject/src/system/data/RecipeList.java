package system.data;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import recipe.Comment;
import recipe.Ingredient;
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
	public boolean setComment(Comment c) {
		if (contains(c.getRecipe())) {
			return c.getRecipe().addComment(c);
		}
		return false;
	}
	
	/*Question*/
	public boolean setQuestion(Question q) {
		if (contains(q.getRecipe())) {
			return q.getRecipe().addQuestion(q);
		}
		return false;
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

	public List<Recipe> fridgeFilter(Collection<Recipe> recipes, Set<Ingredient> fridge) {
		List<Recipe> list = new LinkedList<>();
		for (Recipe r : recipes) {
			if (fridgeContidion(r, fridge))
				list.add(r);
		}
		return list;
	}
	
	public List<Recipe> followerFilter(Collection<Recipe> recipes, Collection<RegisteredUser> following) {
		List<Recipe> list = new LinkedList<>();
		for (Recipe r : recipes) {
			if (followerCondition(r, following))
				list.add(r);
		}
		return list;
	}
	
	public List<Recipe> blockedFilter(Collection<Recipe> recipes, Collection<RegisteredUser> blocked) {
		List<Recipe> list = new LinkedList<>();
		for (Recipe r : recipes) {
			if (blockedCondition(r, blocked))
				list.add(r);
		}
		return list;
	}
	
	public List<Recipe> nameFilter(Collection<Recipe> recipes, String name) {
		List<Recipe> list = new LinkedList<>();
		for (Recipe r : recipes) {
			if (nameCondition(r, name))
				list.add(r);
		}
		return list;
	}
	
	private boolean fridgeContidion(Recipe r, Collection<Ingredient> fridge) {
		return fridge.containsAll(r.getIngredientsList());
	}
	
	private boolean followerCondition(Recipe r, Collection<RegisteredUser> following) {
		return following.contains(r.getUser());
	}
	
	private boolean blockedCondition(Recipe r, Collection<RegisteredUser> blocked) {
		return !blocked.contains(r.getUser());
	}
	
	//title contains name
	private boolean nameCondition(Recipe r, String name) {
		return r.getName().toLowerCase().contains(name.toLowerCase());
	}
}
