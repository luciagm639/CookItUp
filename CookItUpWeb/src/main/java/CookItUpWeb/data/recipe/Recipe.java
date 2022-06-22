package CookItUpWeb.data.recipe;

import CookItUpWeb.data.recipe.comment.Comment;
import CookItUpWeb.data.recipe.ingredient.Ingredient;
import CookItUpWeb.data.recipe.question.Question;
import CookItUpWeb.data.recipe.review.Review;
import CookItUpWeb.data.recipe.step.Step;
import CookItUpWeb.data.user.User;

import javax.persistence.*;
import java.util.List;

@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private int priority;

    @ManyToOne(optional = false)
    private User author;

    @ManyToMany()
    private List<Ingredient> ingredients;

    //TODO add a field to order the steps whithin a recipe
    @ManyToMany()
    private List<Step> steps;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Question> questions;
/*
    @OneToMany(cascade = CascadeType.ALL)
    private List<Review> reviews;
*/
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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User user) {
        this.author = user;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public boolean addIngredient(Ingredient ingredient) {
        boolean added = false;
        if (!ingredients.contains(ingredient)) {
            added = ingredients.add(ingredient);
        }
        return added;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public boolean addComment(Comment comment) {
        boolean added = false;
        if (!comments.contains(comment)) {
            added = comments.add(comment);
        }
        return added;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public boolean addQuestion(Question question) {
        boolean added = false;
        if (!questions.contains(question)) {
            added = questions.add(question);
        }
        return added;
    }
/*
    public List<Review> getReview() {
        return reviews;
    }

    public boolean addReview(Review review) {
        boolean added = false;
        if (!reviews.contains(review)) {
            added = reviews.add(review);
        }
        return added;
    }
*/
}
