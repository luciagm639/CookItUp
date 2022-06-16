package recipe;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import system.RecipeExtended;
import system.RegisteredUserExtended;
import system.data.Data;
import user.*;

public class Recipe extends Data<Recipe> {

	protected RegisteredUser user;
	protected String name;
	protected int priority;
	protected Set<Ingredient> ingredientsList;
	protected List<Step> stepsList;


	public Recipe(String name, int priority, RegisteredUser user) {
		this.name = name;
		this.priority = priority;
		this.user = user;
		
		this.ingredientsList = new HashSet<Ingredient>();
		this.stepsList = new ArrayList<Step>();
	}
	
	public Recipe(String name, RegisteredUser user) {
		this(name, 0, user);
	}
	
	public Recipe(RecipeExtended recipe) {
		super(recipe.getId());
		user = recipe.getUser();
		if (user instanceof RegisteredUserExtended) {
			user = ((RegisteredUserExtended) user).unextend();
		}
		name = recipe.name;
		priority = recipe.priority;
		ingredientsList = recipe.ingredientsList;
		stepsList = recipe.stepsList;
	}

	@Override
	public boolean equals(Object o) {
		return (o instanceof Recipe) && (((Recipe) o).name.equalsIgnoreCase(name));
	}
	
	@Override
	public int hashCode() {
		return name.toLowerCase().hashCode();
	}
	
	//TODO implement a list with this order
	/*
	@Override
	public int compareTo(Recipe recipe) {
		if (this.priority > recipe.priority)
			return -1;
		if (this.priority < recipe.priority)
			return 1;
		return this.name.compareTo(recipe.name);
	}
	*/

	public RegisteredUser getUser() {
		return user;
	}
	
	public void setUser(RegisteredUser defaultUser) {
		user = defaultUser;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getPriority() {
		return priority;
	}
	
	public void setPriority(int p) {
		priority = p;
	}

	

	public Set<Ingredient> getIngredientsList() {
		return ingredientsList;
	}

	public List<Step> getStepsList() {
		return stepsList;
	}

	public void addIngredient(Ingredient ingredient) {
		ingredientsList.add(ingredient);
	}

	public void deleteIngredient(Ingredient ing) {
		ingredientsList.remove(ing);
	}

	public void addStep(Step step) {
		stepsList.add(step);
	}
	
	public void addStep(Step step, int index) {
		stepsList.add(index, step);
	}
	
	public void deleteStep(Step step) {
		stepsList.remove(step);
	}
	
	public void deleteStep(int index) {
		stepsList.remove(index);
	}

	public boolean checkOwner(RegisteredUser reg) {
		return user.equals(reg);
	}
	
	// Limitamos el numero de ingredientes y pasos a mostrar a 5
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append(name + "\n");
			sb.append("Author: " + user + "\n");

			sb.append("Ingredients:\n");
			int cont = 0;
			for (Ingredient i : ingredientsList) {
				if (cont == 5) {
					sb.append("\t...\n");
					break;
				}
				sb.append("\t" + i + "\n");
				cont++;
			}

			sb.append("Steps:\n");
			cont = 0;
			for (int i = 0; i < stepsList.size(); i++) {
				if (cont == 5) {
					sb.append("...\n");
					break;
				}
				sb.append((i + 1) + ". " + stepsList.get(i) + "\n");
				cont++;
			}
			return sb.toString();
		}
	
}