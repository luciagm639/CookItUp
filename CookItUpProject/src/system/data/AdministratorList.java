package system.data;

import administrator.Administrator;
import system.MySystem;

public final class AdministratorList extends DataSet<Administrator> {

	public AdministratorList() {
		super("Administrators.txt");
	}

	@Override
	public void readData(MySystem system, String text) {
		MyStringSplitter sp = new MyStringSplitter(text);
		int id = sp.nextInt();
		String name = sp.next();
		String password = sp.next();
		Administrator adm = new Administrator(name, password);
		add(adm, id);
	}

	@Override
	public String writeData(Administrator adm) {
		MyStringJoiner sj = new MyStringJoiner();
		sj.add(adm.getId());
		sj.add(adm.getName());
		sj.add(adm.getPasword());
		
		return sj.toString();
	}

	public Administrator find(String name) {
		for (Administrator adm : this) {
			if (adm.getName().equals(name)) {
				return adm;
			}
		}
		return null;
	}
}
