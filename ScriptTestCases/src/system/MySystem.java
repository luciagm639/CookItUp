package system;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import administrator.Administrator;
import recipe.Comment;
import recipe.Ingredient;
import recipe.Question;
import recipe.Recipe;
import recipe.Step;
import report.Report;
import user.RegisteredUser;

public class MySystem {
	
	private RecipeList recipesList;
	private RegisteredUserList userList;
	private Set<Ingredient> ingredientsList;
	private List<Report> reportsList;
	private Set<Administrator> administratorList;
	private RegisteredUserList blockedList;
	
	public MySystem() {
		recipesList = new RecipeList();
		userList = new RegisteredUserList();
		ingredientsList = new HashSet<>();
		reportsList = new ArrayList<>();
		administratorList = new HashSet<>();

		//deberia acceder a los datos
		fillSystemTesting();
	}
	
	public void empty() {
		recipesList = new RecipeList();
		userList = new RegisteredUserList();
		ingredientsList = new HashSet<>();
		reportsList = new ArrayList<>();
		administratorList = new HashSet<>();
	}
	
	/*Recipes*/
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
	
	public Recipe findRecipe(String name) {
		return recipesList.find(name);
	}
	
	public boolean hasRecipe(Recipe recipe) {
		return recipesList.contains(recipe);
	}
	
	public RecipeList getRecipes() {
		return recipesList;
	}
	
	/*Users*/
	public void addUser(RegisteredUser user) {
		userList.add(user);
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
	
	public RegisteredUserList getUserList() {
		return userList;
	}
	
	/*Ingredients*/
	public void addIngredient(Ingredient ing) {
		ingredientsList.add(ing);
	}
	
	public void removeIngredient(Ingredient ing) {
		ingredientsList.remove(ing);
	}
	
	/*Reports*/
	public void addReport(Report report) {
		reportsList.add(report);
	}
	
	public void removeReport(Report report) {
		reportsList.remove(report);
	}
	
	/*Administrators*/
	public void addAdmin(Administrator adm2) {
		administratorList.add(adm2);
	}
	
	public void removeAdmin(Administrator adm2) {
		administratorList.remove(adm2);
	}
	
	public Set<Administrator> getAdminList() {
		return administratorList;
	}
	
	/*Blocked*/
	public void addBlockedUser(RegisteredUser us0) {
		blockedList.add(us0);
	}

	public void removeBlockedUser(RegisteredUser us0) {
		blockedList.remove(us0);
	}
	
	public RegisteredUserList getBlockedUserList() {
		return blockedList;
	}
	
	public void fillSystemTesting() {
		
		//Registered Users
		RegisteredUser us0 = new RegisteredUser("Alfonso", 0, "password");
		RegisteredUser us1 = new RegisteredUser("Inbal", 1, "password");
		RegisteredUser us2 = new RegisteredUser("Jordan", 2, "password");
		RegisteredUser us3 = new RegisteredUser("Jorge", 3, "password");
		
		addUser(us0); addUser(us1); addUser(us2); addUser(us3);
		
		//Recipes
		Recipe recipe = new Recipe("Tortilla de papa", 1, us3);
		Recipe recipe1 = new Recipe("Huevo frito", 3, us3);
		
		addRecipe(recipe); addRecipe(recipe1);
		
		//Comment
		Comment comment = new Comment(us0, "Esta bueno pero le pondria mas tiempo de coccion");
		recipe.addComment(comment);
		
		//Question
		Question question = new Question(us0, "¿De qué tamaño son los huevos que usas?");
		recipe.addQuestion(question);
		Question question1 = new Question(us1, "¿De qué tamaño son los huevos que usas?");
		recipe.addQuestion(question1);
		
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

	public int nextUserId() {
		return userList.nextId();
	}
}
