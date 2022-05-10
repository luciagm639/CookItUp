package Recipe;

import java.awt.Image;

import User.RegisteredUser;

public class RecipePhoto {
	
	RegisteredUser author;
	Image photo;
	int id;
	
	public RecipePhoto(Image image, RegisteredUser author, int id) {
		this.author = author;
		this.photo = image;
		this.id = id;
	}
}
