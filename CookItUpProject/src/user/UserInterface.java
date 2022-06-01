package user;

import java.util.ArrayList;
import java.util.List;

import administrator.Administrator;
import administrator.AdministratorInterface;
import recipe.Recipe;
import system.MySystem;

public class UserInterface {
	
	MySystem system;
	
	public UserInterface() {
		this.system = new MySystem();
	}
	
	public UserInterface(MySystem system) {
		this.system = system;
	}
	
	//TODO If the Id = 0 then block the log in, because it is for the deleted/default user
	public RegisteredUserInterface logIn(String name, String password) {
		RegisteredUser user = system.findUser(name);
		RegisteredUserInterface us0interface = null;
		if (user == null) {
			System.out.println("Name was not found");
		} else {
			if (user.getPassword().equals(password)) {
				System.out.println("You logged in");
				us0interface = new RegisteredUserInterface(user, system);
			}else {
				System.out.println("Incorrect password");
			}
		}
		return us0interface;
	}
	
	public AdministratorInterface logInAdmin(String name, String password) {
		Administrator adm = system.findAdmin(name);
		AdministratorInterface i = null;
		if (adm == null) {
			System.out.println("Name was not found");
		} else {
			if (adm.getPasword().equals(password)) {
				System.out.println("You logged in as an admin");
				i = new AdministratorInterface(adm, system);
			}
		}
		return i;
	}
	
	public RegisteredUserInterface registerNewAccount(String name, String password) {
		RegisteredUser user = system.findUser(name);
		if (user == null) {
			if (validatePassword(password)) {
				user = new RegisteredUser(name, password);
				system.addUser(user);
				System.out.println("You have successfully created a new account");
				return new RegisteredUserInterface(user, system);
			}
			else {
				System.out.println("Password is not safe enough");
			}
		}
		else {
			System.out.println("The requested user name is already in use");
		}
		return null;
	}
	
	//TODO añadir seguridad, por ejemplo una contraseña para crear un administrador
	public AdministratorInterface registerNewAdminAccount(String name, String password) {
		Administrator adm = system.findAdmin(name);
		if (adm == null) {
			if (validatePassword(password)) {
				adm = new Administrator(name, password);
				system.addAdmin(adm);
				System.out.println("You have successfully created a new administrator account");
				return new AdministratorInterface(adm, system);
			}
			else {
				System.out.println("Password is not safe enough");
			}
		}
		else {
			System.out.println("The requested administrator name is already in use");
		}
		return null;
	}
	
	public boolean validatePassword(String name) {
		//8 caracteres minimo
		return name.length() >= 8;
	}
	
	public String showAllRecipes() {
		return system.showRecipes(system.getAllRecipes());
	}
	
	public List<Recipe> getAllRecipes() {
		return system.getAllRecipes();
	}
	
	/*No entiendo esta funcion*/
	public boolean isReg(UserInterface us4, String name, String password) {
		RegisteredUser user = system.findUser(name);
		if (user != null) {
			if(user.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}
	
	public void close() {
		system.close();
	}
}
