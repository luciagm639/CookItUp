package system.data;

import recipe.Question;
import recipe.Recipe;
import system.MySystem;
import system.RecipeExtended;
import system.RegisteredUserExtended;
import user.RegisteredUser;

public final class QuestionsList extends DataSet<Question, Question> {

	public QuestionsList() {
		super("Questions.txt");
	}

	@Override
	public void readData(MySystem system, String text) {
		MyStringSplitter sp = new MyStringSplitter(text);
		
		int id = sp.nextInt();
		RegisteredUserExtended author = system.getUser(sp.nextInt());
		RecipeExtended recipe = system.getRecipe(sp.nextInt());
		String question = sp.next();
		
		Question c = new Question(author.unextend(), question, recipe.unextend());
		add(c, id);
		system.addQuestion(c);
		recipe.addQuestion(c);
	}

	@Override
	public String writeData(Question question) {
		MyStringJoiner sj = new MyStringJoiner();
		sj.add(question.getId());
		sj.add(question.getAuthor().getId());
		sj.add(question.getRecipe().getId());
		sj.add(question.getText());
		
		return sj.toString();
	}
}
