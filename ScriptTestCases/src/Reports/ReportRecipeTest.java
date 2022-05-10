package Reports;

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
import User.RegisteredUser;

class ReportRecipeTest {
	
	RegisteredUser us0 = null;
	RegisteredUser us1 = null;
	String justification = null;
	Recipe receta = null;
	Report report = null;
	Report report2 = null;
	
	List<Report> listaReports = null;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		listaReports = new ArrayList<Report>();
		us0 = new RegisteredUser("Alfonso", 0, "password");
		us1 = new RegisteredUser("Javi", 1, "password");
		justification = "text";
		receta = new Recipe("Tortilla de papa", 1, us0);
		report = new Report(us0, justification);
		report2 = new Report(us1, justification);
		
		receta.setListaReports(listaReports);
	}

	@AfterEach
	void tearDown() throws Exception {
		us0 = null;
		us1 = null;
		justification = null;
		receta = null;
		report = null;
		report2 = null;	
	}

	@Test
	void usuarioReportaRecipeYSeAgregaALaListaDeReportsDeEsaRecipe() {
		us0.reportRecipe(receta,report2);
		assertTrue(receta.getListaReports().contains(report2));
	}
}
