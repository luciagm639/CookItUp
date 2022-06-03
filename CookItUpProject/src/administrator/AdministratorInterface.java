package administrator;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import recipe.Comment;
import recipe.Question;
import recipe.Recipe;
import report.*;
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
	}
	
	public void unblockAccount(RegisteredUser us0) {
		system.removeBlockedUser(us0);		
	}
	
	public boolean deleteAnyAccount(RegisteredUser us1) {
		return system.removeUser(us1);
	}
	
	public boolean deleteRecipe(Recipe rec2) {
		return system.removeRecipe(rec2);
	}
	
	public boolean deleteAdminAccount(Administrator adm2) {
		return system.removeAdmin(adm2);
	}	
	
	public Administrator getAdministrator() {
		return adm;
	}
	
	public void close() {
		system.close();
	}
	
	/**
	 * Reports
	 */
	public List<ReportComment> getCommentReports() {
		Set<Report> set = system.getReports();
		List<ReportComment> list = new LinkedList<>();
		for (Report r : set) {
			if (r instanceof ReportComment) {
				list.add((ReportComment) r);
			}
		}
		return list;
	}
	
	public List<ReportQuestion> getQuestionReports() {
		Set<Report> set = system.getReports();
		List<ReportQuestion> list = new LinkedList<>();
		for (Report r : set) {
			if (r instanceof ReportQuestion) {
				list.add((ReportQuestion) r);
			}
		}
		return list;
	}
	
	public List<ReportRecipe> getRecipeReports() {
		Set<Report> set = system.getReports();
		List<ReportRecipe> list = new LinkedList<>();
		for (Report r : set) {
			if (r instanceof ReportRecipe) {
				list.add((ReportRecipe) r);
			}
		}
		return list;
	}
	
	public List<ReportUser> getUserReports() {
		Set<Report> set = system.getReports();
		List<ReportUser> list = new LinkedList<>();
		for (Report r : set) {
			if (r instanceof ReportUser) {
				list.add((ReportUser) r);
			}
		}
		return list;
	}
}

