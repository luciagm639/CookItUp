package gson;


import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import administrator.AdministratorInterface;
import user.RegisteredUser;
import user.RegisteredUserInterface;
import user.UserInterface;

public class Test {
	
	UserInterface userInterface;
	RegisteredUserInterface regInterface;
	AdministratorInterface admInterface;
	ClientSystem client;
	ServerSystem server;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {

	}

	@BeforeEach
	void setUp() throws Exception {
		server = new ServerSystem();
		new Thread(server).start();
		userInterface = new UserInterface();
		regInterface = userInterface.logIn("Inbal", "password");
		admInterface = userInterface.logInAdmin("Administrator", "AdminPassword");
		client = new ClientSystem();
	}
	
	@AfterEach
	void tearDown() throws Exception {
		userInterface = null;
		regInterface = null;
		admInterface = null;
		client = null;
		server.exit(); server = null;
	}
	
//	@Test
	void createNewUser() {
		String name = "Juana";
		String password = "jPassword";
		userInterface.registerNewAccount(name, password);
		RegisteredUser user = server.findUser(name);
		assertTrue(user != null && name.equals(user.getName()) && password.equals(user.getPassword()));
	}
	
	void emptyTheSystem() {
		client.empty();
		assertTrue(server.getAllRecipes().isEmpty());
	}
	
	public static void main(String[] args) throws Exception {
		Test t = new Test();
		t.setUp();
		t.emptyTheSystem();
		t.tearDown();
	}
}
