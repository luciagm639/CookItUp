package User;

import java.util.ArrayList;
import java.util.List;
import Recipe.Recipe;

public class RegisteredUser {

	String name;
	int id;
	String password;
	List listaBloqueados = new ArrayList<String>();
	List listaFollows = new ArrayList<String>();
	List listaDeRecetas = new ArrayList<String>();
	double chips;
	
	public RegisteredUser(String name, int id, String password) {
		
		this.name = name;
		this.id = id;
		this.password = password;
		listaBloqueados = null;
		listaFollows = null;
		chips = 0;
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
	
	
	
	
}
