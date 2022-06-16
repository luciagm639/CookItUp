package gson;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import administrator.AdministratorInterface;
import user.RegisteredUserInterface;
import user.UserInterface;

public class Test {
	
	UserInterface userInterface;
	RegisteredUserInterface regInterface;
	AdministratorInterface admInterface;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {

	}

	@BeforeEach
	void setUp() throws Exception {
		userInterface = new UserInterface();
		regInterface = userInterface.logIn("Inbal", "password");
		admInterface = userInterface.logInAdmin("Administrator", "AdminPassword");
	}
	
	@AfterEach
	void tearDown() throws Exception {
		userInterface = null;
		regInterface = null;
		admInterface = null;
	
	}
	
}
