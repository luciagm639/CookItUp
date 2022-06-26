package CookItUpWeb.data.user;

import CookItUpWeb.auxiliary.ListAuxiliary;
import CookItUpWeb.data.recipe.Recipe;
import CookItUpWeb.data.recipe.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path="/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RecipeRepository recipeRepository;

    @RequestMapping(path="current")
    public @ResponseBody User currentUser(HttpSession session) {
        return (User) session.getAttribute("user");
    }

    @RequestMapping(path="{id}/recipes")
    public @ResponseBody List<Recipe> userRecipes(@PathVariable int id) {
        List<Recipe> list = new LinkedList<>();
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            User user = optional.get();
            for (Recipe recipe : recipeRepository.findAll()) {
                if (recipe.getAuthor().getId() == id)
                    list.add(recipe);
            }
        }
        return list;
    }

    @RequestMapping(path="add")
    public @ResponseBody String add(@RequestParam String name, @RequestParam String password ) {
        for (User u : userRepository.findAll()) {
            if (u.getName().equalsIgnoreCase(name)) {
                return "Name is already in use";
            }
        }
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setBlocked(false);
        user.setChips(0);
        userRepository.save(user);
        return user + " has been added";
    }

    @RequestMapping(path="delete")
    public @ResponseBody String delete(@RequestParam String name, @RequestParam String password) {
        for (User u : userRepository.findAll()) {
            if (u.getName().equalsIgnoreCase(name)) {
                if (u.getPassword().equals(password)) {
                    userRepository.delete(u);
                    return u + " has been deleted";
                }
                else {
                    return "Wrong password";
                }
            }
        }
        return "User not found";
    }

    @RequestMapping(path="all", method = {RequestMethod.POST, RequestMethod.GET})
    public @ResponseBody List<User> allUsers() {
        return ListAuxiliary.fromIterableToList(userRepository.findAll());
    }

    @RequestMapping(path="own_recipes_link")
    public @ResponseBody String ownRecipesLink(HttpSession session) {
        String url = "";
        if (session.getAttribute("user") instanceof User) {
            User user = (User) session.getAttribute("user");
            url = "/user/"+user.getId()+"/recipes";
        }
        return url;
    }

}