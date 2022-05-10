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

	// Users Null
	RegisteredUser us0 = null;
	RegisteredUser us1 = null;
	RegisteredUser us2 = null;
	RegisteredUser us3 = null;

	List<RegisteredUser> blockList = null;
	List<RegisteredUser> followList = null;

	// Recipes Null
	Recipe recipe = null;
	Recipe recipe1 = null;

	List<Recipe> recipesList = null;

	// Messages Null
	Comment comment = null;
	Question question = null;

	List<Comment> commentsList = null;
	List<Question> questionsList = null;

	// Photos Null
	RecipePhoto photo = null;
	RecipePhoto photo1 = null;
	Image image = null;
	Image image1 = null;

	List<RecipePhoto> photosList = null;

	// Ingredients Null
	Ingredients ingredient = null;
	Ingredients ingredient1 = null;

	List<Ingredients> ingredientsList = null;

	// Steps Null
	Step step = null;
	Step step1 = null;

	List<Step> stepsList = null;

	// Reports Null
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
		photo1 = new RecipePhoto(image1, us0, 2);

		// Ingredients Creation
		ingredient = new Ingredients("Patata");
		ingredient1 = new Ingredients("huevo");

		// Step Creation
		step = new Step(5, "Dejar que el huevo se ponga a temperatura ambiente durante 5 minutos");
		step1 = new Step(0, "Pelar de 3 a 5 patatas");

		// Reports Creation
		justification = "text";
		justification1 = "text1";
		report = new Report(us0, justification);
		report1 = new Report(us1, justification1);

		// User's Lists Creation
		blockList = new ArrayList<RegisteredUser>();
		blockList.add(us2);
		us0.blockList = blockList;

		followList = new ArrayList<RegisteredUser>();
		followList.add(us1);
		us0.followList = followList;

		// Recipe's Lists Creation
		recipesList = new ArrayList<Recipe>();
		recipesList.add(recipe);
		us0.recipesList = recipesList;

		// Message's List Creation
		commentsList = new ArrayList<Comment>();

		questionsList = new ArrayList<Question>();

		// Photo's List Creation
		photosList = new ArrayList<RecipePhoto>();
		photosList.add(photo1);

		// Ingredient's List Creation
		ingredientsList = new ArrayList<Ingredients>();
		ingredientsList.add(ingredient);

		// Step's List Creation
		stepsList = new ArrayList<Step>();
		stepsList.add(step1);

		// Report's List Creation
		reportsList = new ArrayList<Report>();

		// Recipe's Lists Initialization
		recipe.setCommentsList(commentsList);
		recipe.setQuestionsList(questionsList);
		recipe.setPhotosList(photosList);
		recipe.setIngredientsList(ingredientsList);
		recipe.setReportsList(reportsList);
		recipe.setStepsList(stepsList);
	}

	@AfterEach
	void tearDown() throws Exception {

		// Users Null
		us0 = null;
		us1 = null;
		us2 = null;
		us3 = null;

		blockList = null;
		followList = null;

		// Recipes Null
		recipe = null;
		recipe1 = null;

		recipesList = null;

		// Messages Null
		comment = null;
		question = null;

		commentsList = null;
		questionsList = null;

		// Photos Null
		photo = null;
		photo1 = null;
		image = null;
		image1 = null;

		photosList = null;

		// Ingredients Null
		ingredient = null;
		ingredient1 = null;

		ingredientsList = null;

		// Steps null;
		step = null;
		step1 = null;

		stepsList = null;

		// Reports Null
		justification = null;
		justification1 = null;

		reportsList = null;
	}

	@Test
	void usuarioBloqueaUsuarioLoAnadeAListaDeUsuariosBloqueados() {
		us0.block(us1);
		assertTrue(us0.blockList.contains(us1));
	}

	@Test
	void usuarioDesbloqueaUsuarioLoQuitaDeListaDeUsuariosBloqueados() {
		us0.unblock(us2);
		assertTrue(us0.blockList.contains(us2) == false);
	}

	@Test
	void usuarioFollowUsuarioLoAnadeListaDeUsuariosSeguidos() {
		us0.follow(us3);
		assertTrue(us0.followList.contains(us3));
	}

	@Test
	void usuarioUnfollowUsuarioLoQuitaListaDeUsuariosSeguidos() {
		us0.unfollow(us1);
		assertTrue(us0.followList.contains(us2) == false);
	}

	@Test
	void deletearMiPropiaCuentaDeUsuario() {

	}

	@Test
	void deletearMiPropiaRecetaYQuitarDeListaDeRecetas() {
		us0.deleteRecipe(recipe);
		assertTrue(us0.recipesList.contains(recipe) == false);
	}

	@Test
	void usuarioCreaUnaRecetaYSeAnadeAListaDeRecetas() {
		us0.addRecipe(recipe1);
		assertTrue(us0.recipesList.contains(recipe1));
	}

	@Test
	void usuarioSeGastaLasChips() {
		assertTrue(us0.spendChips(500) == 500);
	}

	@Test
	void usuarioPreguntaUnaQuestionSobreUnaRecetaYSeAnadeAListaDePreguntasDeLaReceta() {
		us0.askQuestion(question, recipe);
		assertTrue(recipe.getQuestionsList().contains(question));
	}

	@Test
	void usuarioHaceComentarioSobreUnaRecetaYSeAnadeAListaDeComentariosDeLaReceta() {
		us0.makeComment(comment, recipe);
		assertTrue(recipe.getCommentsList().contains(comment));
	}

	@Test
	void usuarioSubePhotoDeUnaRecetaYSeAnadeALaListaDePhotosDeLaReceta() {
		us0.uploadRecipePhoto(recipe, photo);
		assertTrue(recipe.getPhotosList().contains(photo));
	}

	@Test
	void usuarioEliminaPhotoDeUnaRecetaYSeQuitaDeLaListaDePhotosDeLaReceta() {
		us0.deleteRecipePhoto(recipe, photo1);
		assertTrue(recipe.getPhotosList().contains(photo1) == false);
	}

	@Test
	void usuarioAgregaIngredienteAlaRecetaYSeAnadeALaListaDeIngredientes() {
		us0.addIngredient(ingredient1, recipe);
		assertTrue(recipe.getIngredientsList().contains(ingredient1));
	}

	@Test
	void usuarioEliminaIngredienteDelaRecetaYSeEliminaDeLaListaDeIngredientes() {
		us0.deleteIngredient(ingredient, recipe);
		assertTrue(recipe.getIngredientsList().contains(ingredient) == false);
	}

	@Test
	void usuarioReportaRecipeYSeAgregaALaListaDeReportsDeEsaRecipe() {
		us0.reportRecipe(recipe, report1);
		assertTrue(recipe.getReportsList().contains(report1));
	}
	
	@Test
	void usuarioAgregaPasoAUnaReceteYSeAgeregaALaListaDeStepsDeLaReceta() {
		us0.addStep(recipe, step);
		assertTrue(recipe.getStepsList().contains(step));
	}
	
	@Test
	void usuarioQuitaPasoAUnaReceteYSeAgeregaALaListaDeStepsDeLaReceta() {
		us0.deleteStep(recipe, step1);
		assertTrue(recipe.getStepsList().contains(step1) == false);
	}
}