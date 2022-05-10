package User;

import Recipe.Recipe;

public class Question extends Message {
	
	public Question(RegisteredUser author, String text) {
		super(author, text);
	}
}
