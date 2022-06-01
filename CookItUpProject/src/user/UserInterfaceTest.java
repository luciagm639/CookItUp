package user;

import static org.junit.Assert.assertTrue;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import administrator.Administrator;
import administrator.AdministratorInterface;
import recipe.*;
import system.MySystem;

class UserInterfaceTest {

	//System Null
	MySystem system = null;
	
	// Users Null
	RegisteredUser regUser = null;
		
	UserInterface us0interface = null;
	
	// Register and login Null
	String name = null;
	String password = null;
	
	// Recipes Null
	Recipe recipe0 = null;
	
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
		regUser = new RegisteredUser("Alfonso", "password");
		system.addUser(regUser);
	
		us0interface = new UserInterface(system);	
		
		//Register and Login
		name = "Alfonso";
		password = "password";
		
		// Recipes
		recipe0 = new Recipe("Tortilla de papa", 1, regUser);
		
		regUser.addRecipe(recipe0);
		system.addRecipe(recipe0);
		
	}

	@AfterEach
	void tearDown() throws Exception {
		
		// System Null
		system = null;
		
		// Users Null
		regUser = null;
		
		us0interface = null;
		
		//Name and Password Null
		name = null;
		password = null;
		
		// Recipes Null
		recipe0 = null;
	}
	
	@Test
	void usuarioseregistra() {
		us0interface.registerNewAccount(name, password);
		assertTrue(us0interface.isReg(us0interface, name, password));
	}
	
	@Test
	void usuariologea() {
		us0interface.logIn(name, password);
		assertTrue(us0interface.isReg(us0interface, name, password)); // isLogIn
	}
	
	@Test
	void gettodaslasrecetas() {
		List<Recipe> recipeList = us0interface.getAllRecipes();
		assertFalse(recipeList.isEmpty());
	}
	
	@Test
	void showtodaslasrecetas() {
		String recEs = system.showRecipes(system.getAllRecipes());
		assertFalse(recEs == null);
	}
	
	@Test 
	void comprobarpassword() {
		assertTrue(us0interface.validatePassword(password));
	}
	
	@Test
	void adminSeRegistra() {
		AdministratorInterface adminterface = us0interface.registerNewAdminAccount(name, password);
		Administrator adm = system.findAdmin(name);
		assertTrue(adminterface.getAdministrator().equals(adm) &&
				adm.getName().equals(name) &&
				adm.getPasword().equals(password));
	}
	
	@Test
	void adminIntentaRegistarsePeroElNombreYaEsDeOtro() {
		//we create an admin
		us0interface.registerNewAdminAccount(name, password);
		
		AdministratorInterface adminterface = us0interface.registerNewAdminAccount(name, password);
		assertTrue(adminterface == null);
	}
	
	@Test
	void adminIntentaRegistrarsePeroClavePocoSegura() {
		AdministratorInterface adminterface = us0interface.registerNewAdminAccount(name, "");
		assertTrue(adminterface == null);
	}
	
	@Test
	void adminLogea() {
		//we create an admin
		us0interface.registerNewAdminAccount(name, password);
		
		AdministratorInterface adminterface = us0interface.logInAdmin(name, password);
		Administrator adm = adminterface.getAdministrator();
		assertTrue(system.findAdmin(name).equals(adm) &&
				adm.getName().equals(name) &&
				adm.getPasword().equals(password) &&
				adm.getId() == system.findAdmin(name).getId());
	}
	
	@Test
	void adminIntentaLogearPeroNoExisteLaCuenta() {
		AdministratorInterface adminterface = us0interface.logInAdmin(name, password);
		assertNull(adminterface);
	}
	
	@Test
	void adminIntentaLogearPeroClaveIncorrecta() {
		us0interface.registerNewAdminAccount(name, password);
		
		AdministratorInterface adminterface = us0interface.logInAdmin(name, "");
		assertTrue(adminterface == null);
		
	}
}
