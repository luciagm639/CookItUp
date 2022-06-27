package CookItUpWeb.data.recipe;

import CookItUpWeb.data.recipe.comment.Comment;
import CookItUpWeb.data.recipe.ingredient.Ingredient;
import CookItUpWeb.data.recipe.question.Question;
import CookItUpWeb.data.recipe.review.Review;
import CookItUpWeb.data.recipe.step.Step;
import CookItUpWeb.data.user.User;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Entity
public class Recipe implements Comparable<Recipe> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private int priority;

    @ManyToOne(optional = false)
    private User author;

    @ManyToMany()
    private List<Ingredient> ingredients;

    @OneToMany()
    private List<Step> steps;

    public Recipe() {
        name = "";
        priority = 0;
        author = null;
        ingredients = new LinkedList<>();
    }

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

    public boolean deleteIngredient(Ingredient ingredient) {
        boolean removed = false;
        if (ingredients.contains(ingredient)) {
            removed = ingredients.remove(ingredient);
        }
        return removed;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public boolean addStep(Step step) {
        boolean added = false;
        if (!steps.contains(step)) {
            added = steps.add(step);
        }
        return added;
    }

    public boolean deleteStep(Step step) {
        boolean removed = false;
        if (steps.contains(step)) {
            removed = steps.remove(step);
        }
        return removed;
    }

    @Override
    public int compareTo(Recipe o) {
        // first the ones with the highest priority
        int res = o.getPriority() - priority;
        if (res == 0){
            // then the most recent ones first
            res = o.getId() - id;
        }
        return res;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Recipe recipe = (Recipe) o;

        return Objects.equals(id, recipe.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
