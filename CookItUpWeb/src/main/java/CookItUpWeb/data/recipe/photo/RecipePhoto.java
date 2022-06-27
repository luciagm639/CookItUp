package CookItUpWeb.data.recipe.photo;

import CookItUpWeb.data.recipe.Recipe;
import CookItUpWeb.data.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
public class RecipePhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String type;

    @ManyToOne(optional = false)
    private User author;

    @ManyToOne(optional = false)
    private Recipe recipe;

    public String getURL() {
        return "/recipe/" + recipe.getId() +
                "/recipe_photos/user" + author.getId() +
                "." + type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
