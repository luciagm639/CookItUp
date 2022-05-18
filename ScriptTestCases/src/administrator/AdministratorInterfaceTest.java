package administrator;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import recipe.Recipe;
import system.MySystem;
import system.RecipeList;
import system.RegisteredUserList;
import user.RegisteredUser;

class AdministratorInterfaceTest {
	
	//---------------------------------------------------------------------------------
	//NEW CODE (LUCIA G)
	
	//Initialize the system:
	private static MySystem system = new MySystem();
	
	//Initialize the Administrators:
	private static Administrator admin1 = null;
	private Administrator admin2 = null;
	
	//Initialize an Administrator that does not exist in the List:
	private Administrator admin3 = null;
	
	//Initialize the RegisteredUser:
	private RegisteredUser us1 = null;
	
	//Initialize a RegisteredUser who does not exist in the List:
	private RegisteredUser us2 = null;
	
	//Initialize the Interface of both admins:
	private static AdministratorInterface admin1Int = null;
	
	//Initialize the recipes:
	private Recipe rec1 = null;
	private Recipe rec2 = null;
	
	//Initialize a user who is already blocked:
	private RegisteredUser blockedUser = null;
	
	
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		admin1 = new Administrator("admin1", "passwordAdmin1");
		admin1Int = new AdministratorInterface (admin1);
		system.addAdmin(admin1);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		system.removeAdmin(admin1);
		admin1 = null;
		admin1Int = null;
	}

	@BeforeEach
	void setUp() throws Exception {
		us1 = new RegisteredUser ("us1", 0, "passwordUs1");
		us2 = new RegisteredUser ("us2", 0, "passwordUs2");
		system.addUser(us1);
		
		rec1 = new Recipe("rec1", 1, us1);
		rec2 = new Recipe("rec2", 1, us1);
		system.addRecipe(rec1);
		
		admin2 = new Administrator ("admin2", "passwordAdmin2");
		admin3 = new Administrator ("admin3", "passwordAdmin3");
		system.addAdmin(admin2);
		
		blockedUser = new RegisteredUser ("blockedUser", 0, "passwordBlockedUser");
		admin1Int.blockAccount(blockedUser);
	}

	@AfterEach
	void tearDown() throws Exception {
		admin1Int.unblockAccount(us1);
	}
	
	@Test
	void alBloquearAUnUsuarioSeAnadeALaListaDeBloqueados() {
		admin1Int.blockAccount(us1);
		assertTrue(system.getBlockedUserList().contains(us1));	
	}
	
	@Test
	void alBloquearAUnUsuarioYaBloqueadoNoSeHaceNada() {
		RegisteredUserList aux = system.getBlockedUserList();
		admin1Int.blockAccount(blockedUser);
		assertEquals(system.getBlockedUserList(), aux);
	}
	
	
	@Test	
	void unUsuarioBloqueadoTieneElStatusBloqueado() {
		admin1Int.blockAccount(us1);		
		assertEquals(us1.getStatus(), false);	
	}
	
	@Test
	void alDesbloquearUnUsuarioSeEliminaDeLaListaDeBloqueados() {
		admin1Int.unblockAccount(blockedUser);
		assertFalse(system.getBlockedUserList().contains(blockedUser));
		
	}
	
	@Test
	void alDesbloquearAUnUsuarioDesbloqueadoNoSeHaceNada() {
		RegisteredUserList aux = system.getBlockedUserList();
		admin1Int.unblockAccount(us1);
		assertEquals(system.getBlockedUserList(), aux);
	}
	
	
	@Test
	void unUsuarioDesbloqueadoTieneElStatusDesbloqueado() {
		admin1Int.unblockAccount(blockedUser);
		assertEquals(blockedUser.getStatus(), true);
	}
	
	@Test
	void alEliminarUnUsuarioEsteNoPerteneceALaListaDeUsuarios() {
		admin1Int.deleteAnyAccount(us1);
		assertFalse(system.getUserList().contains(us1));
	}
	
	@Test
	void alEliminarUnUsuarioNoExistenteNoSeHaceNada() {
		RegisteredUserList aux = system.getUserList();
		admin1Int.deleteAnyAccount(us2);
		assertEquals(system.getUserList(), aux);
	}
	
	@Test
	void alEliminarUnaRecetaEstaSeBorraDeLaListaDeRecetas() {
		admin1Int.deleteRecipe(rec1);
		assertTrue(!system.getRecipes().contains(rec1));
	}
	
	@Test
	void alEliminarUnaRecetaNoExistenteNoSeHaceNada() {
		RecipeList aux = system.getRecipes();
		admin1Int.deleteRecipe(rec2);
		assertEquals(system.getRecipes(), aux);
	}
	
	@Test
	void AlEliminarUnAdministradorEsteSeBorraDeLaListaDeAdministradores() {
		admin1Int.deleteAdminAccount(admin2);		
		assertTrue(!system.getAdminList().contains(admin2));
	}
	
	@Test
	void alEliminarUnAdminNoExistenteNoSeHaceNada() {
		Set<Administrator> aux = system.getAdminList();
		admin1Int.deleteAdminAccount(admin3);
		assertEquals(system.getAdminList(), aux);
	}
	
	//---------------------------------------------------------------------------------
}
