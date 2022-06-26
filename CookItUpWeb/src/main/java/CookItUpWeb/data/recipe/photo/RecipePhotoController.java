package CookItUpWeb.data.recipe.photo;

import CookItUpWeb.data.recipe.Recipe;
import CookItUpWeb.data.recipe.RecipeRepository;
import CookItUpWeb.data.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@CrossOrigin
public class RecipePhotoController {

    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private RecipePhotoRepository recipePhotoRepository;


    @RequestMapping("/recipe/{id}/upload_recipe_photo")
    public @ResponseBody
    String uploadRecipePhoto(HttpSession session, @PathVariable int id, @RequestParam("image") MultipartFile file) {
        String response = "";
        Optional<Recipe> optional = recipeRepository.findById(id);
        if (optional.isPresent()) {
            if (session.getAttribute("user") instanceof User) {
                User user = (User) session.getAttribute("user");
            }
        }
        else {
            response = "This recipe doesn't exist";
        }


        return response;
    }
}
