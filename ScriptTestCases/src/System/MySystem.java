package System;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Recipe.Ingredient;
import Recipe.Recipe;
import Recipe.Step;
import Reports.Report;
import User.Comment;
import User.Question;
import User.RegisteredUser;

public class MySystem {
	
	private RecipeList recipesList;
	private RegisteredUserList userList;
	private Set<Ingredient> ingredientsList;
	private List<Report> reportsList;
	
	public MySystem() {
		//deberia acceder a los datos
		recipesList = new RecipeList();
		userList = new RegisteredUserList();
		ingredientsList = new HashSet<>();
		reportsList = new ArrayList<>();
		
		fillSystemTesting();
	}
	
	public void addRecipe(Recipe recipe) {
		recipesList.add(recipe);
	}
	
	public void removeRecipe(Recipe recipe) {
		recipesList.remove(recipe);
	}
	
	public List<Recipe> getAllRecipes() {
		return recipesList.showRecipes();
	}
	
	public String showRecipes(List<Recipe> recipes) {
		return RecipeList.toString(recipes);
	}
	//TODO
	public Recipe findRecipe(String name) {
		return null;
	}
	
	public void addUser(RegisteredUser user) {
		userList.addToList(user);
	}
	
	public RegisteredUser getUser(int id) {
		return userList.getUser(id);
	}
	
	public void removeUser(RegisteredUser user) {
		userList.remove(user);
	}
	
	public RegisteredUser findUser(String name) {
		return userList.findUser(name);
	}
	
	public void addIngredient(Ingredient ing) {
		ingredientsList.add(ing);
	}
	
	public boolean containsIngredient(Ingredient ing) {
		return ingredientsList.contains(ing);
	}
	
	public void removeIngredient(Ingredient ing) {
		ingredientsList.remove(ing);
	}
	
	public void addReport(Report report) {
		reportsList.add(report);
	}
	
	public void removeReport(Report report) {
		reportsList.remove(report);
	}
	
	public void fillSystemTesting() {
		
		//Registered Users
		RegisteredUser us0 = new RegisteredUser("Alfonso", 0, "password");
		RegisteredUser us1 = new RegisteredUser("Javi", 1, "password");
		RegisteredUser us2 = new RegisteredUser("Jordan", 2, "password");
		RegisteredUser us3 = new RegisteredUser("Jorge", 3, "password");
		
		addUser(us0); addUser(us1); addUser(us2); addUser(us3);
		
		//Recipes
		Recipe recipe = new Recipe("Tortilla de papa", 1, us3);
		Recipe recipe1 = new Recipe("Huevo frito", 3, us3);
		
		addRecipe(recipe); addRecipe(recipe1);
		/*
		Comment comment = new Comment(us0, "Esta bueno pero le pondria mas tiempo de coccion");
		recipe.addComment(comment);
		Question question = new Question(us0, "¿De qué tamaño son los huevos que usas?");
		recipe.addQuestion(question);
		*/
		//Ingredients
		Ingredient ingredient = new Ingredient("Patata");
		Ingredient ingredient1 = new Ingredient("Huevo");
		
		addIngredient(ingredient); addIngredient(ingredient1);
		
		recipe.addIngredient(ingredient);
		recipe.addIngredient(ingredient1);
		recipe1.addIngredient(ingredient1);
		
		//Steps
		Step step = new Step(10, "Batir los huevos");
		Step step2 = new Step(30, "Cortar las patatas");
		Step step3 = new Step(10, "Cocinar la tortilla");
		
		recipe.addStep(step);
		recipe.addStep(step2);
		recipe.addStep(step3);
		recipe1.addStep(step);
		/*
		Report report = new Report(us0, "justification");
		Report report1 = new Report(us1, "justification1");
		*/
	}
}
