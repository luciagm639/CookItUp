package User;

import java.util.ArrayList;
import java.util.List;

import Recipe.Ingredients;
import Recipe.Recipe;
import Recipe.RecipePhoto;
import Reports.Report;

public class RegisteredUser {

	String name;
	int id;
	String password;
	List<RegisteredUser> listaBloqueados = new ArrayList<RegisteredUser>();
	List<RegisteredUser> listaFollows = new ArrayList<RegisteredUser>();
	List<Recipe> listaDeRecetas = new ArrayList<Recipe>();
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

	public List<RegisteredUser> listaBloqueados(RegisteredUser usuario) {
		
		return usuario.listaBloqueados;
	}
	
	public List<RegisteredUser> listaFollows(RegisteredUser usuario) {
		
		return usuario.listaFollows;
	}
	
	public void bloquear(RegisteredUser usuario) {
		if(this.listaBloqueados.contains(usuario)) {
			throw new RuntimeException("The user you are trying to block is already blocked");
		} else {
			this.listaBloqueados.add(usuario);
		}
	}
	
	public void desbloquear(RegisteredUser usuario) {
		if(this.listaBloqueados.contains(usuario)) {
			this.listaBloqueados.remove(usuario);
		} else {
			throw new RuntimeException("That user is not blocked");
		}
	}
	
	public void follow(RegisteredUser usuario) {
		if(this.listaFollows.contains(usuario)) {
			throw new RuntimeException("Ypu already follow this user");
		} else {
			this.listaFollows.add(usuario);
		}
	}
	
	public void unfollow(RegisteredUser usuario) {
		if(this.listaFollows.contains(usuario)) {
			this.listaFollows.remove(usuario);
		} else {
			throw new RuntimeException("You do not follow this user");
		}
	}
	
	public void deleteOwnAccount() {	
		
	}

	public void deleteRecipe(Recipe receta) {
		if(this.listaDeRecetas.contains(receta)) {
			this.listaDeRecetas.remove(receta);
		} else {
			throw new RuntimeException("You can not delete a recipe that you have not created");
		}
	}
	
	public void addRecipe(Recipe receta) {
		if(this.listaDeRecetas.contains(receta)) {
			throw new RuntimeException("You already have this recipe");
		} else {
			this.listaDeRecetas.add(receta);
		}	
	}
	
	public double spendChips(double cantidad) {
		if(this.chips - cantidad >= 0) {
			return this.chips - cantidad;
		} else {
			throw new RuntimeException("Usuario se gasta mas de lo que tiene");
		}
	}

	public void askQuestion(Question question, Recipe receta) {
		receta.getListaQuestions().add(question);
	}

	public void hacerComentario(Comment comment, Recipe receta) {
		receta.getListaComments().add(comment);
	}

	public void subirPhotoDeReceta(Recipe receta, RecipePhoto photo) {
		receta.getListaPhotos().add(photo);
	}

	public void addIngredient(Ingredients ingrediente, Recipe receta) {
		receta.getListaIngredientes().add(ingrediente);
	}

	public void deleteIngredient(Ingredients ingrediente1, Recipe receta) {
		if(receta.getListaIngredientes().contains(ingrediente1)) {
			receta.getListaIngredientes().remove(ingrediente1);
		} else {
			throw new RuntimeException("Ypu trying to delete an ingredient that is not in the recipe");
		}
		
	}

	public void eliminarPhotoDeReceta(Recipe receta, RecipePhoto photo) {
		if(receta.getListaPhotos().contains(photo)) {
			receta.getListaPhotos().remove(photo);
		} else {
			throw new RuntimeException("You can not delete a photo that does not exist");
		}
	}

	public void reportRecipe(Recipe recipe, Report report1) {
		
	}
}