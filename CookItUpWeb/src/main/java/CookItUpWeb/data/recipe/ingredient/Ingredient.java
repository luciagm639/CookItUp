package CookItUpWeb.data.recipe.ingredient;

import CookItUpWeb.data.user.User;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ingredient ingredient = (Ingredient) o;

        return Objects.equals(id, ingredient.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
