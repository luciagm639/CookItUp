package recipe;

import system.data.Data;
import user.RegisteredUser;

public class Review extends Data<Review> {
	
	private RegisteredUser author;
	private boolean like;
	private Recipe recipe;
	
	//TODO DELETE
	public Review(RegisteredUser author, boolean like) {
		this.author = author;
		this.like = like;	
	}
	
	public Review(RegisteredUser author, boolean like, Recipe recipe) {
		this.author = author;
		this.like = like;
		this.recipe = recipe;
	}

	public RegisteredUser getAuthor() {
		return author;
	}

	public boolean isLike() {
		return like;
	}

	public void setLike(boolean like) {
		this.like = like;
	}
	
	public Recipe getRecipe() {
		return recipe;
	}
	
	@Override
	public boolean equals(Object o) {
		return (o instanceof Review) &&
				(((Review) o).author.equals(author)) &&
				(((Review) o).recipe.equals(recipe));
	}
}
