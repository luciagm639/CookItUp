package Class;

public class RegisteredUser {
	private String name;
	private String password;
	private int chips;
	private RegisteredUser listFollowing[];
	private RegisteredUser listBlocked[];
	
	public boolean checkChips(int nChips) {
		boolean canSpend = false;
		return canSpend;
	}
	
	public void obtainChips(int mode) {
		
	}
	
	public RegisteredUser[] addUserToBlockedList(RegisteredUser registeredUser) {
		return this.listBlocked;
	}
	
	public RegisteredUser[] deleteUserFromBlockedList(RegisteredUser registeredUser) {
		return this.listBlocked;
	}
	
	public RegisteredUser[] addUserToFollowingList(RegisteredUser registeredUser) {
		return listFollowing;
	}
	
	public RegisteredUser[] deleteUserFromFollowingList(RegisteredUser registeredUser) {
		return listFollowing;
	}
	
	public void makeRegisteredUser(String name, String password) {
		this.name = name;
		this.password = password;
	}
}