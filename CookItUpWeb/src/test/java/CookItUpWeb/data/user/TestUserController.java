package CookItUpWeb.data.user;

import CookItUpWeb.data.recipe.Recipe;
import CookItUpWeb.data.recipe.RecipeRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Controller
public class TestUserController {

    private UserRepository userRepository;

    private UserController userController = new UserController();

    /**
     * Tests for the function userRecipes()
     */
    @Test
    public void t() {
        //TODO cannot get the data
        System.out.println("here");
        for(User user : userRepository.findAll()) {
            System.out.println("here");
        }
    }

    /**
     * Tests for the function ownRecipes()
     */
    @Test
    public void requestTheLinkToSeeAllMyRecipesWithAUserInTheSession() {
        HttpSession session = new MockHttpSession();
        User user = new User(); user.setId(1);
        session.setAttribute("user", user);
        String url = "\"/user/1/recipes\"";
        String urlFunction = userController.ownRecipes(session);
        assertTrue(url.equalsIgnoreCase(urlFunction));
    }

    @Test
    public void requestTheLinkToSeeAllMyRecipesWithoutAUserInTheSession() {
        HttpSession session = new MockHttpSession();
        String url = "\"\"";
        String url_function = userController.ownRecipes(session);
        assertTrue(url.equalsIgnoreCase(url_function));
    }


}
