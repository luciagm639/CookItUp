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

class RegisteredUserTest {

	RegisteredUser us0 = null;
	RegisteredUser us1 = null;
	RegisteredUser us2 = null;
	RegisteredUser us3 = null;
	
	Recipe receta = null;
	Recipe receta1 = null;
	
	Question question = null;
	Comment comment = null;
	
	RecipePhoto photo = null;
	Ingredients ingrediente = null;
	Ingredients ingrediente1 = null;
	
	Image imagen = null;
	
	List<RegisteredUser> listaBloqueados = null;
	List<RegisteredUser> listaFollows = null;
	List<Recipe> listaDeRecetas = null;	
	
	List<Comment> listaComments = null;
	List<Question> listaQuestions = null;
	List<Ingredients> listaIngredientes = null;
	List<RecipePhoto> listaPhotos = null;
	
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		
	}

	@BeforeEach
	void setUp() throws Exception {
		
		 us0 = new RegisteredUser("Alfonso", 0, "password");
		 us1 = new RegisteredUser("Javi", 1, "password");
		 us2 = new RegisteredUser("Jordan", 2, "password");
		 us3 = new RegisteredUser("Jorge", 3, "password");
		 
		 receta = new Recipe("Tortilla de papa",1,us3);
		 receta1 = new Recipe("Paella",3,us3);
		 
		 question = new Question(us0,"A que temperatura esta el horno");
		 comment = new Comment(us0,"Esta bueno pero le pondria mas tiempo de coccion");
		 
		 photo = new RecipePhoto(imagen,us0);
		 
		 ingrediente = new Ingredients("Patata");
		 ingrediente1 = new Ingredients("huevo");
		 
		 //Usuario
		 listaBloqueados = new ArrayList<RegisteredUser>();
		 listaFollows = new ArrayList<RegisteredUser>();
		 listaDeRecetas = new ArrayList<Recipe>();
		 //Recipe
		 listaComments = new ArrayList<Comment>();
		 listaQuestions = new ArrayList<Question>();
		 listaPhotos = new ArrayList<RecipePhoto>();
		 
		 listaBloqueados.add(us2);
		 listaFollows.add(us1);
		 listaDeRecetas.add(receta);
		 
		 listaComments.add(comment);
		 listaQuestions.add(question);
		 listaPhotos.add(photo);
		 listaIngredientes.add(ingrediente);
		 
		 us0.listaBloqueados = listaBloqueados;
		 us0.listaFollows = listaFollows;
		 us0.listaDeRecetas = listaDeRecetas;
		 
		receta.setListaComments(listaComments);
		receta.setListaQuestions(listaQuestions);
		receta.setListaPhotos(listaPhotos);
		receta.setListaIngredientes(listaIngredientes); 
	}

	@AfterEach
	void tearDown() throws Exception {
		 us0 = null;
		 us1 = null;
		 us2 = null;
		 us3 = null;
		 receta = null;
		 receta1= null;
		 comment = null;
		 question = null;
		
		 listaBloqueados = null;
		 listaFollows = null;
		 listaDeRecetas = null;	

	}

	@Test
	void test() {
		fail("Not yet implemented");
	}
	
	@Test
	void usuarioBloqueaUsuarioLoAnadeAListaDeUsuariosBloqueados () {
		us0.bloquear(us1);
		assertTrue(us0.listaBloqueados.contains(us1));
	}
	
	@Test
	void usuarioDesbloqueaUsuarioLoQuitaDeListaDeUsuariosBloqueados () {
		us0.desbloquear(us2);
		assertTrue(us0.listaBloqueados.contains(us2) == false);
	}
	
	@Test
	void usuarioFollowUsuarioLoAnadeListaDeUsuariosSeguidos () {
		us0.follow(us3);
		assertTrue(us0.listaFollows.contains(us3));
	}
	
	@Test
	void usuarioUnfollowUsuarioLoQuitaListaDeUsuariosSeguidos () {
		us0.unfollow(us1);
		assertTrue(us0.listaFollows.contains(us2) == false);
	}
	
	@Test
	void deletearMiPropiaCuentaDeUsuario() {

	}
	
	@Test
	void deletearMiPropiaRecetaYQuitarDeListaDeRecetas() {
		us0.deleteRecipe(receta);
		assertTrue(us0.listaDeRecetas.contains(receta) == false);	
	}
	
	@Test
	void usuarioCreaUnaRecetaYSeAnadeAListaDeRecetas() {
		us0.addRecipe(receta1);
		assertTrue(us0.listaDeRecetas.contains(receta1));
	}
	
	@Test
	void usuarioSeGastaLasChips() {
		us0.chips = 1000;
		double valor = us1.spendChips(500);
		assertEquals(valor, us1.chips - 500);
	}
	
	@Test
	void usuarioIntentaGastarMasChipsDeLasQueTiene() {
		double valor = us0.spendChips(500);
		assertEquals(valor, -1);
	}
	
	@Test
	void usuarioPreguntaUnaQuestionSobreUnaRecetaYSeAnadeAListaDePreguntasDeLaReceta() {
		us0.askQuestion(question,receta);
		assertTrue(receta.getListaQuestions().contains(question));
	}
	
	@Test
	void usuarioHaceComentarioSobreUnaRecetaYSeAnadeAListaDeComentariosDeLaReceta() {
		us0.hacerComentario(comment,receta);
		assertTrue(receta.getListaComments().contains(comment));
	}
	
	@Test
	void usuarioSubePhotoDeUnaRecetaYSeAnadeALaListaDePhotosDeLaReceta() {
		us0.subirPhotoDeReceta(receta,photo);
		assertTrue(receta.getListaPhotos().contains(photo));
	}
	
	@Test
	void usuarioEliminaPhotoDeUnaRecetaYSeQuitaDeLaListaDePhotosDeLaReceta() {
		us0.eliminarPhotoDeReceta(receta,photo);
		assertTrue(receta.getListaPhotos().contains(photo) == false);	
	}
	
	@Test
	void usuarioAgregaIngredienteAlaRecetaYSeAnadeALaListaDeIngredientes() {
		us0.addIngredient(ingrediente1,receta);
		assertTrue(receta.getListaIngredientes().contains(ingrediente1));
	}
	
	@Test
	void usuarioEliminaIngredienteDelaRecetaYSeEliminaDeLaListaDeIngredientes() {
		us0.deleteIngredient(ingrediente1,receta);
		assertTrue(receta.getListaIngredientes().contains(ingrediente1) == false);
	}
	
	
	
	
	
	
	
	
	
}