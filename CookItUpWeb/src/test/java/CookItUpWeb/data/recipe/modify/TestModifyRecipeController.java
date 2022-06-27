package CookItUpWeb.data.recipe.modify;

import CookItUpWeb.data.recipe.Recipe;
import CookItUpWeb.data.recipe.RecipeRepository;
import CookItUpWeb.data.recipe.ingredient.Ingredient;
import CookItUpWeb.data.recipe.ingredient.IngredientRepository;
import CookItUpWeb.data.recipe.step.Step;
import CookItUpWeb.data.recipe.step.StepRepository;
import CookItUpWeb.data.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Array;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class TestModifyRecipeController {

    private MockMvc mvc;

    @Mock
    private RecipeRepository recipeRepository;
    @Mock
    private IngredientRepository ingredientRepository;
    @Mock
    private StepRepository stepRepository;

    @InjectMocks
    private ModifyRecipeController modifyRecipeController;

    private JacksonTester<User> jsonUser;

    @BeforeEach
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());

        mvc = MockMvcBuilders.standaloneSetup(modifyRecipeController)
                .build();
    }

    @Test
    public void Given_UserIsNotInSession_Then_addIngredient_ShouldReturn_Null() {
        MockHttpSession session = new MockHttpSession();

        assertNull(modifyRecipeController.addIngredient(session,1,"potato"));
    }

    @Test
    public void Given_RecipeDoesNotExist_Then_addIngredient_ShouldReturn_Null() {
        MockHttpSession session = new MockHttpSession();
        User user = new User();
        String name = "pepe";
        String password = "password";
        user.setId(1); user.setName(name); user.setPassword(password);
        session.setAttribute("user", user);

        given(recipeRepository.findById(1))
                .willReturn(Optional.empty());

        assertNull(modifyRecipeController.addIngredient(session,1,"potato"));
    }

    @Test
    public void Given_UserInSessionIsNotAuthor_Then_addIngredient_ShouldReturn_Null() {
        MockHttpSession session = new MockHttpSession();
        User user = new User();
        String name = "pepe";
        String password = "password";
        user.setId(1); user.setName(name); user.setPassword(password);
        session.setAttribute("user", user);

        User author = new User();
        author.setName("marta"); author.setPassword("password"); author.setId(2);
        Recipe recipe = new Recipe();
        recipe.setAuthor(author); recipe.setId(1);

        given(recipeRepository.findById(1))
                .willReturn(Optional.of(recipe));

        assertNull(modifyRecipeController.addIngredient(session,1,"potato"));
    }

    @Test
    public void Given_IngredientNotFound_Then_addIngredient_ShouldReturn_NewIngredient() {
        MockHttpSession session = new MockHttpSession();
        User user = new User();
        String name = "pepe";
        String password = "password";
        user.setId(1); user.setName(name); user.setPassword(password);
        session.setAttribute("user", user);
        Recipe recipe = new Recipe();
        recipe.setAuthor(user); recipe.setId(1);

        given(recipeRepository.findById(1))
                .willReturn(Optional.of(recipe));

        assertNotNull(modifyRecipeController.addIngredient(session,1,"potato"));
    }

    @Test
    public void Given_IngredientInRepository_Then_addIngredient_ShouldReturn_ThatIngredient() {
        MockHttpSession session = new MockHttpSession();
        User user = new User();
        String name = "pepe";
        String password = "password";
        user.setId(1); user.setName(name); user.setPassword(password);
        session.setAttribute("user", user);
        Recipe recipe = new Recipe();
        recipe.setAuthor(user); recipe.setId(1);

        given(recipeRepository.findById(1))
                .willReturn(Optional.of(recipe));

        Ingredient ingredient = new Ingredient();
        ingredient.setName("potato");
        ingredient.setId(1);
        List<Ingredient> ingredients = new LinkedList<>();
        ingredients.add(ingredient);
        given(ingredientRepository.findAll())
                .willReturn(ingredients);

        assertEquals(modifyRecipeController.addIngredient(session,1,"potato"), ingredient);
    }

    @Test
    public void Given_UserIsNotInSession_Then_deleteIngredient_ShouldReturn_Null() {
        MockHttpSession session = new MockHttpSession();

        assertNull(modifyRecipeController.deleteIngredient(session,1,1));
    }

    @Test
    public void Given_RecipeDoesNotExist_Then_deleteIngredient_ShouldReturn_Null() {
        MockHttpSession session = new MockHttpSession();
        User user = new User();
        String name = "pepe";
        String password = "password";
        user.setId(1); user.setName(name); user.setPassword(password);
        session.setAttribute("user", user);

        given(recipeRepository.findById(1))
                .willReturn(Optional.empty());

        assertNull(modifyRecipeController.deleteIngredient(session,1,1));
    }

    @Test
    public void Given_UserInSessionIsNotAuthor_Then_addIngredient_ShouldReturn_RecipeUnchanged() {
        MockHttpSession session = new MockHttpSession();
        User user = new User();
        String name = "pepe";
        String password = "password";
        user.setId(1); user.setName(name); user.setPassword(password);
        session.setAttribute("user", user);

        User author = new User();
        author.setName("marta"); author.setPassword("password"); author.setId(2);
        Recipe recipe = new Recipe();
        recipe.setAuthor(author); recipe.setId(1);

        given(recipeRepository.findById(1))
                .willReturn(Optional.of(recipe));

        assertEquals(recipe, modifyRecipeController.deleteIngredient(session,1,1));
    }

    @Test
    public void Given_IngredientNotFound_Then_deleteIngredient_ShouldReturn_UnchangedRecipe() {
        MockHttpSession session = new MockHttpSession();
        User user = new User();
        String name = "pepe";
        String password = "password";
        user.setId(1); user.setName(name); user.setPassword(password);
        session.setAttribute("user", user);
        Recipe recipe = new Recipe();
        recipe.setAuthor(user); recipe.setId(1);

        given(recipeRepository.findById(1))
                .willReturn(Optional.of(recipe));

        assertEquals(recipe, modifyRecipeController.deleteIngredient(session,1,1));
    }

    @Test
    public void Given_IngredientFound_Then_deleteIngredient_ShouldReturn_RecipeWithoutIngredient() {
        MockHttpSession session = new MockHttpSession();
        User user = new User();
        String name = "pepe";
        String password = "password";
        user.setId(1); user.setName(name); user.setPassword(password);
        session.setAttribute("user", user);
        Recipe recipe = new Recipe();
        recipe.setAuthor(user); recipe.setId(1);

        Ingredient ingredient = new Ingredient();
        ingredient.setName("potato");
        ingredient.setId(1);
        List<Ingredient> ingredients = new LinkedList<>();
        ingredients.add(ingredient);
        given(ingredientRepository.findById(1))
                .willReturn(Optional.of(ingredient));
        recipe.addIngredient(ingredient);

        given(recipeRepository.findById(1))
                .willReturn(Optional.of(recipe));

        assertFalse(modifyRecipeController.deleteIngredient(session,1,1).getIngredients().contains(ingredient));
    }

    @Test
    public void Given_UserIsNotInSession_Then_addStep_ShouldReturn_Null() {
        MockHttpSession session = new MockHttpSession();

        assertNull(modifyRecipeController.addStep(session,1,"Cook the pasta", 10, ""));
    }

    @Test
    public void Given_RecipeDoesNotExist_Then_addStep_ShouldReturn_Null() {
        MockHttpSession session = new MockHttpSession();
        User user = new User();
        String name = "pepe";
        String password = "password";
        user.setId(1); user.setName(name); user.setPassword(password);
        session.setAttribute("user", user);

        given(recipeRepository.findById(1))
                .willReturn(Optional.empty());

        assertNull(modifyRecipeController.addStep(session,1,"Cook the pasta", 10, ""));
    }

    @Test
    public void Given_UserInSessionIsNotAuthor_Then_addStep_ShouldReturn_Null() {
        MockHttpSession session = new MockHttpSession();
        User user = new User();
        String name = "pepe";
        String password = "password";
        user.setId(1); user.setName(name); user.setPassword(password);
        session.setAttribute("user", user);

        User author = new User();
        author.setName("marta"); author.setPassword("password"); author.setId(2);
        Recipe recipe = new Recipe();
        recipe.setAuthor(author); recipe.setId(1);

        given(recipeRepository.findById(1))
                .willReturn(Optional.of(recipe));

        assertNull(modifyRecipeController.addStep(session,1,"Cook the pasta", 10, ""));
    }

    @Test
    public void Given_UserIsAuthor_Then_addStep_ShouldReturn_NewStep() {
        MockHttpSession session = new MockHttpSession();
        User user = new User();
        String name = "pepe";
        String password = "password";
        user.setId(1); user.setName(name); user.setPassword(password);
        session.setAttribute("user", user);
        Recipe recipe = new Recipe();
        recipe.setAuthor(user); recipe.setId(1);

        given(recipeRepository.findById(1))
                .willReturn(Optional.of(recipe));

        assertNotNull(modifyRecipeController.addStep(session,1,"Cook the pasta", 10, ""));
    }

    @Test
    public void Given_UserIsNotInSession_Then_deleteStep_ShouldReturn_Null() {
        MockHttpSession session = new MockHttpSession();

        assertNull(modifyRecipeController.deleteStep(session,1,1));
    }

    @Test
    public void Given_RecipeDoesNotExist_Then_deleteStep_ShouldReturn_Null() {
        MockHttpSession session = new MockHttpSession();
        User user = new User();
        String name = "pepe";
        String password = "password";
        user.setId(1); user.setName(name); user.setPassword(password);
        session.setAttribute("user", user);

        given(recipeRepository.findById(1))
                .willReturn(Optional.empty());

        assertNull(modifyRecipeController.deleteStep(session,1,1));
    }

    @Test
    public void Given_UserInSessionIsNotAuthor_Then_addStep_ShouldReturn_RecipeUnchanged() {
        MockHttpSession session = new MockHttpSession();
        User user = new User();
        String name = "pepe";
        String password = "password";
        user.setId(1); user.setName(name); user.setPassword(password);
        session.setAttribute("user", user);

        User author = new User();
        author.setName("marta"); author.setPassword("password"); author.setId(2);
        Recipe recipe = new Recipe();
        recipe.setAuthor(author); recipe.setId(1);

        given(recipeRepository.findById(1))
                .willReturn(Optional.of(recipe));

        assertEquals(recipe, modifyRecipeController.deleteStep(session,1,1));
    }

    @Test
    public void Given_UserIsAuthor_Then_deleteStep_ShouldReturn_RecipeWithoutStep() {
        MockHttpSession session = new MockHttpSession();
        User user = new User();
        String name = "pepe";
        String password = "password";
        user.setId(1); user.setName(name); user.setPassword(password);
        session.setAttribute("user", user);
        Recipe recipe = new Recipe();
        recipe.setAuthor(user); recipe.setId(1);

        Step step = new Step();
        step.setId(1);
        List<Step> steps = new LinkedList<>();
        steps.add(step);
        given(stepRepository.findById(1))
                .willReturn(Optional.of(step));
        recipe.addStep(step);

        given(recipeRepository.findById(1))
                .willReturn(Optional.of(recipe));

        assertFalse(modifyRecipeController.deleteStep(session,1,1).getSteps().contains(step));
    }

}
