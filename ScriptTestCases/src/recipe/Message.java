package recipe;

import user.RegisteredUser;

public class Message {
	
	private RegisteredUser author;
	private String text;
	
	public Message(RegisteredUser author, String text) {
		this.author = author;
		this.text = text;
	}

	public RegisteredUser getAuthor() {
		return author;
	}

	public String getText() {
		return text;
	}
}
