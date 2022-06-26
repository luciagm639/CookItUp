package CookItUpWeb.data.recipe.review;

import CookItUpWeb.data.recipe.Recipe;
import CookItUpWeb.data.user.User;

import javax.persistence.*;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private boolean likeIt;

    @ManyToOne(optional = false)
    private User author;

    @ManyToOne(optional = false)
    private Recipe recipe;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isLikeIt() {
        return likeIt;
    }

    public void setLikeIt(boolean like) {
        this.likeIt = like;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
