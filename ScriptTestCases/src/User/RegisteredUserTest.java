package User;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Recipe.Recipe;

class RegisteredUserTest {

	RegisteredUser us0 = null;
	RegisteredUser us1 = null;
	RegisteredUser us2 = null;
	RegisteredUser us3 = null;
	Recipe receta = null;
	Recipe receta1 = null;
	
	List listaBloqueados = null;
	List listaFollows = null;
	List listaDeRecetas = null;	
	
	
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
		 receta = new Recipe("Tortilla de papa");
		 receta1 = new Recipe("Paella");
		
		 listaBloqueados = new ArrayList<String>();
		 listaFollows = new ArrayList<String>();
		 listaDeRecetas = new ArrayList<String>();	
		 
		 listaBloqueados.add(us2);
		 listaFollows.add(us1);
		 listaDeRecetas.add(receta);
		 
		 
		 us0.listaBloqueados = listaBloqueados;
		 us0.listaFollows = listaFollows;
		 us0.listaDeRecetas = listaDeRecetas;
		 
		 
	}

	@AfterEach
	void tearDown() throws Exception {
		 us0 = null;
		 us1 = null;
		 us2 = null;
		 us3 = null;
		 receta = null;
		
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
		RegisteredUser us1 = new RegisteredUser("Javi", 1, "password");
		double valor = us1.spendChips(500);
		assertEquals(valor, -1);
	}
}
