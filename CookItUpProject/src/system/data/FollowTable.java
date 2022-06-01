package system.data;

import system.MySystem;
import user.RegisteredUser;

public class FollowTable extends Table<RegisteredUser, RegisteredUser> {
	
	public FollowTable() {
		super("FollowTable.txt");
	}
	
	public FollowTable(MySystem system) {
		this();
		getData(system);
	}

	@Override
	public void readData(MySystem system, String text) {
		MyStringSplitter sp = new MyStringSplitter(text);
		RegisteredUser user = system.getUser(sp.nextInt());
		RegisteredUser followed = system.getUser(sp.nextInt());
		
		user.follow(followed);
	}

	@Override
	public void getData(MySystem system) {
		for (RegisteredUser u : system.getUserList()) {
			for (RegisteredUser f : u.getFollowList()) {
				add(new Tuple<>(u, f));
			}
		}
	}
}
