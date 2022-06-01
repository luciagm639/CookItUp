package system.data;

import system.MySystem;
import user.RegisteredUser;

public class BlockedTable extends Table<RegisteredUser, RegisteredUser> {
	
	public BlockedTable() {
		super("BlockedTable.txt");
	}
	
	public BlockedTable(MySystem system) {
		this();
		getData(system);
	}

	@Override
	public void readData(MySystem system, String text) {
		MyStringSplitter sp = new MyStringSplitter(text);
		RegisteredUser user = system.getUser(sp.nextInt());
		RegisteredUser blocked = system.getUser(sp.nextInt());
		
		user.block(blocked);
	}

	@Override
	public void getData(MySystem system) {
		for (RegisteredUser u : system.getUserList()) {
			for (RegisteredUser b : u.getBlockList()) {
				add(new Tuple<>(u, b));
			}
		}
	}
}
