package menu.InitialMenu.RegisteredUser.Recipes;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import menu.Interface;
import menu.Menu;
import menu.Option;
import menu.Show5;
import menu.InitialMenu.RegisteredUser.Recipes.SelectRecipe;
import user.RegisteredUserInterface;

import recipe.*;

public class ViewCommentsOption extends Show5<Comment> {
	
	Recipe r;
	
	public ViewCommentsOption(Recipe r) {
		super("See the comments");
		this.r = r;
	}
	
	protected ViewCommentsOption(ViewCommentsOption s, boolean isPrev) {
		super(s, isPrev);
		this.r = s.r;
	}

	@Override
	public void executeOption(Interface inter, Scanner lector, Menu prevMenu) {
		RegisteredUserInterface regInterface = Interface.toRegisteredUserInterface(inter);
		setList(r.getCommentsList());
		setPrevMenu(prevMenu);		
		
		ViewCommentsOption prevPage = null;
		if (hasPrevPage())
			prevPage = new ViewCommentsOption(this, true);
		ViewCommentsOption nextPage = null;
		if (hasNextPage())
			nextPage = new ViewCommentsOption(this, false);
		List<Option> options = new LinkedList<>();
		execute(regInterface, lector, 0, prevPage, nextPage, options);
	}
}