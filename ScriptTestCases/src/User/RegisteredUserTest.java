package User;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Recipe.*;
import Reports.Report;

class RegisteredUserTest {
	
	//Users Null
	RegisteredUser us0 = null;
	RegisteredUser us1 = null;
	RegisteredUser us2 = null;
	RegisteredUser us3 = null;
	
	List<RegisteredUser> blockList = null;
	List<RegisteredUser> followList = null;

	//Recipes Null
	Recipe recipe = null;
	Recipe recipe1 = null;
	
	List<Recipe> recipesList = null;
	
	//Messages Null
	Comment comment = null;
	Question question = null;
	
	List<Comment> commentsList = null;
	List<Question> questionsList = null;

	//Photos Null
	RecipePhoto photo = null;
	Image image = null;
	
	List<RecipePhoto> photosList = null;

	//Ingredients Null
	Ingredients ingredient = null;
	Ingredients ingredient1 = null;
	
	List<Ingredients> ingredientsList = null;

	//Reports Null
	String justification = null;
	String justification1 = null;
	Report report = null;
	Report report1 = null;
	
	List<Report> reportsList = null;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {

	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {

	}

	@BeforeEach
	void setUp() throws Exception {

		// User's Creation
		us0 = new RegisteredUser("Alfonso", 0, "password");
		us1 = new RegisteredUser("Javi", 1, "password");
		us2 = new RegisteredUser("Jordan", 2, "password");
		us3 = new RegisteredUser("Jorge", 3, "password");
		
		us0.chips = 1000;
		
		

		// Recipe's Creation
		recipe = new Recipe("Tortilla de papa", 1, us3);
		recipe1 = new Recipe("Paella", 3, us3);

		// Message's Creation
		comment = new Comment(us0, "Esta bueno pero le pondria mas tiempo de coccion");
		question = new Question(us0, "A que temperatura esta el horno");

		// Photo's Creation
		photo = new RecipePhoto(image, us0, 1);

		// Ingredients Creation
		ingredient = new Ingredients("Patata");
		ingredient1 = new Ingredients("huevo");
		
		//Reports Creation
		justification = "text";
		justification1 = "text1";
		report = new Report(us0, justification);
		report1 = new Report(us1, justification1);
		
		// Reports
		reportsList = new ArrayList<Report>();


		// User's Lists Creation
		blockList = new ArrayList<RegisteredUser>();
		blockList.add(us2);
		us0.listaBloqueados = blockList;

		followList = new ArrayList<RegisteredUser>();
		followList.add(us1);
		us0.listaFollows = followList;
		
		// Message's List Creation
		commentsList = new ArrayList<Comment>();
		commentsList.add(comment);

		questionsList = new ArrayList<Question>();
		questionsList.add(question);

		// Photo's List Creation
		photosList = new ArrayList<RecipePhoto>();
		photosList.add(photo);

		// Ingredient's List Creation
		ingredientsList = new ArrayList<Ingredients>();
		ingredientsList.add(ingredient);
		

		//Recipe's Lists Initialization
		recipe.setListaComments(commentsList);
		recipe.setListaQuestions(questionsList);
		recipe.setListaPhotos(photosList);
		recipe.setListaIngredientes(ingredientsList);
		recipe.setListaReports(reportsList);

		// Recipe's Lists Creation
		recipesList = new ArrayList<Recipe>();
		recipesList.add(recipe);
		us0.listaDeRecetas = recipesList;




	
	}

	@AfterEach
	void tearDown() throws Exception {
		
		//Users Null
		us0 = null;
		us1 = null;
		us2 = null;
		us3 = null;
		
		// me gustaria resetear el  valor del us0.chips a 0 pero si lo hago todo falla xddd
		
	
		
		blockList = null;
		followList = null;
		
		//Recipes Null
		recipe = null;
		recipe1 = null;
		recipesList = null;
		
		//Messages Null
		comment = null;
		question = null;
		
		//Photos Null
		photo = null;
		
		//Ingredients Null
		ingredient = null;
		ingredient1 = null;
		
		//Reports Null
		justification = null;
		justification1 = null;
		reportsList = null;
	}

	@Test
	void usuarioBloqueaUsuarioLoAnadeAListaDeUsuariosBloqueados() {
		us0.bloquear(us1);
		assertTrue(us0.listaBloqueados.contains(us1));
	}

	@Test
	void usuarioDesbloqueaUsuarioLoQuitaDeListaDeUsuariosBloqueados() {
		us0.desbloquear(us2);
		assertTrue(us0.listaBloqueados.contains(us2) == false);
	}

	@Test
	void usuarioFollowUsuarioLoAnadeListaDeUsuariosSeguidos() {
		us0.follow(us3);
		assertTrue(us0.listaFollows.contains(us3));
	}

	@Test
	void usuarioUnfollowUsuarioLoQuitaListaDeUsuariosSeguidos() {
		us0.unfollow(us1);
		assertTrue(us0.listaFollows.contains(us2) == false);
	}

	@Test
	void deletearMiPropiaCuentaDeUsuario() {
		
	}

	@Test
	void deletearMiPropiaRecetaYQuitarDeListaDeRecetas() {
		us0.deleteRecipe(recipe);
		assertTrue(us0.listaDeRecetas.contains(recipe) == false);
	}

	@Test
	void usuarioCreaUnaRecetaYSeAnadeAListaDeRecetas() {
		us0.addRecipe(recipe1);
		assertTrue(us0.listaDeRecetas.contains(recipe1));
	}

	@Test
	void usuarioSeGastaLasChips() {
		
		
		assertTrue(us0.spendChips(500) == 500);
	}


	@Test
	void usuarioPreguntaUnaQuestionSobreUnaRecetaYSeAnadeAListaDePreguntasDeLaReceta() {
		us0.askQuestion(question, recipe);
		assertTrue(recipe.getListaQuestions().contains(question));
	}

	@Test
	void usuarioHaceComentarioSobreUnaRecetaYSeAnadeAListaDeComentariosDeLaReceta() {
		us0.hacerComentario(comment, recipe);
		assertTrue(recipe.getListaComments().contains(comment));
	}

	@Test
	void usuarioSubePhotoDeUnaRecetaYSeAnadeALaListaDePhotosDeLaReceta() {
		us0.subirPhotoDeReceta(recipe, photo);
		assertTrue(recipe.getListaPhotos().contains(photo));
	}

	@Test
	void usuarioEliminaPhotoDeUnaRecetaYSeQuitaDeLaListaDePhotosDeLaReceta() {
		us0.eliminarPhotoDeReceta(recipe, photo);
		assertTrue(recipe.getListaPhotos().contains(photo) == false);
	}

	@Test
	void usuarioAgregaIngredienteAlaRecetaYSeAnadeALaListaDeIngredientes() {
		us0.addIngredient(ingredient1, recipe);
		assertTrue(recipe.getListaIngredientes().contains(ingredient1));
	}

	@Test
	void usuarioEliminaIngredienteDelaRecetaYSeEliminaDeLaListaDeIngredientes() {
		us0.deleteIngredient(ingredient, recipe);
		assertTrue(recipe.getListaIngredientes().contains(ingredient) == false);
	}

	@Test
	void usuarioReportaRecipeYSeAgregaALaListaDeReportsDeEsaRecipe() {
		us0.reportRecipe(recipe, report1);
		assertTrue(recipe.getListaReports().contains(report1));
	}
}