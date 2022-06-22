package CookItUpWeb.data.recipe;

import CookItUpWeb.auxiliary.ListAuxiliary;
import CookItUpWeb.data.user.User;
import CookItUpWeb.data.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(path="recipe")
public class RecipeController {

    @Autowired private RecipeRepository recipeRepository;
    @Autowired private UserRepository userRepository;

    @RequestMapping(path="all")
    public @ResponseBody List<Recipe> allRecipes() {
        return ListAuxiliary.fromIterableToList(recipeRepository.findAll());
    }

    @RequestMapping(path="add")
    public @ResponseBody Recipe addRecipe(HttpSession session, @RequestParam String name) {
        Recipe recipe = null;
        if (session.getAttribute("user") instanceof User) {
            //TODO look for recipe of the same name and if user is blocked...
            recipe = new Recipe();
            recipe.setName(name);
            recipe.setPriority(0);
            //recipe.setAuthor((User) session.getAttribute("user"));
            recipeRepository.save(recipe);
        }
        else {
            //TODO error?, ya veremos
        }
        return recipe;
    }
}
