package CookItUpWeb.data.recipe;

import CookItUpWeb.auxiliary.ListAuxiliary;
import CookItUpWeb.data.recipe.comment.Comment;
import CookItUpWeb.data.recipe.comment.CommentRepository;
import CookItUpWeb.data.recipe.question.Question;
import CookItUpWeb.data.user.User;
import CookItUpWeb.data.user.UserRepository;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.net.www.protocol.http.HttpURLConnection;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpSession;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import java.net.URL;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Controller
@RequestMapping(path="recipe")
public class RecipeController {

    @Autowired private RecipeRepository recipeRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private CommentRepository commentRepository;

    @RequestMapping(path="all")
    public @ResponseBody List<Recipe> allRecipes() {
        return ListAuxiliary.fromIterableToList(recipeRepository.findAll());
    }

    @RequestMapping(path="{id}/get")
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

    @RequestMapping(path="{id}/comments")
    public @ResponseBody List<Comment> getComments(@PathVariable int id) {
        List<Comment> list = null;

        Optional<Recipe> optional = recipeRepository.findById(id);

        if (optional.isPresent()) {
            Recipe recipe = optional.get();
            //TODO
        }

        return list;
    }

    @RequestMapping(path="{id}/questions")
    public @ResponseBody List<Question> getQuestions(@PathVariable int id) {
        List<Question> list = null;

        Optional<Recipe> optional = recipeRepository.findById(id);

        if (optional.isPresent()) {
            Recipe recipe = optional.get();
            //TODO
        }

        return list;
    }

    @RequestMapping(path="{id}/add_comment")
    public String addComment(@PathVariable int id, @RequestParam String text, HttpSession session) {
        Optional<Recipe> optional = recipeRepository.findById(id);
        if (session.getAttribute("user") instanceof User) {
            User user = (User) session.getAttribute("user");
            if (optional.isPresent()) {
                Recipe recipe = optional.get();
                Comment comment = new Comment();
                comment.setAuthor(user);
                comment.setRecipe(recipe);
                comment.setText(text);
                commentRepository.save(comment);
            }
        }
        return "forward:/recipe/"+id+"/view.html";
    }

    @RequestMapping(path="{id}/add_question")
    public String addQuestion(@PathVariable int id, @RequestParam String text, HttpSession session) {
        Optional<Recipe> optional = recipeRepository.findById(id);
        if (session.getAttribute("user") instanceof User) {
            User user = (User) session.getAttribute("user");
            if (optional.isPresent()) {
                Recipe recipe = optional.get();
                Question question = new Question();
                question.setAuthor(user);
                question.setRecipe(recipe);
                question.setText(text);
                questionRepository.save(question);
            }
        }
        return "forward:/recipe/"+id+"/view.html";
    }

    //TODO check
    @RequestMapping(path="{name}/search")
    public @ResponseBody List<Recipe> searchByName (@PathVariable String name) {
        List<Recipe> list = new LinkedList<>();

        for (Recipe r : recipeRepository.findAll()){
            if(r.getName().toLowerCase(Locale.ROOT).contains(name.toLowerCase(Locale.ROOT))){

                list.add(r);
            }
        }
        return list;
    }

    //TODO check
    @RequestMapping(path= "{id}/author")
    public @ResponseBody User getAuthor(@PathVariable int id) {
        User user = null;

        Optional <Recipe> optional = recipeRepository.findById(id);

        if (optional.isPresent()) {
            Recipe recipe = optional.get();
            user = recipe.getAuthor();


        }
        return user;
    }

    @RequestMapping(path="{id}/delete")
    public String deleteRecipe(HttpSession session, @PathVariable int id) {
        if (session.getAttribute("user") instanceof User) {
            User user = (User) session.getAttribute("user");
            if (user.isBlocked()) {
                //TODO add an error message stating that the user is blocked and therefore cannot create a recipe
            }
            else {
                Optional<Recipe> optional = recipeRepository.findById(id);
                if (optional.isPresent()) {
                    Recipe recipe = optional.get();
                    if (recipe.getAuthor() == user) {
                        recipeRepository.delete(recipe);
                    } else {
                        // TODO show message recipe is not yours
                    }
                }
                else {
                    //TODO show message recipe not found
                }
            }
        }
        else {
            //TODO error you cannot create a recipe without logging in
        }
        return "forward:/home.html";
    }

    @RequestMapping(path="{id}/delete_admin")
    public String deleteRecipeAdmin(HttpSession session, @PathVariable int id) {
        if (session.getAttribute("admin") instanceof Administrator) {
            Administrator admin = (Administrator) session.getAttribute("admin");
            Optional<Recipe> optional = recipeRepository.findById(id);
            if (optional.isPresent()) {
                Recipe recipe = optional.get();
                recipeRepository.delete(recipe);
            } else {
                //TODO show message recipe not found
            }
        } else {
            //TODO error you cannot create a recipe without logging in
        }
        return "forward:/home.html";
    }
}
