package user;

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

import recipe.*;
import report.Report;
import system.MySystem;

class RegisteredUserTest {
	
	// System Null
	MySystem system = null;
	
	// Users Null
	RegisteredUser us0 = null;
	RegisteredUser us1 = null;
	RegisteredUser us2 = null;
	RegisteredUser us3 = null;
	
	// User Interface Null
	RegisteredUserInterface us0interface = null;

	// Recipes Null
	Recipe recipe = null;
	Recipe recipe1 = null;

	// Messages Null
	Comment comment = null;
	Question question = null;

	// Photos Null
	RecipePhoto photo = null;
	RecipePhoto photo1 = null;
	Image image = null;
	Image image1 = null;

	// Ingredients Null
	Ingredient ingredient = null;
	Ingredient ingredient1 = null;

	// Steps Null
	Step step0 = null;
	Step step1 = null;

	// Reports Null
	String justification = null;
	String justification1 = null;
	Report report = null;
	Report report1 = null;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {

	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {

	}

	@BeforeEach
	void setUp() throws Exception {
		
		// System set up
		system = new MySystem();
		system.empty();

		// Users' Creation
		us0 = new RegisteredUser("Alfonso", 0, "password");
		us1 = new RegisteredUser("Javi", 1, "password");
		us2 = new RegisteredUser("Jordan", 2, "password");
		us3 = new RegisteredUser("Jorge", 3, "password");
		
		//TODO addition to the system
		
		// User interface
		us0interface = new RegisteredUserInterface(us0, system);
		
		// Recipes
		recipe = new Recipe("Tortilla de papa", 1, us3);
		recipe1 = new Recipe("Huevo frito", 3, us3);
		system.addRecipe(recipe);
		
		// Messages
		Comment comment = new Comment(us0, "Esta bueno pero le pondria mas tiempo de coccion");
		recipe.addComment(comment);
		Question question = new Question(us0, "¿De qué tamaño son los huevos que usas?");
		recipe.addQuestion(question);
		
		// Ingredients
		Ingredient ingredient = new Ingredient("Patata");
		Ingredient ingredient1 = new Ingredient("Huevo");
		system.addIngredient(ingredient);
		system.addIngredient(ingredient1);
		recipe.addIngredient(ingredient);
		recipe.addIngredient(ingredient1);
		recipe1.addIngredient(ingredient1);
		
		// Steps
		step0 = new Step(5, "Dejar que el huevo se ponga a temperatura ambiente durante 5 minutos");
		step1 = new Step(0, "Pelar de 3 a 5 patatas");
		
		//TODO add to recipe
		

		// Reports Creation
		justification = "text";
		justification1 = "text1";
		report = new Report(us0, justification);
		report1 = new Report(us1, justification1);
		
		// User chips
		us0.setChips(1000);

		// User Block and Follow
		us0.block(us2);
		us0.follow(us1);		
	}

	@AfterEach
	void tearDown() throws Exception {
		
		// System Null
		system = null;
		
		// Users Null
		us0 = null;
		us1 = null;
		us2 = null;
		us3 = null;
		
		// User Interface Null
		us0interface = null;

		// Recipes Null
		recipe = null;
		recipe1 = null;

		// Messages Null
		comment = null;
		question = null;

		// Photos Null
		photo = null;
		photo1 = null;
		image = null;
		image1 = null;

		// Ingredients Null
		ingredient = null;
		ingredient1 = null;

		// Steps null;
		step0 = null;
		step1 = null;

		// Reports Null
		justification = null;
		justification1 = null;
	}
	
	@Test
	void usuarioBloqueaUsuarioLoAnadeAListaDeUsuariosBloqueados() {
		us0.block(us1);
		boolean bool0 = us0.isBlocked(us1);
		Exception exception = assertThrows(RuntimeException.class, () -> us0.block(us1));
		boolean bool1 = ("ERROR: The user cannot block another user already blocked").equals(exception.getMessage());
		assertTrue(bool0 && bool1);
	}
	
	@Test
	void usuarioDesbloqueaUsuarioLoQuitaDeListaDeUsuariosBloqueados() {
		us0.unblock(us2);
		boolean bool0 = us0.isBlocked(us2);
		Exception exception = assertThrows(RuntimeException.class, () -> us0.unblock(us2));
		boolean bool1 = ("ERROR: The user cannot unblock another user not previously blocked").equals(exception.getMessage());
		assertTrue(!bool0 && bool1);
	}

	@Test
	void usuarioFollowUsuarioLoAnadeListaDeUsuariosSeguidos() {
		us0.follow(us3);
		boolean bool0 = us0.isFollowed(us3);
		Exception exception = assertThrows(RuntimeException.class, () -> us0.follow(us3));
		boolean bool1 = ("ERROR: The user cannot follow another user already followed").equals(exception.getMessage());
		assertTrue(bool0 && bool1);
	}

	@Test
	void usuarioUnfollowUsuarioLoQuitaListaDeUsuariosSeguidos() {
		us0.unfollow(us1);
		boolean bool0 = us0.isFollowed(us1);
		Exception exception = assertThrows(RuntimeException.class, () -> us0.unfollow(us1));
		boolean bool1 = ("ERROR: The user cannot unfollow a user not previously followed").equals(exception.getMessage());
		assertTrue(!bool0 && bool1);
	}

	@Test
	void deletearMiPropiaCuentaDeUsuario() {

	}
	
	@Test
	void deletearMiPropiaRecetaYQuitarDeListaDeRecetas() {
		us0.deleteRecipe(recipe);
		boolean bool0 = system.hasRecipe(recipe);
		Exception exception = assertThrows(RuntimeException.class, () -> us0.deleteRecipe(recipe));
		boolean bool1 = ("ERROR: The user cannot delete a recipe not previously created").equals(exception.getMessage());
		assertTrue(!bool0 && bool1);
	}

	@Test
	void usuarioCreaUnaRecetaYSeAnadeAListaDeRecetas() {
		us0interface.createRecipe(recipe1.getName());
		boolean bool0 = system.hasRecipe(recipe1);
		Exception exception = assertThrows(RuntimeException.class, () -> us0.addRecipe(recipe1));
		boolean bool1 = ("ERROR: The user cannot add a recipe already done").equals(exception.getMessage());
		assertTrue(bool0 && bool1);
	}
	
	@Test
	void usuarioSeGastaLasChips() {
		boolean bool0 = us0.spendChips(500) == 500;
		Exception exception = assertThrows(RuntimeException.class, () -> us0.spendChips(1500));
		boolean bool1 = ("ERROR: The user cannot spend more chips that it has").equals(exception.getMessage());
		assertTrue(bool0 && bool1);
	}
	
	@Test
	void usuarioPreguntaUnaQuestionSobreUnaRecetaYSeAnadeAListaDePreguntasDeLaReceta() {
		recipe.addQuestion(question);
		boolean bool0 = recipe.getQuestionsList().contains(question);
		Exception exception = assertThrows(RuntimeException.class, () -> recipe.addQuestion(question));
		boolean bool1 = ("ERROR: The user cannot ask the same question").equals(exception.getMessage());
		assertTrue(bool0 && bool1);
	}

	@Test
	void usuarioHaceComentarioSobreUnaRecetaYSeAnadeAListaDeComentariosDeLaReceta() {
		recipe.addComment(comment);
		assertTrue(recipe.getCommentsList().contains(comment));
	}
	
	//Por ahora no vamos a usar las fotos
	/*
	@Test
	void usuarioSubePhotoDeUnaRecetaYSeAnadeALaListaDePhotosDeLaReceta() {
		us0.uploadRecipePhoto(recipe, photo);
		boolean bool0 = recipe.getPhotosList().contains(photo);
		Exception exception = assertThrows(RuntimeException.class, () -> us0.uploadRecipePhoto(recipe, photo));
		boolean bool1 = ("ERROR: The user cannot upload the same recipe photo").equals(exception.getMessage());
		assertTrue(bool0 && bool1);
	}

	@Test
	void usuarioEliminaPhotoDeUnaRecetaYSeQuitaDeLaListaDePhotosDeLaReceta() {
		us0.deleteRecipePhoto(recipe, photo1);
		boolean bool0 = recipe.getPhotosList().contains(photo1);
		Exception exception = assertThrows(RuntimeException.class, () -> us0.deleteRecipePhoto(recipe, photo1));
		boolean bool1 = ("ERROR: The user cannot delete a recipe photo not previously done").equals(exception.getMessage());
		assertTrue(!bool0 && bool1);
	}
	*/
	
	@Test
	void usuarioAgregaIngredienteAlaRecetaYSeAnadeALaListaDeIngredientes() {
		recipe.addIngredient(ingredient1);
		boolean bool0 = recipe.getIngredientsList().contains(ingredient1);
		Exception exception = assertThrows(RuntimeException.class, () -> recipe.addIngredient(ingredient1));
		boolean bool1 = ("ERROR: The user cannot add the same ingredient").equals(exception.getMessage());
		assertTrue(bool0 && bool1);
	}

	@Test
	void usuarioEliminaIngredienteDelaRecetaYSeEliminaDeLaListaDeIngredientes() {
		recipe.deleteIngredient(ingredient);
		boolean bool0 = recipe.getIngredientsList().contains(ingredient);
		Exception exception = assertThrows(RuntimeException.class, () -> recipe.deleteIngredient(ingredient));
		boolean bool1 = ("ERROR: The user cannot delete an ingredient not previously in the recipe").equals(exception.getMessage());
		assertTrue(!bool0 && bool1);
	}
	
	//He quitado la lista de reports de la receta
	/*
	@Test
	void usuarioReportaRecipeYSeAgregaALaListaDeReportsDeEsaRecipe() {
		us0.reportRecipe(recipe, report);
		boolean bool0 = false;
		for (int i = 0; i < recipe.getReportsList().size(); i++) {
				if (recipe.getReportsList().get(i).getReporting() == report.reporting)
					bool0 = true;
			}
		Exception exception = assertThrows(RuntimeException.class, () -> 
		us0.reportRecipe(recipe, report));
		boolean bool1 = ("ERROR: The user cannot report a recipe already been reported").equals(exception.getMessage());
		assertTrue(bool0 && bool1);
	}
	*/
	
	@Test
	void usuarioAgregaPasoAUnaReceteYSeAgeregaALaListaDeStepsDeLaReceta() {
		recipe.addStep(step0);
		boolean bool0 = recipe.getStepsList().contains(step0);
		Exception exception = assertThrows(RuntimeException.class, () -> recipe.addStep(step0));
		boolean bool1 = ("ERROR: The user cannot add a step already existing").equals(exception.getMessage());
		assertTrue(bool0 && bool1);
	}
	
	@Test
	void usuarioQuitaPasoAUnaReceteYSeEliminaDeLaListaDeStepsDeLaReceta() {
		recipe.deleteStep(step1);
		boolean bool0 = !recipe.getStepsList().contains(step1);
		Exception exception = assertThrows(RuntimeException.class, () -> recipe.deleteStep(step1));
		boolean bool1 = ("ERROR: There user cannot delete a step not previously created").equals(exception.getMessage());
		Exception exception2 = assertThrows(RuntimeException.class, () -> recipe.deleteStep(step1));
		boolean bool2 = ("ERROR: The user cannot delete a step not previously in recipe").equals(exception2.getMessage());
		assertTrue(bool0 && bool1 && bool2);
	}
	
	/*
	@Test
	void usuarioReportaUsuarioYSeAgregaALaListaDeReportsDeLosUsuarios() {
		us0.reportUser(us1, report);
		Boolean bool0 = us1.getReportsList().contains(report);
		Exception exception = assertThrows(RuntimeException.class, () -> us0.reportUser(us1, report));
		Boolean bool1 = ("ERROR: The user cannot report an user already been reported").equals(exception.getMessage());
		assertTrue(bool0 && bool1);
	}
	*/
	
	/*
	@Test
	void usuarioReportaPreguntaYSeAgregaALaListaDeReportsDeLasPreguntas() {
		us0.reportQuestion(question, report);
		Boolean bool0 = question.getReportsList().contains(report);
		Exception exception = assertThrows(RuntimeException.class, () -> us0.reportQuestion(question, report));
		Boolean bool1 = ("ERROR: The user cannot report a question already been reported").equals(exception.getMessage());
		assertTrue(bool0 && bool1);
	}
	
	@Test
	void usuarioReportaMensageYSeAgregaALaListaDeReportsDeLosMessages() {
		us0.reportComment(comment, report);
		Boolean bool0 = comment.getReportsList().contains(report);
		Exception exception = assertThrows(RuntimeException.class, () -> us0.reportComment(comment, report));
		Boolean bool1 = ("ERROR: The user cannot report a comment already been reported").equals(exception.getMessage());
		assertTrue(bool0 && bool1);
	}
	*/
}