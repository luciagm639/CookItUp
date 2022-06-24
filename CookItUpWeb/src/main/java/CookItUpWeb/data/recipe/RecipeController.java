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
import java.util.List;
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

    //TODO same as add comment
    @RequestMapping(path="{id}/add_question")
    public @ResponseBody boolean addQuestion(@PathVariable int id, Question question) {
        Optional<Recipe> optional = recipeRepository.findById(id);
        Boolean bool = null;
        if (optional.isPresent()) {
            Recipe recipe = optional.get();
        }
        return bool;
    }
}
