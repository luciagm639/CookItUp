package system;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedSet;

import administrator.Administrator;
import recipe.Comment;
import recipe.Ingredient;
import recipe.Question;
import recipe.Recipe;
import recipe.Step;
import report.Report;
import system.data.*;
import user.RegisteredUser;
import user.RegisteredUserInterface;
import user.UserInterface;

public class MySystem {
	
	private RecipeList recipesList;
	private RegisteredUserList userList;
	
	/*Changes*/
	private IngredientsList ingredientsList;
	private ReportsList reportsList;
	private AdministratorList administratorList;
	
	private RegisteredUserList blockedList;
	
	/*Additions*/
	private StepsList stepsList;
	private CommentsList commentsList;
	private QuestionsList questionsList;
	
	public MySystem() {
		recipesList = new RecipeList();
		userList = new RegisteredUserList();
		ingredientsList = new IngredientsList();
		reportsList = new ReportsList();
		administratorList = new AdministratorList();
		
		stepsList = new StepsList();
		commentsList = new CommentsList();
		questionsList = new QuestionsList();

		//deberia acceder a los datos
		//fillSystemTesting();
		//open();
	}
	
	public void empty() {
		recipesList = new RecipeList();
		userList = new RegisteredUserList();
		ingredientsList = new IngredientsList();
		reportsList = new ReportsList();
		administratorList = new AdministratorList();
	}
	
	/*Recipes*/
	public void addRecipe(Recipe recipe) {
		recipesList.add(recipe);
		recipe.getUser().addRecipe(recipe);
	}
	
	public void removeRecipe(Recipe recipe) {
		recipesList.remove(recipe);
		recipe.getUser().deleteRecipe(recipe);
	}
	
	public List<Recipe> getAllRecipes() {
		return recipesList.toList();
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

	public Recipe getRecipe(int id) {
		return recipesList.get(id);
	}
	
	/*Users*/
	public void addUser(RegisteredUser user) {
		userList.add(user);
	}
	
	public RegisteredUser getUser(int id) {
		return userList.get(id);
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

	public RegisteredUser getDefaultUser() {
		return userList.get(0);
	}
	
	/*Ingredients*/
	public Ingredient addIngredient(Ingredient ing) {
		return ingredientsList.addAndGet(ing);
	}
	
	public void removeIngredient(Ingredient ing) {
		ingredientsList.remove(ing);
	}

	public Ingredient findIngredient(String name) {
		return ingredientsList.find(name);
	}

	public Ingredient getIngredient(int id) {
		return ingredientsList.get(id);
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
	
	/*Comment*/
	public Comment getComment(int id) {
		return commentsList.get(id);
	}

	public void addComment(Comment c) {
		commentsList.add(c);
		recipesList.setComment(c);
		c.getAuthor().addMessage(c);
	}
	
	/*Question*/
	public Question getQuestion(int id) {
		return questionsList.get(id);
	}

	public void addQuestion(Question q) {
		questionsList.add(q);
		recipesList.setQuestion(q);
		q.getAuthor().addMessage(q);
	}
	
	/*Step*/	
	public void addStep(Step step, Recipe recipe) {
		Step s = stepsList.addAndGet(step);
		recipe.addStep(step);
	}
	
	public void addStep(Step step, Recipe recipe, int order) {
		Step s = stepsList.addAndGet(step);
		recipe.addStep(step, order);
	}
	
	public Step getStep(int id) {
		return stepsList.get(id);
	}
	
	/*Save data*/
	public void open() {
		readData(userList);
		readData(recipesList);
		readData(ingredientsList);
		readData(reportsList);
		readData(administratorList);
		
		readData(stepsList);
		readData(commentsList);
		readData(questionsList);
		//...
		readData(reportsList);
		
		readData(new RecipeIngredientTable(this));
	}
	
	public void close() {
		writeData(userList);
		writeData(recipesList);
		writeData(ingredientsList);
		writeData(reportsList);
		writeData(administratorList);
		
		writeData(stepsList);
		writeData(commentsList);
		writeData(questionsList);
		//...
		writeData(reportsList);
		
		writeData(new RecipeIngredientTable(this));
		
		empty();
	}
	
	private <E extends Data<E>> void readData(DataSet<E> ds) {
		try(Scanner sc = new Scanner(new File(ds.getFileName()))) {
			while (sc.hasNext()) {
				ds.readData(this, sc.nextLine());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private <E extends Data<E>> void writeData(DataSet<E> ds) {
		try(FileWriter fw = new FileWriter(new File(ds.getFileName()))) {
			for (E element : ds) {
				fw.write(ds.writeData(element));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private <F extends Data<F>, S extends Data<S>>
	void readData(Table<F, S> t) {
		try(Scanner sc = new Scanner(new File(t.getFileName()))) {
			while (sc.hasNext()) {
				t.readData(this, sc.nextLine());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private <F extends Data<F>, S extends Data<S>>
	void writeData(Table<F, S> t) {
		try(FileWriter fw = new FileWriter(new File(t.getFileName()))) {
			for (Tuple<F, S> tuple : t) {
				fw.write(t.writeData(tuple.getFirst(), tuple.getSecond()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void fillSystemTesting() {
		
		//Create new accounts
		UserInterface ui = new UserInterface(this);
		addUser(new RegisteredUser("[deleted]", 0, null, 0, false));
		RegisteredUserInterface ius1 = ui.registerNewAccount("Alfonso", "password");
		RegisteredUserInterface ius2 = ui.registerNewAccount("Inbal", "password");
		RegisteredUserInterface ius3 = ui.registerNewAccount("Jordan", "password");
		RegisteredUserInterface ius4 = ui.registerNewAccount("Jorge", "password");
		
		Recipe recipe1 = ius4.createRecipe("Tortilla de papa");
		ius4.addIngredient("Patata", recipe1);
		ius4.addIngredient("Huevo", recipe1);
		
		Recipe recipe2 = ius4.createRecipe("Huevo frito");
		ius4.addIngredient("Huevo", recipe2);
		
		ius1.makeComment("Esta bueno pero le pondria mas tiempo de coccion", recipe1);
		
		ius1.askQuestion("¿De qué tamaño son los huevos que usas?", recipe1);
		
		ius4.addStep(10, "Batir los huevos", 0,  recipe1);
		/*
		//Comment
		Comment comment = new Comment(us0, "Esta bueno pero le pondria mas tiempo de coccion", recipe);
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
		*/
		/*
		Report report = new Report(us0, "justification");
		Report report1 = new Report(us1, "justification1");
		*/
	}
}
