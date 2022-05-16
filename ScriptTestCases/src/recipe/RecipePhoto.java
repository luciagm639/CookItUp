package recipe;

import java.awt.Image;

import user.RegisteredUser;

public class RecipePhoto {
	
	private RegisteredUser author;
	private Image photo;
	private int id;
  
	public RecipePhoto(Image image, RegisteredUser author, int id) {
		this.author = author;
		this.photo = image;
		this.id = id;
	}
  
	public RecipePhoto(Image image, RegisteredUser author) {
		this.author = author;
		this.photo = image;
		}
}
