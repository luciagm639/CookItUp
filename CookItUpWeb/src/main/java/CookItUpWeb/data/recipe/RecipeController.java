package CookItUpWeb.data.recipe;

import CookItUpWeb.auxiliary.CopyFolder;
import CookItUpWeb.auxiliary.ListAuxiliary;
import CookItUpWeb.data.administrator.Administrator;
import CookItUpWeb.data.recipe.comment.Comment;
import CookItUpWeb.data.recipe.comment.CommentRepository;
import CookItUpWeb.data.recipe.question.Question;
import CookItUpWeb.data.recipe.question.QuestionRepository;
import CookItUpWeb.data.recipe.review.Review;
import CookItUpWeb.data.recipe.review.ReviewRepository;
import CookItUpWeb.data.user.User;
import CookItUpWeb.data.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping(path="recipe")
public class RecipeController {

    @Autowired private RecipeRepository recipeRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private CommentRepository commentRepository;
    @Autowired private QuestionRepository questionRepository;
    @Autowired private ReviewRepository reviewRepository;

    public static SortedSet<Recipe> fromIterableToSortedSet(Collection<Recipe> iter){
        SortedSet<Recipe> res = new TreeSet<>();
        for (Recipe r : iter) {
            res.add(r);
        }
        return res;
    }

    @RequestMapping(path="all")
    public @ResponseBody SortedSet<Recipe> allRecipes() {
        return fromIterableToSortedSet((Collection<Recipe>) recipeRepository.findAll());
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
                    try {
                        CopyFolder.copyFolder("recipe\\0", "recipe\\"+recipe.getId());
                    } catch (IOException e) {
                        e.printStackTrace();
                        recipeRepository.delete(recipe);
                        recipe.setId(-1);
                    }
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
        List<Comment> list = new ArrayList<>();
        Optional<Recipe> optional = recipeRepository.findById(id);
        if (optional.isPresent()) {
            Recipe recipe = optional.get();
            for (Comment comment : commentRepository.findAll()) {
                if (comment.getRecipe().getId() == recipe.getId()) {
                    list.add(comment);
                }
            }
        } else {
            //TODO show message recipe not found
        }

        return list;
    }

    @RequestMapping(path="{id}/questions")
    public @ResponseBody List<Question> getQuestions(@PathVariable int id) {
        List<Question> list = new ArrayList<>();
        Optional<Recipe> optional = recipeRepository.findById(id);
        if (optional.isPresent()) {
            Recipe recipe = optional.get();
            for (Question question : questionRepository.findAll()) {
                if (question.getRecipe().getId() == recipe.getId()) {
                    list.add(question);
                }
            }
        } else {
            //TODO show message recipe not found
        }

        return list;
    }

    @RequestMapping(path="{id}/add_comment")
    public @ResponseBody String addComment(@PathVariable int id, @RequestParam String text, HttpSession session) {
        String message = "";
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
            else {
                message = "The recipe was not found";
            }
        }
        else {
            message = "You have lo register to add a comment to a recipe";
        }
        return message;
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
        return "redirect:/recipe/"+id+"/view.html";
    }

    @RequestMapping(path="{name}/search")
    public @ResponseBody SortedSet<Recipe> searchByName (@PathVariable String name) {
        List<Recipe> list = new LinkedList<>();

        for (Recipe r : recipeRepository.findAll()){
            if(r.getName().toLowerCase(Locale.ROOT).contains(name.toLowerCase(Locale.ROOT))){

                list.add(r);
            }
        }
        return fromIterableToSortedSet((Collection<Recipe>) list);
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
        return "redirect:/home.html";
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
        return "redirect:/home.html";
    }

    @RequestMapping(path="{id}/num_likes")
    public @ResponseBody int getLikes(@PathVariable int id, HttpSession session) {
        Optional<Recipe> optional = recipeRepository.findById(id);
        int likes = 0;
        User user = (User) session.getAttribute("user");
        if (optional.isPresent()) {
            Recipe recipe = optional.get();
            for (Review review : reviewRepository.findAll()) {
                if (review.isLikeIt() == true) {
                    likes++;
                }
            }
        } else {
            //TODO show message recipe not found
            likes = -1;
        }

        return likes;
    }

    @RequestMapping(path="{id}/num_dislikes")
    public @ResponseBody int getDislikes(@PathVariable int id, HttpSession session) {
        Optional<Recipe> optional = recipeRepository.findById(id);
        int dislikes = 0;
        User user = (User) session.getAttribute("user");
        if (optional.isPresent()) {
            Recipe recipe = optional.get();
            for (Review review : reviewRepository.findAll()) {
                if (review.isLikeIt() == false) {
                    dislikes++;
                }
            }
        } else {
            //TODO show message recipe not found
            dislikes = -1;
        }

        return dislikes;
    }
}
