package CookItUpWeb.data.recipe;

import CookItUpWeb.data.recipe.comment.Comment;
import CookItUpWeb.data.recipe.comment.CommentRepository;
import CookItUpWeb.data.recipe.question.Question;
import CookItUpWeb.data.recipe.question.QuestionRepository;
import CookItUpWeb.data.recipe.review.Review;
import CookItUpWeb.data.recipe.review.ReviewRepository;
import CookItUpWeb.data.user.User;
import CookItUpWeb.data.user.UserController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class TestRecipeController {

    private MockMvc mvc;

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private RecipeController recipeController;

    private JacksonTester<User> jsonUser;

    @BeforeEach
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());

        mvc = MockMvcBuilders.standaloneSetup(recipeController)
                .build();
    }

    @Test
    public void Given_RecipeHasThreeComments_Then_getComments_ShouldReturn_AListWithTheComments(){

        Recipe recipe = new Recipe();
        recipe.setId(1);

        Comment comment1 = new Comment();
        Comment comment2 = new Comment();
        Comment comment3 = new Comment();

        comment1.setId(1);
        comment1.setRecipe(recipe);

        comment2.setId(2);
        comment2.setRecipe(recipe);

        comment3.setId(3);
        comment3.setRecipe(recipe);

        List<Comment> comments = new ArrayList<>();

        comments.add(comment1);
        comments.add(comment2);
        comments.add(comment3);

        given(commentRepository.findAll())
                .willReturn(comments);

        given(recipeRepository.findById(1))
                .willReturn(Optional.of(recipe));

        assertEquals(comments, recipeController.getComments(1));
    }

    @Test
    public void Given_RecipeHasThreeQuestions_Then_getQuestions_ShouldReturn_AListWithTheQuestions(){

        Recipe recipe = new Recipe();
        recipe.setId(1);

        Question question1 = new Question();
        Question question2 = new Question();
        Question question3 = new Question();

        question1.setId(1);
        question1.setRecipe(recipe);

        question2.setId(2);
        question2.setRecipe(recipe);

        question3.setId(3);
        question3.setRecipe(recipe);

        List<Question> questions = new ArrayList<>();

        questions.add(question1);
        questions.add(question2);
        questions.add(question3);

        given(questionRepository.findAll())
                .willReturn(questions);

        given(recipeRepository.findById(1))
                .willReturn(Optional.of(recipe));

        assertEquals(questions, recipeController.getQuestions(1));
    }

    @Test
    public void Given_RecipeIsNotFound_Then_addComent_ShouldReturn_AStringWithTheErrorMessage(){
        MockHttpSession session = new MockHttpSession();

        User user = new User();
        session.setAttribute("user", user);

        given(recipeRepository.findById(1))
                .willReturn(Optional.empty());

        String message = recipeController.addComment(1, "comment", session);
        assertEquals("The recipe was not found", message);
    }

    @Test
    public void Given_UserNotRegistered_Then_addComment_ShouldReturn_AStringWithTheErrorMessage(){
        MockHttpSession session = new MockHttpSession();

        given(recipeRepository.findById(1))
                .willReturn(Optional.empty());

        String message = recipeController.addComment(1, "comment", session);
        assertEquals("You have to register to add a comment to a recipe", message);
    }
    @Test
    public void Given_RecipeIsNotFound_Then_addQuestion_ShouldReturn_AStringWithTheErrorMessage(){
        MockHttpSession session = new MockHttpSession();

        User user = new User();
        session.setAttribute("user", user);

        given(recipeRepository.findById(1))
                .willReturn(Optional.empty());

        String message = recipeController.addQuestion(1, "comment", session);
        assertEquals("The recipe was not found", message);
    }

    @Test
    public void Given_UserNotRegistered_Then_addQuestion_ShouldReturn_AStringWithTheErrorMessage(){
        MockHttpSession session = new MockHttpSession();

        given(recipeRepository.findById(1))
                .willReturn(Optional.empty());

        String message = recipeController.addQuestion(1, "comment", session);
        assertEquals("You have to register to add a question to a recipe", message);
    }
    @Test
    public void Given_TheUserHasAReview_Then_getUserReview_ShouldReturn_TheReview(){
        MockHttpSession session = new MockHttpSession();

        User user = new User();

        Recipe recipe = new Recipe();
        recipe.setId(1);

        Review review = new Review();
        review.setRecipe(recipe);
        review.setAuthor(user);

        List<Review> reviews = new LinkedList<>();
        reviews.add(review);

        session.setAttribute("user", user);

        given(reviewRepository.findAll())
                .willReturn(reviews);

        Review result = recipeController.getUserReview(session, 1);

        assertEquals(review, result);
    }
}
