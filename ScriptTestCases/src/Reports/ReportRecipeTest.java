package Reports;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Recipe.Recipe;
import User.RegisteredUser;

class ReportRecipeTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		RegisteredUser user = new RegisteredUser("Javi", 0, "password");
		Recipe recipe = new Recipe("Tortilla de Patatas");
	}

	@AfterEach
	void tearDown() throws Exception {
		
	}

	@Test
	void testMakeRecipeReport() {
		
	}
}
