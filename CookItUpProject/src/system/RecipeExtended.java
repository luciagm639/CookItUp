package system;

import java.util.HashSet;
import java.util.Set;

import recipe.Comment;
import recipe.Ingredient;
import recipe.Question;
import recipe.Recipe;
import recipe.Review;
import user.RegisteredUser;

public class RecipeExtended extends Recipe {

	private Set<Comment> commentsList;
	private Set<Question> questionsList;
	private Set<Review> reviewList;

	public RecipeExtended(String name, int priority, RegisteredUser user) {
		super(name, priority, user);

		this.commentsList = new HashSet<Comment>();
		this.questionsList = new HashSet<Question>();
		this.reviewList = new HashSet<Review>();
	}
	
	public RecipeExtended(Recipe recipe) {
		super(recipe.getName(), recipe.getPriority(), recipe.getUser());
	}

	/**
	 * Comments
	 */
	public Set<Comment> getCommentsList() {
		return commentsList;
	}

	public boolean addComment(Comment comment) {
		return commentsList.add(comment);
	}

	/**
	 * Questions
	 */
	public Set<Question> getQuestionsList() {
		return questionsList;
	}

	public boolean addQuestion(Question question) {
		return questionsList.add(question);
	}

	/**
	 * Reviews
	 */
	public Set<Review> getReviews() {
		return reviewList;
	}

	public boolean addReview(Review r) {
		return reviewList.add(r);
	}

	public boolean deleteReview(Review r) {
		return reviewList.remove(r);
	}

	public int getNumberofLikes() {
		int cont = 0;
		for (Review r : reviewList) {
			if (r.isLike())
				cont++;
		}
		return cont;
	}

	public int getNumberofDislikes() {
		int cont = 0;
		for (Review r : reviewList) {
			if (!r.isLike())
				cont++;
		}
		return cont;
	}
	
	/**
	 * To Strings
	 */
	// Limitamos el numero de ingredientes y pasos a mostrar a 5
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(name + "\n");
		sb.append("Author: " + user + "\n");
		sb.append("Number of likes: " + getNumberofLikes() + ", Number of dislikes: " + getNumberofDislikes() + "\n");

		sb.append("Ingredients:\n");
		int cont = 0;
		for (Ingredient i : ingredientsList) {
			if (cont == 5) {
				sb.append("\t...\n");
				break;
			}
			sb.append("\t" + i + "\n");
			cont++;
		}

		sb.append("Steps:\n");
		cont = 0;
		for (int i = 0; i < stepsList.size(); i++) {
			if (cont == 5) {
				sb.append("...\n");
				break;
			}
			sb.append((i + 1) + ". " + stepsList.get(i) + "\n");
			cont++;
		}
		return sb.toString();
	}

	// Limitamos el numero de comentarios y preguntas a 2
	public String toStringExtended() {
		StringBuilder sb = new StringBuilder();
		sb.append(name + "\n");
		sb.append("Author: " + user + "\n");
		sb.append("Number of likes: " + getNumberofLikes() + ", Number of dislikes: " + getNumberofDislikes() + "\n");
		sb.append("Ingredients:\n");
		for (Ingredient i : ingredientsList) {
			sb.append("\t" + i + "\n");
		}
		sb.append("Steps:\n");
		for (int i = 0; i < stepsList.size(); i++) {
			sb.append((i + 1) + ". " + stepsList.get(i) + "\n");
		}
		sb.append("Questions: \n");
		int cont = 0;
		for (Question q : questionsList) {
			if (cont == 2) {
				sb.append("...\n");
				break;
			}
			sb.append("- " + q);
			cont++;
		}
		sb.append("Comments: \n");
		cont = 0;
		for (Comment c : commentsList) {
			if (cont == 2) {
				sb.append("...\n");
				break;
			}
			sb.append("- " + c);
			cont++;
		}
		return sb.toString();
	}

	public Recipe unextend() {
		return new Recipe(this);
	}

}
