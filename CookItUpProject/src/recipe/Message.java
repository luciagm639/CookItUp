package recipe;

import system.data.Data;
import user.RegisteredUser;

public abstract class Message<E extends Message<E>> extends Data<E>{
	
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

	public void setAuthor(RegisteredUser defaultUser) {
		author = defaultUser;
	}
}
