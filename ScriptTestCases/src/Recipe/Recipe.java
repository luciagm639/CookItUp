package Recipe;

import java.util.ArrayList;
import java.util.List;

import Reports.Report;
import User.*;

public class Recipe {

	RegisteredUser usuario;
	String name;
	int priority;
	List<Comment> listaComments = new ArrayList<Comment>();
	List<Question> listaQuestions = new ArrayList<Question>();
	List<Ingredients> listaIngredientes = new ArrayList<Ingredients>();
	List<RecipePhoto> listaPhotos = new ArrayList<RecipePhoto>();
	List<Report> listaReports = new ArrayList<Report>();
	
	public List<Report> getListaReports() {
		return listaReports;
	}

	public void setListaReports(List<Report> listaReports) {
		this.listaReports = listaReports;
	}

	public Recipe (String name,int priority,RegisteredUser usuario){
		this.name = name;
		this.listaComments = null;
		this.listaIngredientes = null;
		this.listaQuestions = null;
		this.priority = priority;
		this.usuario = usuario;
	}
	
	public RegisteredUser getUsuario() {
		return usuario;
	}
	
	public void setUsuario(RegisteredUser usuario) {
		this.usuario = usuario;
	}
	
	public List<RecipePhoto> getListaPhotos() {
		return listaPhotos;
	}

	public void setListaPhotos(List<RecipePhoto> listaPhotos) {
		this.listaPhotos = listaPhotos;
	}

	public List<Comment> getListaComments() {
		return listaComments;
	}

	public List<Question> getListaQuestions() {
		return listaQuestions;
	}

	public List<Ingredients> getListaIngredientes() {
		return listaIngredientes;
	}

	public void setListaComments(List<Comment> listaComments) {
		this.listaComments = listaComments;
	}

	public void setListaQuestions(List<Question> listaQuestions) {
		this.listaQuestions = listaQuestions;
	}

	public void setListaIngredientes(List<Ingredients> listaIngredientes) {
		this.listaIngredientes = listaIngredientes;
	}
}