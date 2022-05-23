package recipe;

import system.data.Data;
import user.RegisteredUser;

public class Comment extends Data<Comment> {
	
	private RegisteredUser author;
	private String text;
	Recipe recipe;

	public Comment(RegisteredUser author, String text, Recipe recipe) {
		this.author = author;
		this.text = text;
		this.recipe = recipe;
	}
	
	public RegisteredUser getAuthor() {
		return author;
	}

	public String getText() {
		return text;
	}

	public void setAuthor(RegisteredUser defaultUser) {
		author = defaultUser;
	}
	
	@Override
	public boolean equals(Object o) {
		return (o instanceof Comment) &&
				(((Comment) o).getText().equalsIgnoreCase(getText())) &&
				(((Comment) o).getRecipe().equals(getRecipe()));
	}
	
	
	@Override
	public int hashCode() {
		return getText().hashCode() + getRecipe().hashCode();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getAuthor() + ": " + getText());
		return sb.toString();
	}

	public Recipe getRecipe() {
		return recipe;
	}
}
