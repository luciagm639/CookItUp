package Recipe;

import java.awt.Image;

import User.RegisteredUser;

public class RecipePhoto {
	
	RegisteredUser author;
	Image photo;
	
	public RecipePhoto(Image imagen, RegisteredUser author) {
		this.author = author;
		this.photo = imagen;
	}
}
