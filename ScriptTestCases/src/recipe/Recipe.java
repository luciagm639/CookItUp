package recipe;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import report.Report;
import user.*;

public class Recipe {

	private RegisteredUser user;
	private String name;
	private int priority;
	private Set<Comment> commentsList;
	private Set<Question> questionsList;
	private Set<Ingredient> ingredientsList;
	private Set<RecipePhoto> photosList;
	private List<Step> stepsList;


	public Recipe(String name, int priority, RegisteredUser user) {
		this.name = name;
		this.commentsList = new HashSet<Comment>();
		this.ingredientsList = new HashSet<Ingredient>();
		this.questionsList = new HashSet<Question>();
		this.photosList = new HashSet<RecipePhoto>();
		this.stepsList = new ArrayList<Step>();
		this.priority = priority;
		this.user = user;
	}
	
	public Recipe(String name, RegisteredUser user) {
		this(name, 0, user);
	}

	public RegisteredUser getUser() {
		return user;
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

	public void addComment(Comment comment) {
		commentsList.add(comment);		
	}

	public void addQuestion(Question question) {
		questionsList.add(question);
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
	
	@Override
	public boolean equals(Object o) {
		return (o instanceof Recipe) && (((Recipe) o).name.equalsIgnoreCase(name));
	}
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(name+"\n");
		sb.append("Author: " + user + "\n");
		sb.append("Ingredients:\n");
		for (Ingredient i : ingredientsList) {
			sb.append("\t"+i+"\n");
		}
		sb.append("Steps:\n");
		for(int i = 0; i < stepsList.size(); i++) {
			sb.append((i+1)+". "+stepsList.get(i)+"\n");
		}
		return sb.toString();
	}

	//With comments and questions
	public String toStringExtended() {
		StringBuilder sb = new StringBuilder();
		sb.append(this);
		sb.append("Questions: \n");
		for (Question q : questionsList) {
			sb.append("- " + q + "\n");
		}
		sb.append("Comments: \n");
		for (Comment c : commentsList) {
			sb.append("- " + c + "\n");
		}
		return sb.toString();
	}
}