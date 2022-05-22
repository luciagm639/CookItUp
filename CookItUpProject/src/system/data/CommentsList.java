package system.data;

import recipe.Comment;
import recipe.Recipe;
import system.MySystem;
import user.RegisteredUser;

public final class CommentsList extends DataSet<Comment> {
	
	public CommentsList() {
		super("Comments.txt");
	}

	@Override
	public void readData(MySystem system, String text) {
		MyStringSplitter sp = new MyStringSplitter(text);
		
		int id = sp.nextInt();
		RegisteredUser author = system.getUser(sp.nextInt());
		Recipe recipe = system.getRecipe(sp.nextInt());
		String comment = sp.next();
		
		Comment c = new Comment(author, comment, recipe);
		add(c, id);
		system.addComment(c);
	}

	@Override
	public String writeData(Comment comment) {
		MyStringJoiner sj = new MyStringJoiner();
		sj.add(comment.getId());
		sj.add(comment.getAuthor().getId());
		sj.add(comment.getRecipe().getId());
		sj.add(comment.getText());
		
		return sj.toString();
	}
}