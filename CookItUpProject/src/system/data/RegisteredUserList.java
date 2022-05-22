package system.data;

import system.MySystem;
import user.RegisteredUser;

public class RegisteredUserList extends DataSet<RegisteredUser> {
	
	public RegisteredUserList() {
		super("RegisteredUsers.txt");
	}

	public RegisteredUser findUser(String name) {
		for (RegisteredUser user : this) {
			if (user.getName().equals(name)) {
				return user;
			}
		}
		return null;
	}
	
	/*Data Saver*/	
	@Override
	public void readData(MySystem system, String text) {
		String[] sp = split(text);
		
		try {
			String name = sp[0];
			int id = Integer.parseInt(sp[1]);
			String password = sp[2];
			int chips = Integer.parseInt(sp[3]);
			boolean status = Boolean.getBoolean(sp[4]);
		
			add(new RegisteredUser(name, id, password, chips, status));
		} catch(NumberFormatException e) {/*We don't add*/}
	}
	
	@Override
	public String writeData(RegisteredUser user) {
		MyStringJoiner sj = new MyStringJoiner();
		sj.add(user.getName());
		sj.add(user.getId());
		sj.add(user.getPassword());
		sj.add(user.getChips());
		sj.add(user.getStatus());
		
		return sj.toString();
	}
}
