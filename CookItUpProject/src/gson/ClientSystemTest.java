package gson;


import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import administrator.Administrator;
import administrator.AdministratorInterface;
import recipe.Recipe;
import system.MySystem;
import system.RecipeExtended;
import system.RecipePrinting;
import system.RegisteredUserExtended;
import user.RegisteredUser;
import user.RegisteredUserInterface;
import user.UserInterface;

public class ClientSystemTest {
	
	MySystem system;
	ServerSystem server;
	UserInterface userInterface;
	RegisteredUserInterface regInterface;
	AdministratorInterface admInterface;
	ClientSystem client;
	

	String name = "Juana";
	String password = "jPassword";
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {

	}

	@BeforeEach
	void setUp() throws Exception {
		system = new MySystem();
		server = new ServerSystem(system);
		new Thread(server).start();
		userInterface = new UserInterface();
		regInterface = userInterface.logIn("Inbal", "password");
		admInterface = userInterface.logInAdmin("Administrator", "AdminPassword");
		client = new ClientSystem();
	}
	
	@AfterEach
	void tearDown() throws Exception {
		server.exit();
		client.endConnection();
		userInterface.endConnection();
		userInterface = null;
		regInterface = null;
		admInterface = null;
		client = null;
		server = null;
	}
	
	@Test
	void createNewUser() {
		userInterface.registerNewAccount(name, password);
		RegisteredUser user = system.findUser(name);
		
		assertTrue(user != null && name.equals(user.getName()) && password.equals(user.getPassword()));
	}
	
	@Test
	void emptyTheSystem() {
		client.empty();
		
		assertTrue(system.getAllRecipes().isEmpty());
	}
	
	@Test
	void getAdministrators() {
		List<Administrator> list = client.getAdminList();
		Set<Administrator> set = system.getAdminList();

		assertTrue(set.containsAll(list) && list.containsAll(set));
	}
	
	@Test
	void showUserRecipes() {
		RegisteredUserExtended ue = system.getUser(3);
		RegisteredUser u = ue.unextend();
		String showC = client.showUserRecipes(u);
		String showS = system.showRecipes(ue.getRecipesList());
		
		assertEquals(showC, showS);
	}
	
	@Test
	void getUserRecipes() {
		RegisteredUserExtended ue = system.getUser(3);
		RegisteredUser u = ue.unextend();
		List<RecipePrinting> listC = client.getUserRecipes(u);
		List<RecipeExtended> listS = ue.getRecipesList();

		assertTrue(listC.containsAll(listS) && listS.containsAll(listC));
	}
}
