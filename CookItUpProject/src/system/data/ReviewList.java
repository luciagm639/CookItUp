package system.data;

import recipe.Recipe;
import recipe.Review;
import system.MySystem;
import user.RegisteredUser;

public class ReviewList extends DataSet<Review> {
	
	public ReviewList() {
		super("Reviews.txt");
	}

	@Override
	public void readData(MySystem system, String text) {
		MyStringSplitter sp = new MyStringSplitter(text);
		int id = sp.nextInt();
		RegisteredUser author = system.getUser(sp.nextInt());
		Recipe recipe = system.getRecipe(sp.nextInt());
		boolean like = sp.nextBoolean();
		Review r = new Review(author, like, recipe);
		add(r, id);
	}

	@Override
	public String writeData(Review review) {
		MyStringJoiner sj = new MyStringJoiner();
		sj.add(review.getId());
		sj.add(review.getAuthor().getId());
		sj.add(review.getRecipe().getId());
		sj.add(review.isLike());
		
		return sj.toString();
	}
	
	public int numLikes(Recipe recipe) {
		int cont = 0;
		for (Review r : this) {
			if (r.getRecipe().equals(recipe)) {
				if (r.isLike())
					cont++;
			}
		}
		
		return cont;
	}
	
	public int numDislikes(Recipe recipe) {
		int cont = 0;
		for (Review r : this) {
			if (r.getRecipe().equals(recipe)) {
				if (!r.isLike())
					cont++;
			}
		}
		
		return cont;
	}
	
	public int[] numLikesDislikes(Recipe recipe) {
		int cont[] = new int[2];
		cont[0] = 0;//number of likes
		cont[1] = 0;//number of dislikes
		for (Review r : this) {
			if (r.getRecipe().equals(recipe)) {
				if (r.isLike())
					cont[0]++;
				else
					cont[1]++;
			}
		}
		return cont;
	}
	
	public int numLikes(RegisteredUser u) {
		int cont = 0;
		for (Review r : this) {
			if (r.getRecipe().getUser().equals(u)) {
				if (r.isLike())
					cont++;
			}
		}
		return cont;
	}
}
