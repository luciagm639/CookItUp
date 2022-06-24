package CookItUpWeb.data.recipe;

import CookItUpWeb.auxiliary.ListAuxiliary;
import CookItUpWeb.data.user.User;
import CookItUpWeb.data.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path="recipe")
public class RecipeController {

    @Autowired private RecipeRepository recipeRepository;
    @Autowired private UserRepository userRepository;

    @RequestMapping(path="all")
    public @ResponseBody List<Recipe> allRecipes() {
        return ListAuxiliary.fromIterableToList(recipeRepository.findAll());
    }

    @RequestMapping(path="{id}")
    public @ResponseBody Recipe getRecipe(@PathVariable int id) {
        Recipe recipe = null;
        Optional<Recipe> optional = recipeRepository.findById(id);
        if (optional.isPresent()) {
            recipe = optional.get();
        }
        return recipe;
    }

    @RequestMapping(path="create")
    public @ResponseBody Recipe addRecipe(HttpSession session, @RequestParam String name) {
        Recipe recipe = null;
        if (session.getAttribute("user") instanceof User) {
            User user = (User) session.getAttribute("user");
            if (user.isBlocked()) {
                //TODO add an error message stating that the user is blocked and therefore cannot create a recipe
            }
            else {
                if (RecipeRepository.searchByName(recipeRepository, name) != null) {
                    //TODO add an error message, this recipe name is already taken
                }
                else {
                    recipe = new Recipe();
                    recipe.setName(name);
                    recipe.setPriority(0);
                    recipe.setAuthor(user);
                    recipeRepository.save(recipe);
                }
            }
        }
        else {
            //TODO error you cannot create a recipe without logging in
        }
        return recipe;
    }
}
