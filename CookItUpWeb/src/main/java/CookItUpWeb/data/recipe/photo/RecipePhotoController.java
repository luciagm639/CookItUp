package CookItUpWeb.data.recipe.photo;

import CookItUpWeb.data.recipe.Recipe;
import CookItUpWeb.data.recipe.RecipeRepository;
import CookItUpWeb.data.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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
            Recipe recipe = optional.get();
            if (session.getAttribute("user") instanceof User) {
                User user = (User) session.getAttribute("user");
                RecipePhoto recipePhoto = new RecipePhoto();
                recipePhoto.setAuthor(user);
                recipePhoto.setRecipe(recipe);
                recipePhoto.setType(getImageType(file.getContentType()));
                try {
                    byte[] bytes = file.getBytes();
                    OutputStream outputStream = new FileOutputStream(recipePhoto.getURL());
                    outputStream.write(bytes);
                    recipePhotoRepository.save(recipePhoto);
                } catch (IOException e) {
                    response = "There was an error uploading the image";
                }
            }
            else {
                response = "You can't upload a recipe photo if you are not registered";
            }
        }
        else {
            response = "This recipe doesn't exist";
        }


        return response;
    }

    private String getImageType(String originalType) {
        String[] split = originalType.split("/");
        return split[1];
    }
}
