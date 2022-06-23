package CookItUpWeb.data.user;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpSession;

import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.*;

public class TestUserController {

    UserController userController = new UserController();

    /**
     * Tests for the function ownRecipes()
     */
    @Test
    public void requestTheLinkToSeeAllMyRecipesWithAUserInTheSession() {
        HttpSession session = new MockHttpSession();
        User user = new User(); user.setId(1);
        session.setAttribute("user", user);
        String url = "\"/user/1/recipes\"";
        String url_function = userController.ownRecipes(session);
        assertTrue(url.equalsIgnoreCase(url_function));
    }

    @Test
    public void requestTheLinkToSeeAllMyRecipesWithoutAUserInTheSession() {
        HttpSession session = new MockHttpSession();
        String url = "\"\"";
        String url_function = userController.ownRecipes(session);
        assertTrue(url.equalsIgnoreCase(url_function));
    }


}
