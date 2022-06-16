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

import administrator.Administrator;
import administrator.AdministratorInterface;
import gson.ClientSystem;
import recipe.*;
import report.Report;
import report.ReportRecipe;
import report.ReportUser;

class RegisteredUserInterfaceTest {
	
	// System Null
	ClientSystem system = null;
	
	// Users Null
	RegisteredUser us0 = null;
	RegisteredUser us1 = null;
	RegisteredUser us2 = null;
	RegisteredUser us3 = null;
	
	// User Interface Null
	RegisteredUserInterface us0interface = null;

	// Recipes Null
	Recipe recipe0 = null;
	Recipe recipe1 = null;
	Recipe recipe2 = null;

	// Messages Null
	Comment comment = null;
	Question question = null;

	// Photos Null
	RecipePhoto photo0 = null;
	RecipePhoto photo1 = null;

	// Ingredients Null
	Ingredient ingredient0 = null;
	Ingredient ingredient1 = null;

	// Steps Null
	Step step0 = null;
	Step step1 = null;

	// Reports Null
	Report report0 = null;
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
		system = new ClientSystem();
		system.empty();		

		// Users' Creation
		us0 = new RegisteredUser("Alfonso", 0, "password");
		us0interface = new RegisteredUserInterface(us0, system);
		us1 = new RegisteredUser("Javi", 1, "password");
		us2 = new RegisteredUser("Jordan", 2, "password");
		us3 = new RegisteredUser("Jorge", 3, "password");
		
		us0interface.block(us1);
		us0interface.follow(us1);
		
		// Recipes
		recipe0 = new Recipe("Tortilla de papa", 1, us0);
		recipe1 = new Recipe("Huevo frito", 3, us0);
		recipe2 = new Recipe("Macarrones", 2, us2);
		
		us0.addRecipe(recipe0);
		system.addRecipe(recipe0);
		
		us2.addRecipe(recipe2);
		system.addRecipe(recipe2);
		
		// Messages
		comment = new Comment(us0, "Esta bueno pero le pondria mas tiempo de coccion", recipe2);
		
		question = new Question(us0, "¿De qué tamaño son los huevos que usas?", recipe2);
		
		// Photos
		
		
		// Ingredients
		ingredient0 = new Ingredient("Patata");
		ingredient1 = new Ingredient("Huevo");
		
		us0interface.addIngredient(ingredient0.getName(), recipe0);
		system.addIngredient(ingredient0);
		
		// Steps
		step0 = new Step(5, "Dejar que el huevo se ponga a temperatura ambiente durante 5 minutos");
		step1 = new Step(0, "Pelar de 3 a 5 patatas");
		
		us0interface.addStep(step0.getTime(), step0.getDescription(), 0, recipe0);
		
		// Reports Creation
		report0 = new ReportUser(us0, "justification0", us1);
		report1 = new ReportRecipe(us1, "justification1", recipe0);
		
		// User chips
		us0.setChips(1000);	
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
		
		us0interface.unblock(us1);
		us0interface.unfollow(us1);
		
		// User Interface Null
		us0interface = null;

		// Recipes Null
		recipe0 = null;
		recipe1 = null;
		recipe2 = null;
		

		// Messages Null
		comment = null;
		question = null;

		// Photos Null
		photo0 = null;
		photo1 = null;

		// Ingredients Null
		ingredient0 = null;
		ingredient1 = null;

		// Steps null;
		step0 = null;
		step1 = null;

		// Reports Null
		report0 = null;
		report1 = null;
	}
	
	@Test
	void usuarioBloqueaUsuarioLoAnadeAListaDeUsuariosBloqueados() {
		us0interface.block(us2);
		assertTrue(us0interface.isBlocked(us2));
		assertFalse(us0interface.block(us2));
	}
	
	@Test
	void usuarioDesbloqueaUsuarioLoQuitaDeListaDeUsuariosBloqueados() {
		us0interface.unblock(us1);
		assertFalse(us0interface.unblock(us1));
	}

	@Test
	void usuarioFollowUsuarioLoAnadeListaDeUsuariosSeguidos() {
		us0interface.follow(us2);
		assertTrue(us0interface.isFollowed(us2));
		assertFalse(us0interface.follow(us2));
	}

	@Test
	void usuarioUnfollowUsuarioLoQuitaListaDeUsuariosSeguidos() {
		us0interface.unfollow(us1);
		assertFalse(us0interface.isFollowed(us1));
		assertFalse(us0interface.unfollow(us1));
		}

	@Test
	void deletearMiPropiaCuentaDeUsuario() {
		
	}

	@Test
	void usuarioCreaUnaRecetaYSeAnadeAListaDeRecetas() {
		Recipe rec = us0interface.createRecipe(recipe1.getName());
		assertTrue(system.hasRecipe(rec) && us0.getRecipesList().contains(rec));
		assertNull(us0interface.createRecipe(recipe1.getName()));
	}
	
	@Test
	void deletearMiPropiaRecetaYQuitarDeListaDeRecetas() {
		us0interface.deleteRecipe(recipe0);
		assertFalse(system.hasRecipe(recipe0));
	}
	
	@Test
	void usuarioSeGastaLasChips() {
		assertTrue(us0interface.spendChips(500) == 500);
	}
	
	@Test
	void usuarioPreguntaUnaQuestionSobreUnaRecetaYSeAnadeAListaDePreguntas() {
		us0interface.askQuestion(question.getText(), recipe2);
		assertTrue(system.getQuestions().contains(question));
		assertFalse (us0interface.askQuestion(question.getText(), recipe2));
	}

	@Test
	void usuarioHaceComentarioSobreUnaRecetaYSeAnadeAListaDeComentarios() {
		us0interface.makeComment(comment.getText(), recipe2);
		assertTrue(system.getComments().contains(comment));
	}
	
	@Test
	void usuarioAgregaIngredienteAlaRecetaYSeAnadeALaListaDeIngredientes() {
		us0interface.addIngredient(ingredient1.getName(), recipe0);
		assertTrue(recipe0.getIngredientsList().contains(ingredient1));
	}

	@Test
	void usuarioEliminaIngredienteDelaRecetaYSeEliminaDeLaListaDeIngredientes() {
		us0interface.deleteIngredient(ingredient0.getName(), recipe0);
		assertFalse(recipe0.getIngredientsList().contains(ingredient0));
	}
	
	@Test
	void usuarioAgregaPasoAUnaReceteYSeAgeregaALaListaDeStepsDeLaReceta() {
		us0interface.addStep(step1.getTime(), step1.getDescription(), 1, recipe0);
		assertTrue(recipe0.getStepsList().contains(step1));
	}
	
	@Test
	void usuarioQuitaPasoAUnaReceteYSeEliminaDeLaListaDeStepsDeLaReceta() {
		us0interface.deleteStep(0, recipe0);
		assertFalse(recipe0.getStepsList().contains(step0));
	}
}
