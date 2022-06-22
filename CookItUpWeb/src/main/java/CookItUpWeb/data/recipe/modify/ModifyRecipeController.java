package CookItUpWeb.data.recipe.modify;


import CookItUpWeb.data.recipe.Recipe;
import CookItUpWeb.data.recipe.RecipeRepository;
import CookItUpWeb.data.recipe.ingredient.Ingredient;
import CookItUpWeb.data.recipe.ingredient.IngredientRepository;
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

    @RequestMapping(path="{id}/add_ingredient")
    public @ResponseBody
    Recipe addIngredient(HttpSession session, @PathVariable int id, @RequestParam String name) {
        Recipe recipe = null;
        if (session.getAttribute("user") instanceof User) {
            /**/System.out.println("There is a user in the session");
            /**/System.out.println(recipeRepository.findById(id).isPresent());
            recipe = recipeRepository.findById(id).get();
            if (recipe != null) {
                /**/System.out.println("hay una receta con el id indicado");
                if (((User) session.getAttribute("user")).getId() == recipe.getAuthor().getId()) {
                    /**/System.out.println("tu receta");
                    Ingredient ingredient = IngredientRepository.searchByName(ingredientRepository, name);
                    if (ingredient == null) {
                        /**/System.out.println("ingredient not found");
                        ingredient = IngredientRepository.newIngredient(ingredientRepository, name);
                    }
                    else {
                        /**/System.out.println("ingredient found");
                    }
                    recipe.addIngredient(ingredient);
                }
            }
            else {
                /**/System.out.println("Recipe not found");
            }
        }
        return recipe;
    }
}
