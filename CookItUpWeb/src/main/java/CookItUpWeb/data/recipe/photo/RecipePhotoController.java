package CookItUpWeb.data.recipe.photo;

import CookItUpWeb.auxiliary.ListAuxiliary;
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
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin
public class RecipePhotoController {

    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private RecipePhotoRepository recipePhotoRepository;

    private static final String TARGET = "target\\classes\\static\\";


    @RequestMapping("/recipe/{id}/upload_recipe_photo")
    public @ResponseBody
    String uploadRecipePhoto(HttpSession session, @PathVariable int id, @RequestParam("image") MultipartFile file) {
        String response = "";
        Optional<Recipe> optional = recipeRepository.findById(id);
        if (optional.isPresent()) {
            if (session.getAttribute("user") instanceof User) {
                User user = (User) session.getAttribute("user");
                RecipePhoto recipePhoto = null;
                for (RecipePhoto recipePhoto1 : recipePhotoRepository.findAll()) {
                    if (recipePhoto1.getAuthor().getId() == user.getId()
                        && recipePhoto1.getRecipe().getId() == id) {
                        recipePhoto = recipePhoto1;
                        break;
                    }
                }
                if (recipePhoto != null) {
                    response = "You have replaced the photo";
                }
                else {
                    recipePhoto = new RecipePhoto();
                    recipePhoto.setAuthor(user);
                    recipePhoto.setRecipe(recipe);
                    recipePhoto.setType(getImageType(file.getContentType()));
                    recipePhotoRepository.save(recipePhoto);
                }
                try {
                    byte[] bytes = file.getBytes();
                    OutputStream outputStream = new FileOutputStream(TARGET + recipePhoto.getURL());
                    outputStream.write(bytes);
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

    @RequestMapping("/recipe/{id}/all_recipe_photos")
    public @ResponseBody
    List<RecipePhoto> allRecipePhotos(@PathVariable int id) {
        List<RecipePhoto> recipePhotos = new LinkedList<>();
        for (RecipePhoto recipePhoto : recipePhotoRepository.findAll()) {
            if (recipePhoto.getRecipe().getId() == id) {
                recipePhotos.add(recipePhoto);
            }
        }
        return recipePhotos;
    }

    @RequestMapping("/recipe/{id}/have_recipe_photo")
    public @ResponseBody
    boolean haveRecipePhoto(HttpSession session, @PathVariable int id) {
        boolean response = false;
        if (session.getAttribute("user") instanceof User) {
            User user = (User) session.getAttribute("user");
            for (RecipePhoto recipePhoto : recipePhotoRepository.findAll()) {
                if (recipePhoto.getRecipe().getId() == id) {
                    if (recipePhoto.getAuthor().getId() == user.getId()) {
                        response = true;
                        break;
                    }
                }
            }
        }
        return response;
    }
}
