package recipe;

import user.RegisteredUser;

public class Review {
	
	private RegisteredUser author;
	private boolean like;
	
	public Review(RegisteredUser author, boolean like) {
		this.author = author;
		this.like = like;	
	}

	public RegisteredUser getAuthor() {
		return author;
	}

	public boolean isLike() {
		return like;
	}
}
