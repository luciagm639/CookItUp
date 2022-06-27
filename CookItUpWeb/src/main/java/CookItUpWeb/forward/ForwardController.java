package CookItUpWeb.forward;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ForwardController {

    @RequestMapping("/home")
    public String home() {
        return "forward:/home.html";
    }

    @RequestMapping("/log_in")
    public String logIn() {
        return "forward:/log_in.html";
    }

    @RequestMapping("/sign_up")
    public String signUp() {
        return "forward:/sign_up.html";
    }

    @RequestMapping("/create_recipe")
    public String createRecipe() {
        return "forward:/recipe/create.html";
    }

    @RequestMapping("/search_recipes")
    public String searchRecipes() {
        return "forward:/recipe/search.html";
    }

    @RequestMapping("/recipe/{id}/modify")
    public String modifyRecipe(@PathVariable int id) {
        return "forward:/recipe/"+id+"/modify.html";
    }

    @RequestMapping("/recipe/{id}/view")
    public String viewRecipe(@PathVariable int id) {
        return "forward:/recipe/"+id+"/view.html";
    }

    @RequestMapping("/user/{id}/profile")
    public String viewProfile(@PathVariable int id) {
        return "forward:/user/"+id+"/profile.html";
    }

    @RequestMapping("/user/{id}/view_recipes")
    public String viewRecipes(@PathVariable int id) {
        return "forward:/user/"+id+"/recipes.html";
    }

    @RequestMapping("/user/{id}/fridge")
    public String viewFridge(@PathVariable int id) {
        return "forward:/user/"+id+"/fridge.html";
    }

}
