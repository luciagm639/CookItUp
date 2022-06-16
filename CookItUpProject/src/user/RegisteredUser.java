package user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import recipe.Comment;
import recipe.Ingredient;
import recipe.Question;
import recipe.Recipe;
import report.Report;
import system.data.Data;

public class RegisteredUser extends Data<RegisteredUser> {

	private String name;
	private String password;
	private Set<Ingredient> fridge = new HashSet<>();
	private int chips;
	private boolean status;
  
	
	public RegisteredUser(String name, int id, String password, int chips, boolean status) {
		super(id);
		this.name = name;
		this.password = password;
		this.chips = chips;
		this.status = status;
	}
	
	public RegisteredUser(String name, int id, String password) {
		this(name, id, password, 0, false);
		this.name = name;
		this.password = password;
	}
	
	public RegisteredUser(String name, String password) {
		this.name = name;
		this.password = password;
		this.chips = 0;
		this.status = false;
	}
	  
	public RegisteredUser(RegisteredUser user) {
		super(user.getId());
		name = user.name;
		password = user.password;
		fridge = user.fridge;
		chips = user.chips;
		status = user.status;
	}

	@Override
	public String toString() {
	  return name;
	}
	
	@Override
	public boolean equals(Object o) {
		return (o instanceof RegisteredUser) &&
				(((RegisteredUser) o).name.equalsIgnoreCase(name));
	}
	
	@Override
	public int hashCode() {
		return name.toLowerCase().hashCode();
	}
	  
	public String showProfile() {
		StringBuilder sb = new StringBuilder();
		sb.append("Showing user " + this.getId() + ":\n");
		sb.append("Name: " + this.name + "\n");
		sb.append("Chips : " + this.getChips() + "\n");
		return sb.toString();	
	}

	public String getName() {
		return name;
   }

	public String getPassword() {
		return password;
	}

	

	

	public boolean getStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
	/*Chips*/
	public static final int CREATE_NEW_RECIPE = 1;
	public static final int QUESTION_OR_COMMENT = 0;
	public void obtainChips(int i) {
		if (i == CREATE_NEW_RECIPE) {
			setChips(getChips() + 20);
		} else {
			setChips(getChips() + 10);
		}
	}
	
	public int spendChips(int amount) {
		if (this.getChips() - amount >= 0) {
			this.setChips(this.getChips() - amount);
			return this.getChips();
		} else {
			System.err.println("ERROR: The user cannot spend more chips that it has");
			return this.getChips();
		}
	}

	public int getChips() {
		return chips;
	}

	public void setChips(int chips) {
		this.chips = chips;
	}
  
	/*Fridge*/
	public Set<Ingredient> getFridge() {
		return fridge;
	}
	
	public boolean addIngredientToFridge(Ingredient i) {
		return fridge.add(i);
	}
	
	public boolean deleteIngredientFromFridge(Ingredient i) {
		return fridge.remove(i);
	}
	
	
	
	//Por ahora no vamos a usar las fotos
	/*
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

	public void deleteRecipePhoto(Recipe recipe, RecipePhoto photo) {
		if (recipe.getPhotosList() == null || !recipe.getPhotosList().contains(photo)) {
			throw new RuntimeException("ERROR: The user cannot delete a recipe photo not previously done");
		} else {
			recipe.getPhotosList().remove(photo);
		}
	}
	
	*/
}