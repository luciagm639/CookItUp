package system.data;

import recipe.Question;
import recipe.Recipe;
import system.MySystem;
import user.RegisteredUser;

public final class QuestionsList extends DataSet<Question> {

	public QuestionsList() {
		super("Questions.txt");
	}

	@Override
	public void readData(MySystem system, String text) {
		MyStringSplitter sp = new MyStringSplitter(text);
		
		int id = sp.nextInt();
		RegisteredUser author = system.getUser(sp.nextInt());
		Recipe recipe = system.getRecipe(sp.nextInt());
		String question = sp.next();
		
		Question c = new Question(author, question, recipe);
		add(c, id);
		system.addQuestion(c);
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
