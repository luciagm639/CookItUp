package recipe;

import user.RegisteredUser;

public class Question extends Message {
	
	public Question(RegisteredUser author, String text) {
		super(author, text);
	}
	
	@Override
	public boolean equals(Object o) {
		return (o instanceof Question) && (((Question) o).getText().equalsIgnoreCase(getText()));
	}
	
	@Override
	public int hashCode() {
		return getText().hashCode();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getAuthor() + ": " + getText());
		return sb.toString();
	}
}
