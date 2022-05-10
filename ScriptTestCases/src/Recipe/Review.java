package Recipe;

import User.RegisteredUser;

public class Review {
	
	RegisteredUser author;
	boolean like;
	
	public Review(RegisteredUser author, boolean like) {
		this.author = author;
		this.like = like;	
	}
}
