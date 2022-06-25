package CookItUpWeb.data.user;

import CookItUpWeb.data.recipe.Recipe;
import CookItUpWeb.data.recipe.comment.Comment;
import CookItUpWeb.data.recipe.question.Question;
import CookItUpWeb.data.recipe.review.Review;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
public class User {

    public User() {
        id = -1;
        chips = 0;
        blocked = false;
        following = new LinkedList<>();
        blockedUsers = new LinkedList<>();
    }

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String password;
    private int chips;
    private boolean blocked;

    @JsonIgnoreProperties("{following, blockedUsers}")
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<User> following;

    @JsonIgnoreProperties("{following, blockedUsers}")
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<User> blockedUsers;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getChips() {
        return chips;
    }

    public void setChips(int chips) {
        this.chips = chips;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    @Override
    public String toString() {
        return name;
    }


    public boolean follow(User user) {
        return following.add(user);
    }

    /*
    public List<Recipe> getRecipes() {
        return recipes;
    }

    public boolean addRecipe(Recipe recipe) {
        return recipes.add(recipe);
    }

    public List<Comment> getComments() {
        return comments;
    }
    public boolean addComment(Comment comment) {
        return comments.add(comment);
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public boolean addQuestion(Question question) {
        return questions.add(question);
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public boolean addReview(Review review) {
        return reviews.add(review);
    }

    public List<User> getFollowing() {
        return following;
    }


    public List<User> getBlockedUsers() {
        return blockedUsers;
    }

    public boolean addBlockedUsers(User user) {
        return blockedUsers.add(user);
    }
*/
}
