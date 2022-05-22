package recipe;

import java.awt.Image;

import system.data.Data;
import user.RegisteredUser;

public class RecipePhoto extends Data<RecipePhoto> {
	
	private RegisteredUser author;
	private Image image;
  
	public RecipePhoto(Image image, RegisteredUser author) {
		this.author = author;
		this.image = image;
	}
  
	public RegisteredUser getAuthor() {
		return author;
	}

	public void setAuthor(RegisteredUser author) {
		this.author = author;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image photo) {
		this.image = photo;
	}
}
