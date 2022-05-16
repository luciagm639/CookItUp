package Administrator;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Recipe.Recipe;
import User.RegisteredUser;

class AdministratorTest {
	
	// User's Null
	private RegisteredUser us0 = null;
	private RegisteredUser us1 = null;
	
	private List<RegisteredUser> listaBloqueados = null;;
	private List<RegisteredUser> listaUsuarios = null;
	
	// Administrator's Null
	private Administrator adm = null;
	private Administrator adm2 = null;
	
	private List<Administrator> listaAdministradores = null;
	
	// Recipe's Null
	private Recipe rec1 = null;
	private Recipe rec2 = null;
	
	private List<Recipe> listaRecetas = null;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		
		// User's Creation
		us0 = new RegisteredUser("alberto", 1, "asdf");
		us1 = new RegisteredUser("juan",2, "asdf");
		
		// Administrator's Creation
		adm = new Administrator("alfonso", "asdf");
		adm2 = new Administrator("LuciaC", "asdf1");
		
		// Recipe's Creation
		rec1 = new Recipe("receta1", 1, us0);
		rec2 = new Recipe("receta2", 2, us1);
		
		// User's List Creation
		listaBloqueados = new ArrayList<>();
		listaUsuarios = new ArrayList<>();
		listaBloqueados.add(us1);
		listaUsuarios.add(us1);
		listaRecetas.add(rec1);
		
		// Administrator's List Creation
		listaAdministradores.add(adm);	
	}

	@AfterEach
	void tearDown() throws Exception {
		
		// User's Null
		us0 = null;
		us1 = null;	

		listaBloqueados = null;
		listaUsuarios = null;
		
		// Recipe's Null
		rec1 = null;
		rec2 = null;
		
		listaRecetas = null;
		
		// Administrator's Null
		adm = null;
	}
	
	@Test
	void alBloquearAUnUsuarioSeAnadeALaListaDeBloqueados() {
		adm.blockAccount(us0);
		assertTrue(listaBloqueados.contains(us0));	
	}
	
	@Test
	void alBloquearAUnUsuarioYaBloqueadoNoSeHaceNada() {
		List<RegisteredUser> aux = listaBloqueados;
		adm.blockAccount(us1);
		assertEquals(listaBloqueados, aux);	
	}
	
	@Test	
	void unUsuarioBloqueadoTieneElStatusBloqueado() {
		adm.blockAccount(us0);		
		assertEquals(us0.getStatus(), false);	
	}
	
	@Test
	void alDesbloquearUnUsuarioSeEliminaDeLaListaDeBloqueados() {
		adm.unblockAccount(us1);
		assertFalse(listaBloqueados.contains(us1));
		
	}
	
	@Test
	void alDesbloquearAUnUsuarioDesbloqueadoNoSeHaceNada() {
		List<RegisteredUser> aux = listaBloqueados;
		adm.unblockAccount(us0);
		assertEquals(listaBloqueados, aux);	
	}
	
	
	@Test
	void unUsuarioDesbloqueadoTieneElStatusDesbloqueado() {
		adm.unblockAccount(us1);
		assertEquals(us1.getStatus(), true);
		
	}
	
	@Test
	void alEliminarUnUsuarioEsteNoPerteneceALaListaDeUsuarios() {
		adm.deleteAnyAccount(us1);
		assertFalse(listaUsuarios.contains(us1));
	}
	
	@Test
	void alEliminarUnUsuarioNoExistenteNoSeHaceNada() {
		List<RegisteredUser> aux = listaUsuarios;
		adm.deleteAnyAccount(us0);
		assertEquals(listaUsuarios, aux);
	}
	
	@Test
	void alEliminarUnaRecetaEstaSeBorraDeLaListaDeRecetas() {
		adm.deleteRecipe(rec1);
		assertTrue(!listaRecetas.contains(rec1));
	}
	
	@Test
	void alEliminarUnaRecetaNoExistenteNoSeHaceNada() {
		List<Recipe> aux = listaRecetas;
		adm.deleteRecipe(rec2);
		assertEquals(listaRecetas, aux);	
	}
	
	@Test
	void AlEliminarUnAdministradorEsteSeBorraDeLaListaDeAdministradores() {
		adm.deleteAdminAccount(adm);		
		assertTrue(!listaAdministradores.contains(adm));
	}
	
	@Test
	void alEliminarUnAdminNoExistenteNoSeHaceNada() {
		List<Administrator> aux = listaAdministradores;
		adm.deleteAdminAccount(adm2);
		assertEquals(listaAdministradores, aux);	
	}
}
