package CookItUpWeb.data.recipe.ingredient;

import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, Integer> {

    public static Ingredient searchByName(IngredientRepository ingredientRepository, String name) {
        Ingredient ingredient = null;
        for (Ingredient ingredient1 : ingredientRepository.findAll()) {
            if (ingredient1.getName().equalsIgnoreCase(name)) {
                ingredient = ingredient1;
                break;
            }
        }

        return ingredient;
    }

    public static Ingredient newIngredient(IngredientRepository ingredientRepository, String name) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(name);
        ingredientRepository.save(ingredient);
        return ingredient;
    }
}
