package CookItUpWeb.data.recipe.photo;

import CookItUpWeb.data.recipe.Recipe;
import CookItUpWeb.data.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
}
