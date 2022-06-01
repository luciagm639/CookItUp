package system.data;

import recipe.Ingredient;
import system.MySystem;
import user.RegisteredUser;

public class Fridge extends Table<RegisteredUser, Ingredient> {
	
	public Fridge() {
		super("Fridge.txt");
	}
	
	public Fridge(MySystem system) {
		this();
		getData(system);
	}

	@Override
	public void readData(MySystem system, String text) {
		MyStringSplitter sp = new MyStringSplitter(text);
		RegisteredUser user = system.getUser(sp.nextInt());
		Ingredient ingredient = system.getIngredient(sp.nextInt());
		
		user.addIngredientToFridge(ingredient);
	}

	@Override
	public void getData(MySystem system) {
		for (RegisteredUser u : system.getUserList()) {
			for (Ingredient i : u.getFridge()) {
				add(new Tuple<>(u, i));
			}
		}
	}

}
