package User;

import java.util.ArrayList;
import java.util.List;

import Recipe.Ingredients;
import Recipe.Recipe;
import Recipe.RecipePhoto;

public class RegisteredUser {

	String name;
	int id;
	String password;
	List listaBloqueados = new ArrayList<RegisteredUser>();
	List listaFollows = new ArrayList<RegisteredUser>();
	List listaDeRecetas = new ArrayList<Recipe>();
	double chips;
	
	public RegisteredUser(String name, int id, String password) {	
		this.name = name;
		this.id = id;
		this.password = password;
		listaBloqueados = null;
		listaFollows = null;
		listaDeRecetas = null;
		chips = 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List listaBloqueados(RegisteredUser usuario) {
		
		return usuario.listaBloqueados;
	}
	public List listaFollows(RegisteredUser usuario) {
		
		return usuario.listaFollows;
	}
	
	public void bloquear(RegisteredUser usuario) {
		
	}
	
	public void desbloquear(RegisteredUser usuario) {
		
	}
	
	public void follow(RegisteredUser usuario) {
		
	}
	
	public void unfollow(RegisteredUser usuario) {
		
	}
	
	public void deleteOwnAccount() {
		
		
	}

	public void deleteRecipe(Recipe receta) {
		
	}
	
	public void addRecipe(Recipe receta) {
		
	}
	
	public double spendChips(double cantidad) {
		if(this.chips - cantidad >= 0) {
			return this.chips - cantidad;
		} else {
			throw new RuntimeException("Usuario se gasta mas de lo que tiene");
		}
	}

	public void askQuestion(Question question, Recipe receta) {	
		
	}

	public void hacerComentario(Comment comment, Recipe receta) {	
		
	}

	public void subirPhotoDeReceta(Recipe receta, RecipePhoto photo) {
		
	}

	public void addIngredient(Ingredients ingrediente, Recipe receta) {	
		
	}

	public void deleteIngredient(Ingredients ingrediente1, Recipe receta) {	
		
	}

	public void eliminarPhotoDeReceta(Recipe receta, RecipePhoto photo) {
		// TODO Auto-generated method stub
	}
}