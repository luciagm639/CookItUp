package CookItUpWeb.data.recipe.ingredient;

import CookItUpWeb.auxiliary.ListAuxiliary;
import CookItUpWeb.data.recipe.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(path="ingredient")
public class IngredientController {

    @Autowired private RecipeRepository recipeRepository;
    @Autowired private IngredientRepository ingredientRepository;

    @RequestMapping(path="all")
    public @ResponseBody
    List<Ingredient> allIngredients() {
        return ListAuxiliary.fromIterableToList(ingredientRepository.findAll());
    }

}
