package administrator;

import recipe.Recipe;
import system.MySystem;
import user.RegisteredUser;

public class AdministratorInterface {
	
	private final Administrator adm;
	private MySystem system;
	
	public AdministratorInterface (Administrator adm) {
		this.adm = adm;
		this.system = new MySystem();
	}
	
	public AdministratorInterface (Administrator adm, MySystem system) {
		this.adm = adm;
		this.system = system;
	}
	
	
	public void blockAccount(RegisteredUser us0) {
		system.addBlockedUser(us0);
		us0.setStatus(true);
	}
	
	public void unblockAccount(RegisteredUser us0) {
		system.removeBlockedUser(us0);
		us0.setStatus(false);
	}
	
	public void deleteAnyAccount(RegisteredUser us1) {
		for (Recipe r :us1.getRecipesList()) {
			r.setUser(system.getDefaultUser());
		}
		//TODO change comment and questions user
		/*
		for (Message m : us1.getMessageList()) {
			m.setAuthor(system.getDefaultUser());
		}
		*/
		system.removeUser(us1);
	}
	
	public void deleteRecipe(Recipe rec2) {
		system.removeRecipe(rec2);
	}
	
	public void deleteAdminAccount(Administrator adm2) {
		system.removeAdmin(adm2);
	}
}
