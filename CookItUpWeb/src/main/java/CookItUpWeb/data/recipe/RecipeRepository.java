package CookItUpWeb.data.recipe;

import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Integer> {

    static Recipe searchByName(RecipeRepository recipeRepository, String name) {
        Recipe recipe = null;
        for (Recipe recipe1 : recipeRepository.findAll()) {
            if (recipe1.getName().equalsIgnoreCase(name)) {
                recipe = recipe1;
                break;
            }
        }
        return recipe;
    }
}
