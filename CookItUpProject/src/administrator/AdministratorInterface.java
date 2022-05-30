package administrator;

import recipe.Comment;
import recipe.Question;
import recipe.Recipe;
import system.MySystem;
import user.RegisteredUser;

public class AdministratorInterface {
	
	private final Administrator adm;
	private MySystem system;
	
	public AdministratorInterface (Administrator adm, MySystem system) {
		this.adm = adm;
		this.system = system;
	}
	
	
	public void blockAccount(RegisteredUser us0) {
		system.addBlockedUser(us0);
		
	}
	
	public void unblockAccount(RegisteredUser us0) {
		system.removeBlockedUser(us0);
		
	}
	
	public void deleteAnyAccount(RegisteredUser us1) {
		for (Recipe r :us1.getRecipesList()) {
			r.setUser(system.getDefaultUser());
		}
		for (Comment m : us1.getCommentList()) {
			m.setAuthor(system.getDefaultUser());
		}
		for (Question m : us1.getQuestionList()) {
			m.setAuthor(system.getDefaultUser());
		}
		system.removeUser(us1);
	}
	
	public void deleteRecipe(Recipe rec2) {
		system.removeRecipe(rec2);
	}
	
	public void deleteAdminAccount(Administrator adm2) {
		system.removeAdmin(adm2);
	}


	public Administrator getAdministrator() {
		return adm;
	}
	
	
}