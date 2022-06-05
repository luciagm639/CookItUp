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

public class ViewQuestionsOption extends Show5<Question> {
	
	Recipe r;
	
	public ViewQuestionsOption(Recipe r) {
		super("See the questions");
		this.r = r;
	}
	
	protected ViewQuestionsOption(ViewQuestionsOption s, boolean isPrev) {
		super(s, isPrev);
		this.r = s.r;
	}

	@Override
	public void executeOption(Interface inter, Scanner lector, Menu prevMenu) {
		RegisteredUserInterface regInterface = Interface.toRegisteredUserInterface(inter);
		setList(r.getQuestionsList());
		setPrevMenu(prevMenu);		
		
		ViewQuestionsOption prevPage = null;
		if (hasPrevPage())
			prevPage = new ViewQuestionsOption(this, true);
		ViewQuestionsOption nextPage = null;
		if (hasNextPage())
			nextPage = new ViewQuestionsOption(this, false);
		List<Option> options = new LinkedList<>();
		execute(regInterface, lector, 0, prevPage, nextPage, options);
	}
}