package Recipe;

import java.awt.Image;
import java.util.Random;

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
  
	public RecipePhoto(Image image, RegisteredUser author) {
		this.author = author;
		this.photo = image;
		Random rand = new Random();
		id =  rand.nextInt();
		}
}
