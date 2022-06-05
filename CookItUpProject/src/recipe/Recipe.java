package recipe;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import system.data.Data;
import user.*;

public class Recipe extends Data<Recipe> {

	private RegisteredUser user;
	private String name;
	private int priority;
	private Set<Comment> commentsList;
	private Set<Question> questionsList;
	private Set<Ingredient> ingredientsList;
	private Set<RecipePhoto> photosList;
	private List<Step> stepsList;
	private Set<Review> reviewList;


	public Recipe(String name, int priority, RegisteredUser user) {
		this.name = name;
		this.priority = priority;
		this.user = user;
		
		this.commentsList = new HashSet<Comment>();
		this.ingredientsList = new HashSet<Ingredient>();
		this.questionsList = new HashSet<Question>();
		this.photosList = new HashSet<RecipePhoto>();
		this.stepsList = new ArrayList<Step>();
		this.reviewList = new HashSet<Review>();
	}
	
	public Recipe(String name, RegisteredUser user) {
		this(name, 0, user);
	}
	
	@Override
	public boolean equals(Object o) {
		return (o instanceof Recipe) && (((Recipe) o).name.equalsIgnoreCase(name));
	}
	
	@Override
	public int hashCode() {
		return name.toLowerCase().hashCode();
	}
	
	//Limitamos el numero de ingredientes y pasos a mostrar a 5
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(name+"\n");
		sb.append("Author: " + user + "\n");
		sb.append("Number of likes: " + getNumberofLikes() + ", Number of dislikes: " + getNumberofDislikes() + "\n");
		
		sb.append("Ingredients:\n");
		int cont = 0;
		for (Ingredient i : ingredientsList) {
			if (cont == 5) {
				sb.append("\t...\n");
				break;
			}
			sb.append("\t"+i+"\n");
			cont++;
		}
		
		sb.append("Steps:\n");
		cont = 0;
		for(int i = 0; i < stepsList.size(); i++) {
			if (cont == 5) {
				sb.append("...\n");
				break;
			}
			sb.append((i+1)+". "+stepsList.get(i)+"\n");
			cont++;
		}
		return sb.toString();
	}

	//Limitamos el numero de comentarios y preguntas a 2
	public String toStringExtended() {
		StringBuilder sb = new StringBuilder();
		sb.append(name+"\n");
		sb.append("Author: " + user + "\n");
		sb.append("Number of likes: " + getNumberofLikes() + ", Number of dislikes: " + getNumberofDislikes() + "\n");
		sb.append("Ingredients:\n");
		for (Ingredient i : ingredientsList) {
			sb.append("\t"+i+"\n");
		}
		sb.append("Steps:\n");
		for(int i = 0; i < stepsList.size(); i++) {
			sb.append((i+1)+". "+stepsList.get(i)+"\n");
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
	
	//TODO implement a list with this order
	/*
	@Override
	public int compareTo(Recipe recipe) {
		if (this.priority > recipe.priority)
			return -1;
		if (this.priority < recipe.priority)
			return 1;
		return this.name.compareTo(recipe.name);
	}
	*/

	public RegisteredUser getUser() {
		return user;
	}
	
	public void setUser(RegisteredUser defaultUser) {
		user = defaultUser;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getPriority() {
		return priority;
	}
	
	public void setPriority(int p) {
		priority = p;
	}

	public Set<RecipePhoto> getPhotosList() {
		return photosList;
	}

	public Set<Comment> getCommentsList() {
		return commentsList;
	}

	public Set<Question> getQuestionsList() {
		return questionsList;
	}

	public Set<Ingredient> getIngredientsList() {
		return ingredientsList;
	}

	public List<Step> getStepsList() {
		return stepsList;
	}

	public void addRecipePhoto(RecipePhoto photo) {
		photosList.add(photo);
	}

	public boolean addComment(Comment comment) {
		return commentsList.add(comment);		
	}

	public boolean addQuestion(Question question) {
		return questionsList.add(question);
	}

	public void addIngredient(Ingredient ingredient) {
		ingredientsList.add(ingredient);
	}

	public void deleteIngredient(Ingredient ing) {
		ingredientsList.remove(ing);
	}

	public void addStep(Step step) {
		stepsList.add(step);
	}
	
	public void addStep(Step step, int index) {
		stepsList.add(index, step);
	}
	
	public void deleteStep(Step step) {
		stepsList.remove(step);
	}
	
	public void deleteStep(int index) {
		stepsList.remove(index);
	}

	public boolean checkOwner(RegisteredUser reg) {
		return user.equals(reg);
	}
	
	/*Reviews*/
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
}