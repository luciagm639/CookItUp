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
@RequestMapping(path="recipe")
public class ModifyRecipeController {

    @Autowired private RecipeRepository recipeRepository;
    @Autowired private IngredientRepository ingredientRepository;
    @Autowired private StepRepository stepRepository;

    @RequestMapping(path="{id}/add_ingredient")
    public @ResponseBody
    Ingredient addIngredient(HttpSession session, @PathVariable int id, @RequestParam String name) {
        Ingredient ingredient = null;
        if (session.getAttribute("user") instanceof User) {
            Optional<Recipe> optional = recipeRepository.findById(id);
            if (optional.isPresent()) {
                Recipe recipe = optional.get();
                if (((User) session.getAttribute("user")).equals(recipe.getAuthor())) {
                    ingredient = IngredientRepository.searchByName(ingredientRepository, name);
                    if (ingredient == null) {
                        ingredient = IngredientRepository.newIngredient(ingredientRepository, name);
                    }
                    recipe.addIngredient(ingredient);
                    recipeRepository.save(recipe);
                }
            }
        }
        return ingredient;
    }

    @RequestMapping(path="{id}/delete_ingredient")
    public @ResponseBody
    Recipe deleteIngredient(HttpSession session, @PathVariable int id, @RequestParam int idIng) {
        Recipe recipe = null;
        if (session.getAttribute("user") instanceof User) {
            Optional<Recipe> optional = recipeRepository.findById(id);
            if (optional.isPresent()) {
                recipe = optional.get();
                if (((User) session.getAttribute("user")).equals(recipe.getAuthor())) {
                    Optional<Ingredient> optional2 = ingredientRepository.findById(idIng);
                    if (optional2.isPresent()) {
                        Ingredient ingredient = optional2.get();
                        recipe.deleteIngredient(ingredient);
                        recipeRepository.save(recipe);
                    }
                }
            }
        }
        return recipe;
    }

    @RequestMapping(path="{id}/add_step")
    public @ResponseBody
    Step addStep(HttpSession session, @PathVariable int id,
                   @RequestParam String title,
                   @RequestParam int numberOfMinutes,
                   @RequestParam(required = false, defaultValue = "") String description) {
        Step step = null;
        if (session.getAttribute("user") instanceof User) {
            Optional<Recipe> optional = recipeRepository.findById(id);
            if (optional.isPresent()) {
                Recipe recipe = optional.get();
                if (((User) session.getAttribute("user")).equals(recipe.getAuthor())) {
                    step = new Step();
                    step.setTitle(title);
                    step.setNumberOfMinutes(numberOfMinutes);
                    step.setDescription(description);
                    recipe.addStep(step);
                    stepRepository.save(step);
                    recipeRepository.save(recipe);
                }
            }
        }
        return step;
    }

    @RequestMapping(path="{id}/delete_step")
    public @ResponseBody
    Recipe deleteStep(HttpSession session, @PathVariable int id, @RequestParam int idStep) {
        Recipe recipe = null;
        if (session.getAttribute("user") instanceof User) {
            Optional<Recipe> optional = recipeRepository.findById(id);
            if (optional.isPresent()) {
                recipe = optional.get();
                if (((User) session.getAttribute("user")).equals(recipe.getAuthor())) {
                    Optional<Step> optional2 = stepRepository.findById(idStep);
                    Step step = optional2.get();
                    recipe.deleteStep(step);
                    stepRepository.delete(step);
                    recipeRepository.save(recipe);
                }
            }
        }
        return recipe;
    }
}
