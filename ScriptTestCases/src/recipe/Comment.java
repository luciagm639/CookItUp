package recipe;

import user.RegisteredUser;

public class Comment extends Message {

	public Comment(RegisteredUser author, String text) {
		super(author, text);
	}
	
	@Override
	public boolean equals(Object o) {
		return (o instanceof Comment) && (((Comment) o).getText().equalsIgnoreCase(getText()));
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
