package recipe;

import system.data.Data;
import user.RegisteredUser;

public class Question extends Data<Question> {

	private RegisteredUser author;
	private String text;
	Recipe recipe;
	
	public Question(RegisteredUser author, String text, Recipe recipe) {
		this.author = author;
		this.text = text;
		this.recipe = recipe;
	}
	
	@Override
	public boolean equals(Object o) {
		return (o instanceof Question) && (((Question) o).getText().equalsIgnoreCase(getText())) && (((Question) o).getRecipe().equals(getRecipe()));
	}
	
	@Override
	public int hashCode() {
		return getText().hashCode();
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
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getAuthor() + ": " + getText());
		return sb.toString();
	}

	public Recipe getRecipe() {
		return recipe;
	}
}
