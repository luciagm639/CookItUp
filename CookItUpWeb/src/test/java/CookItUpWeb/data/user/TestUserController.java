package CookItUpWeb.data.user;

import CookItUpWeb.data.recipe.Recipe;
import CookItUpWeb.data.recipe.RecipeController;
import CookItUpWeb.data.recipe.RecipeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.mock.web.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.setup.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class TestUserController {

    private MockMvc mvc;

    @Mock
    private UserRepository userRepository;
    @Mock
    private RecipeRepository recipeRepository;

    @InjectMocks
    private UserController userController;

    private JacksonTester<User> jsonUser;

    @BeforeEach
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());

        mvc = MockMvcBuilders.standaloneSetup(userController)
                .build();
    }

    @Test
    public void Given_UserIsTheCurrentUser_Then_currentUser_ShouldReturn_TheCurrentUser() {
        MockHttpSession session = new MockHttpSession();
        User user = new User();
        String name = "pepe";
        String password = "password";
        user.setId(1); user.setName(name); user.setPassword(password);
        session.setAttribute("user", user);

        assertEquals(userController.currentUser(session), user);
    }

    @Test
    public void Given_UserIsNull_Then_currentUser_ShouldReturn_Null() {
        MockHttpSession session = new MockHttpSession();

        assertNull(userController.currentUser(session));
    }

    @Test
    public void Given_IdIsValid_Then_getUser_ShouldReturn_TheUserWithThatId() {
        User user = new User();
        String name = "pepe";
        String password = "password";
        user.setId(1); user.setName(name); user.setPassword(password);

        given(userRepository.findById(1))
                .willReturn(Optional.of(user));

        assertEquals(userController.getUser(1), user);
    }

    @Test
    public void Given_IdIsNotValid_Then_getUser_ShouldReturn_Null() {
        given(userRepository.findById(1))
                .willReturn(Optional.empty());

        assertNull(userController.getUser(1));
    }

    @Test
    public void Given_UserHasRecipes_Then_userRecipes_ShouldReturn_ThoseRecipes() {
        User user = new User();
        String name = "pepe";
        String password = "password";
        user.setId(1); user.setName(name); user.setPassword(password);

        given(userRepository.findById(1))
                .willReturn(Optional.of(user));

        SortedSet<Recipe> PepeRecipes = new TreeSet<>();

        Recipe recipe = new Recipe();
        recipe.setId(1); recipe.setName("Salad"); recipe.setAuthor(user);
        PepeRecipes.add(recipe);

        recipe = new Recipe();
        recipe.setId(2); recipe.setName("Pizza"); recipe.setAuthor(user);
        PepeRecipes.add(recipe);

        User user1 = new User();
        user1.setId(2); user1.setName("Marta"); user1.setPassword("password");
        recipe = new Recipe();
        recipe.setId(3); recipe.setName("Paella"); recipe.setAuthor(user1);

        List<Recipe> recipes = new LinkedList<>(PepeRecipes);
        recipes.add(recipe);

        given(recipeRepository.findAll())
                .willReturn(recipes);

        assertEquals(userController.userRecipes(1), PepeRecipes);
    }

    @Test
    public void Given_UserDoesNotHaveAnyRecipe_Then_userRecipes_ShouldReturn_EmptySet() {
        User user = new User();
        String name = "pepe";
        String password = "password";
        user.setId(1); user.setName(name); user.setPassword(password);

        given(userRepository.findById(1))
                .willReturn(Optional.of(user));

        User user1 = new User();
        user1.setId(2); user1.setName("Marta"); user1.setPassword("password");
        List<Recipe> recipes = new LinkedList<>();

        Recipe recipe = new Recipe();
        recipe.setId(1); recipe.setName("Salad"); recipe.setAuthor(user1);
        recipes.add(recipe);

        recipe = new Recipe();
        recipe.setId(2); recipe.setName("Pizza"); recipe.setAuthor(user1);
        recipes.add(recipe);

        given(recipeRepository.findAll())
                .willReturn(recipes);

        assertEquals(userController.userRecipes(1), new TreeSet<>());
    }

    @Test
    public void Given_UserIdIsNotValid_Then_userRecipes_ShouldReturn_EmptySet() {
        User user = new User();
        String name = "pepe";
        String password = "password";
        user.setId(1); user.setName(name); user.setPassword(password);

        given(userRepository.findById(1))
                .willReturn(Optional.of(user));

        SortedSet<Recipe> PepeRecipes = new TreeSet<>();

        Recipe recipe = new Recipe();
        recipe.setId(1); recipe.setName("Salad"); recipe.setAuthor(user);
        PepeRecipes.add(recipe);

        recipe = new Recipe();
        recipe.setId(2); recipe.setName("Pizza"); recipe.setAuthor(user);
        PepeRecipes.add(recipe);

        User user1 = new User();
        user1.setId(2); user1.setName("Marta"); user1.setPassword("password");
        recipe = new Recipe();
        recipe.setId(3); recipe.setName("Paella"); recipe.setAuthor(user1);

        List<Recipe> recipes = new LinkedList<>(PepeRecipes);
        recipes.add(recipe);

        given(recipeRepository.findAll())
                .willReturn(recipes);

        assertEquals(userController.userRecipes(1), PepeRecipes);
    }
}
