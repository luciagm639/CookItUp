package User;

import java.util.ArrayList;
import java.util.List;

import Recipe.Ingredients;
import Recipe.Recipe;
import Recipe.RecipePhoto;
import Recipe.Step;
import Reports.Report;

public class RegisteredUser {

	String name;
	int id;
	String password;
	List<RegisteredUser> blockList = new ArrayList<RegisteredUser>();
	List<RegisteredUser> followList = new ArrayList<RegisteredUser>();
	List<Recipe> recipesList = new ArrayList<Recipe>();
	double chips;
	
	public RegisteredUser(String name, int id, String password) {
		this.name = name;
		this.id = id;
		this.password = password;
		blockList = null;
		followList = null;
		recipesList = null;
		chips = 0;
	}

	public RegisteredUser(String name, String password) {
		this.name = name;
		this.password = password;
		blockList = null;
		followList = null;
		recipesList = null;
		chips = 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getPassword() {
		return password;
	}

	public List<RegisteredUser> listaBloqueados(RegisteredUser user) {
		return user.blockList;
	}
	
	public List<RegisteredUser> listaFollows(RegisteredUser user) {
		return user.followList;
	}
	
	public void block(RegisteredUser user) {
		if (this.blockList.contains(user)) {
			throw new RuntimeException("ERROR: The user cannot block another user already blocked");
		} else {
			this.blockList.add(user);
		}
	}
	
	public void unblock(RegisteredUser user) {
		if (this.blockList.contains(user)) {
			this.blockList.remove(user);
		} else {
			throw new RuntimeException("ERROR: The user cannot unblock another user not previously blocked");
		}
	}
	
	public void follow(RegisteredUser user) {
		if (this.followList.contains(user)) {
			throw new RuntimeException("ERROR: The user cannot follow another user already followed");
		} else {
			this.followList.add(user);
		}
	}
	
	public void unfollow(RegisteredUser user) {
		if (this.followList.contains(user)) {
			this.followList.remove(user);
		} else {
			throw new RuntimeException("ERROR: The user cannot unfollow a user not previously followed");
		}
	}
	
	public void deleteOwnAccount() {	
		
	}

	public void deleteRecipe(Recipe recipe) {
		if (this.recipesList.contains(recipe)) {
			this.recipesList.remove(recipe);
		} else {
			throw new RuntimeException("ERROR: The user cannot delete a recipe not previously created");
		}
	}
	
	public void addRecipe(Recipe recipe) {
		if(this.recipesList.contains(recipe)) {
			throw new RuntimeException("ERROR: The user cannot add a recipe already done");
		} else {
			this.recipesList.add(recipe);
		}	
	}
	
	public double spendChips(double amount) {
		if (this.chips - amount >= 0) {
			this.chips = this.chips - amount;
			return this.chips;
		} else {
			throw new RuntimeException("ERROR: The user cannot spend more chips that it has");
		}
	}

	public void askQuestion(Question question, Recipe recipe) {
		if (recipe.getQuestionsList() == null) {
			List<Question> newList = new ArrayList<>();
			newList.add(question);
			recipe.setQuestionsList(newList);
		} else {
			if (recipe.getQuestionsList().contains(question)) {
				throw new RuntimeException("ERROR: The user cannot ask the same question");
			} else {
				recipe.getQuestionsList().add(question);
			}
		}
	}

	public void makeComment(Comment comment, Recipe recipe) {
		if (recipe.getCommentsList() == null) {
			List<Comment> newList = new ArrayList<>();
			newList.add(comment);
			recipe.setCommentsList(newList);
		} else {
			recipe.getCommentsList().add(comment);
		}
	}

	public void uploadRecipePhoto(Recipe recipe, RecipePhoto photo) {
		if (recipe.getPhotosList() == null) {
			List<RecipePhoto> newList = new ArrayList<>();
			newList.add(photo);
			recipe.setPhotosList(newList);
		} else {
			if (recipe.getPhotosList().contains(photo)) {
				throw new RuntimeException("ERROR: The user cannot upload the same recipe photo");
			} else {
				recipe.getPhotosList().add(photo);
			}
		}
	}

	public void addIngredient(Ingredients ingredient, Recipe recipe) {
		if (this.recipesList.contains(recipe)) {
			if (recipe.getIngredientsList() == null) {
				List<Ingredients> newList = new ArrayList<>();
				newList.add(ingredient);
				recipe.setIngredientsList(newList);
			} else {
				if (recipe.getIngredientsList().contains(ingredient)) {
					throw new RuntimeException("ERROR: The user cannot add the same ingredient");
				} else {
					recipe.getIngredientsList().add(ingredient);
				}
			}
		} else {
			throw new RuntimeException("ERROR: The user cannot add an ingredient whose recipe not previously done");
		}
	}

	public void deleteIngredient(Ingredients ingredient1, Recipe recipe) {
		if (this.recipesList == null || !this.recipesList.contains(recipe)) {
			throw new RuntimeException("ERROR: The user cannot delete an ingredient whose recipe not previously done");
		} else {
			if (recipe.getIngredientsList() == null || !recipe.getIngredientsList().contains(ingredient1)) {
				throw new RuntimeException("ERROR: The user cannot delete an ingredient not previously in the recipe");
			} else {
				recipe.getIngredientsList().remove(ingredient1);
			}
		}
	}

	public void deleteRecipePhoto(Recipe recipe, RecipePhoto photo) {
		if (recipe.getPhotosList() == null || !recipe.getPhotosList().contains(photo)) {
			throw new RuntimeException("ERROR: The user cannot delete a recipe photo not previously done");
		} else {
			recipe.getPhotosList().remove(photo);
		}
	}

	public void reportRecipe(Recipe recipe, Report report1) {	
		if (recipe.getReportsList() == null) {
			List<Report> newList = new ArrayList<>();
			newList.add(report1);
			recipe.setReportsList(newList);
		} else {
			int i, cnt = 0;
			for (i = 0; i < recipe.getReportsList().size(); i++) {
				if (recipe.getReportsList().get(i).getReporting() == report1.reporting)
					cnt++;
			}		
			if (cnt > 0) {
				throw new RuntimeException("ERROR: The user cannot report a recipe already been reported");
			} else {
				recipe.getReportsList().add(report1);
			}
		}
	}

	public void addStep(Recipe recipe, Step step) {
		if (recipe.getStepsList() == null){
			List<Step> newList = new ArrayList<>();
			newList.add(step);
			recipe.setStepsList(newList);
		} else {
			if (recipe.getStepsList().contains(step)) {
				throw new RuntimeException("ERROR: The user cannot add a step already existing");
			} else {
				recipe.getStepsList().add(step);
			}
		}
	}
	
	public void deleteStep(Recipe recipe, Step step1) {
		if (recipe.getStepsList() == null) {
			throw new RuntimeException("ERROR: There user cannot delete a step not previously created");
		} else {
			if (recipe.getStepsList().contains(step1)) {
				recipe.getStepsList().remove(step1);
			} else {
				throw new RuntimeException("ERROR: The user cannot delete a step not previously in recipe");
			}
		}
	}
}