package user;

import static org.junit.Assert.assertTrue;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import recipe.*;
import system.MySystem;

class UserInterfaceTest {

	//System Null
	MySystem system = null;
	
	// Users Null
	RegisteredUser us0 = null;
		
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
		us0 = new RegisteredUser("Alfonso", "password");
	
		us0interface = new UserInterface(system);	
		
		//Register and Login
		name = "Juan";
		password = "123456789";
		
		// Recipes
		recipe0 = new Recipe("Tortilla de papa", 1, us0);
		
		us0.addRecipe(recipe0);
		system.addRecipe(recipe0);
		
	}

	@AfterEach
	void tearDown() throws Exception {
		
		// System Null
		system = null;
		
		// Users Null
		us0 = null;
		
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
}
