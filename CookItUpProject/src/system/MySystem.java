package system;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import administrator.Administrator;
import recipe.Comment;
import recipe.Ingredient;
import recipe.Question;
import recipe.Recipe;
import recipe.Review;
import recipe.Step;
import report.Report;
import system.data.*;
import user.RegisteredUser;

public class MySystem {
  
	private RecipeList recipesList;
	private RegisteredUserList userList;
	private IngredientsList ingredientsList;
	private ReportsList reportsList;
	private AdministratorList administratorList;
	private StepsList stepsList;
	private CommentsList commentList;
	private QuestionsList questionList;
	private ReviewList reviewList;
	
	//TODO delete this
	private RegisteredUserList blockedList;
	
	public MySystem() {
		empty();		
		open();
	}
	
	public void empty() {
		recipesList = new RecipeList();
		userList = new RegisteredUserList();
		ingredientsList = new IngredientsList();
		reportsList = new ReportsList();
		administratorList = new AdministratorList();		
		stepsList = new StepsList();
		commentList = new CommentsList();
		questionList = new QuestionsList();
		reviewList = new ReviewList();
	}
	
	/*Recipes*/
	public void addRecipe(Recipe recipe) {
		recipesList.add(recipe);
		recipe.getUser().addRecipe(recipe);
	}

	public boolean removeRecipe(Recipe recipe) {
		return (recipesList.remove(recipe) && recipe.getUser().deleteRecipe(recipe));
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

	/* Users */
	public void addUser(RegisteredUser user) {
		userList.add(user);
	}

	public RegisteredUser getUser(int id) {
		return userList.get(id);
	}

	public boolean removeUser(RegisteredUser user) {
		return userList.remove(user);
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

	/* Reports */
	public void addReport(Report report) {
		reportsList.add(report);
	}

	public void removeReport(Report report) {
		reportsList.remove(report);
	}

	/* Administrators */
	public void addAdmin(Administrator adm2) {
		administratorList.add(adm2);
	}

	public boolean removeAdmin(Administrator adm2) {
		return administratorList.remove(adm2);
	}

	public Set<Administrator> getAdminList() {
		return administratorList;
	}

	public Administrator findAdmin(String name) {
		return administratorList.find(name);
	}

	/* Blocked */
	public void addBlockedUser(RegisteredUser us0) {
		us0.setStatus(true);
	}

	public void removeBlockedUser(RegisteredUser us0) {
		us0.setStatus(false);
	}

	public RegisteredUserList getBlockedUserList() {
		RegisteredUserList blockedUsers = new RegisteredUserList();
		for (RegisteredUser reg : userList) {
			if (reg.getStatus())
				blockedUsers.add(reg);
		}
		return blockedUsers;
	}

	/* Comment */
	public Comment getComment(int id) {
		return commentList.get(id);
	}

	public boolean addComment(Comment c) {
		return (commentList.add(c) && c.getAuthor().addComment(c) && recipesList.setComment(c));
	}
	
	/*Question*/
	public Question getQuestion(int id) {
		return questionList.get(id);
	}

	public boolean addQuestion(Question q) {
		return (questionList.add(q) && q.getAuthor().addQuestion(q) && recipesList.setQuestion(q));
	}

	/* Step */
	public void addStep(Step step, Recipe recipe) {
		Step s = stepsList.addAndGet(step);
		recipe.addStep(s);
	}

	public void addStep(Step step, Recipe recipe, int order) {
		Step s = stepsList.addAndGet(step);
		recipe.addStep(s, order);
	}
	
	public Step getStep(int id) {
		return stepsList.get(id);
	}
	
	/*Review*/
	public void addReview(Review r) {
		
	}
	
	
	
	/*Save data*/
	public void open() {
		readData(userList);
		readData(recipesList);
		readData(ingredientsList);
		readData(administratorList);
		
		readData(stepsList);
		readData(commentList);
		readData(questionList);
		readData(reviewList);
		//...
		readData(reportsList);
		
		readData(new RecipeIngredientTable());
		readData(new RecipeStepTable());
		readData(new BlockedTable());
		readData(new FollowTable());
		readData(new Fridge());
	}
	
	public void close() {
		writeData(userList);
		writeData(recipesList);
		writeData(ingredientsList);
		writeData(administratorList);
		
		writeData(stepsList);
		writeData(commentList);
		writeData(questionList);
		writeData(reviewList);
		//...
		writeData(reportsList);
		
		writeData(new RecipeIngredientTable(this));
		writeData(new RecipeStepTable(this));
		writeData(new BlockedTable(this));
		writeData(new FollowTable(this));
		writeData(new Fridge(this));
		
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

	private <F extends Data<F>, S extends Data<S>> void writeData(Table<F, S> t) {
		try (FileWriter fw = new FileWriter(new File(t.getFileName()))) {
			for (Tuple<F, S> tuple : t) {
				fw.write(t.writeData(tuple.getFirst(), tuple.getSecond()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private <F extends Data<F>, S extends Data<S>>
	void readData(OrderedTable<F, S> t) {
		try(Scanner sc = new Scanner(new File(t.getFileName()))) {
			while (sc.hasNext()) {
				t.readData(this, sc.nextLine());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private <F extends Data<F>, S extends Data<S>>
	void writeData(OrderedTable<F, S> t) {
		try(FileWriter fw = new FileWriter(new File(t.getFileName()))) {
			for (Triple<F, S> triple : t) {
				fw.write(t.writeData(triple.getFirst(), triple.getSecond(), triple.getCardinal()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Recipe> filter(Set<Ingredient> fridge, Collection<RegisteredUser> following, Collection<RegisteredUser> blocked, String name) {
		List<Recipe> recipes = recipesList.toList();
		if (fridge != null)
			recipes = recipesList.fridgeFilter(recipes, fridge);
		if (following != null)
			recipes = recipesList.followerFilter(recipes, following);
		if (blocked != null)
			recipes = recipesList.blockedFilter(recipes, blocked);
		if (name != null)
			recipes = recipesList.nameFilter(recipes, name);
		return recipes;
	}
}
