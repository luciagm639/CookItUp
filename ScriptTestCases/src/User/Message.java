package User;

public class Message {
	
	RegisteredUser author;
	String text;
	
	public Message(RegisteredUser author, String text) {
		this.author = author;
		this.text = text;
	}
}
