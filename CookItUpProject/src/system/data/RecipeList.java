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
import system.RecipeExtended;
import user.RegisteredUser;

public class RecipeList extends DataSet<RecipeExtended, Recipe> {
	
	public RecipeList() {
		super("Recipes.txt");
	}

	public static <R extends Recipe> String toString(List<R> recipes) {
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

	public RecipeExtended find(String name) {
		for (RecipeExtended recipe : this) {
			if (recipe.getName().equalsIgnoreCase(name)) {
				return recipe;
			}
		}
		return null;
	}
	
	public RecipeExtended get(Recipe recipe) {
		return this.get(recipe.getId());
	}
	
	/**
	 * Comment
	 */
	public boolean setComment(Comment c) {
		RecipeExtended recipe = get(c.getRecipe());
		if (recipe != null) {
			return recipe.addComment(c);
		}
		return false;
	}
	
	/**
	 * Question
	 */
	public boolean setQuestion(Question q) {
		RecipeExtended recipe = get(q.getRecipe());
		if (recipe != null) {
			return recipe.addQuestion(q);
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
		
		RecipeExtended r = new RecipeExtended(name, priority, user);
		user.addRecipe(r);
		add(r, id);
	}
	
	@Override
	public String writeData(RecipeExtended recipe) {
		MyStringJoiner sj = new MyStringJoiner();
		sj.add(recipe.getId());
		sj.add(recipe.getName());
		sj.add(recipe.getPriority());
		sj.add(recipe.getUser().getId());
		
		return sj.toString();
	}

	public List<RecipeExtended> fridgeFilter(Collection<RecipeExtended> recipes, Set<Ingredient> fridge) {
		List<RecipeExtended> list = new LinkedList<>();
		for (RecipeExtended r : recipes) {
			if (fridgeContidion(r, fridge))
				list.add(r);
		}
		return list;
	}
	
	public List<RecipeExtended> followerFilter(Collection<RecipeExtended> recipes, Collection<RegisteredUser> following) {
		List<RecipeExtended> list = new LinkedList<>();
		for (RecipeExtended r : recipes) {
			if (followerCondition(r, following))
				list.add(r);
		}
		return list;
	}
	
	public List<RecipeExtended> blockedFilter(Collection<RecipeExtended> recipes, Collection<RegisteredUser> blocked) {
		List<RecipeExtended> list = new LinkedList<>();
		for (RecipeExtended r : recipes) {
			if (blockedCondition(r, blocked))
				list.add(r);
		}
		return list;
	}
	
	public List<RecipeExtended> nameFilter(Collection<RecipeExtended> recipes, String name) {
		List<RecipeExtended> list = new LinkedList<>();
		for (RecipeExtended r : recipes) {
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
