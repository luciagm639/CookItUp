package CookItUpWeb.data.recipe.modify;


import CookItUpWeb.data.recipe.Recipe;
import CookItUpWeb.data.recipe.RecipeRepository;
import CookItUpWeb.data.recipe.ingredient.Ingredient;
import CookItUpWeb.data.recipe.ingredient.IngredientRepository;
import CookItUpWeb.data.recipe.step.Step;
import CookItUpWeb.data.recipe.step.StepRepository;
import CookItUpWeb.data.user.User;
import CookItUpWeb.data.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping(path="recipe/modify")
public class ModifyRecipeController {

    @Autowired private RecipeRepository recipeRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private IngredientRepository ingredientRepository;
    @Autowired private StepRepository stepRepository;

    @RequestMapping(path="{id}/add_ingredient")
    public @ResponseBody
    Recipe addIngredient(HttpSession session, @PathVariable int id, @RequestParam String name) {
        Recipe recipe = null;
        if (session.getAttribute("user") instanceof User) {
            Optional<Recipe> optional = recipeRepository.findById(id);
            if (optional.isPresent()) {
                recipe = optional.get();
                if (((User) session.getAttribute("user")).getId() == recipe.getAuthor().getId()) {
                    Ingredient ingredient = IngredientRepository.searchByName(ingredientRepository, name);
                    if (ingredient == null) {
                        ingredient = IngredientRepository.newIngredient(ingredientRepository, name);
                    }
                    recipe.addIngredient(ingredient);
                    recipeRepository.save(recipe);
                }
            }
        }
        return recipe;
    }

    @RequestMapping(path="{id}/add_step")
    public @ResponseBody
    Recipe addStep(HttpSession session, @PathVariable int id,
                   @RequestParam String title,
                   @RequestParam int numberOfMinutes,
                   @RequestParam(required = false, defaultValue = "") String description) {
        Recipe recipe = null;
        if (session.getAttribute("user") instanceof User) {
            Optional<Recipe> optional = recipeRepository.findById(id);
            if (optional.isPresent()) {
                recipe = optional.get();
                if (((User) session.getAttribute("user")).getId() == recipe.getAuthor().getId()) {
                    Step step = new Step();
                    step.setTitle(title);
                    step.setNumberOfMinutes(numberOfMinutes);
                    step.setDescription(description);
                    recipe.addStep(step);
                    stepRepository.save(step);
                    recipeRepository.save(recipe);
                }
            }
        }
        return recipe;
    }
}
