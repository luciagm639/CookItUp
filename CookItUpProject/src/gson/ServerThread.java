package gson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import administrator.Administrator;
import recipe.Recipe;
import system.MySystem;
import system.RecipeExtended;
import system.RecipePrinting;
import system.RegisteredUserExtended;
import user.RegisteredUser;

public class ServerThread extends Thread {

	private MySystem system;
	private ServerSystem server;
	private DataInputStream inStream = null;
	private DataOutputStream outStream = null;
	private Socket socket = null;
	private boolean connection = false;
	private Gson parser = new Gson();

	public ServerThread(MySystem system, ServerSystem server, Socket socket) {
		this.system = system;
		this.server = server;
		this.socket = socket;
	}

	private String executeFunction(Message<String> message) {
		String line;
		String text = message.getElement();
		switch (message.getFunction()) {
		case CREATE_RECIPE:
			line = createRecipe(text);
			break;
		case DELETE_RECIPE:
			line = deleteRecipe(text);
			break;
		case FIND_USER:
			line = parser.toJson(system.findUser(fromJson(text)), RegisteredUser.class);
			break;
		case FIND_ADMIN:
			line = parser.toJson(system.findAdmin(fromJson(text)), Administrator.class);
			break;
		case ADD_USER:
			line = addUser(text);
			break;
		case ADD_ADMIN:
			line = addAdmin(text);
			break;
		case GET_ALL_RECIPES:
			line = getAllRecipes(text);
			break;
		case SHOW_RECIPES:
			line = showRecipes(text);
			break;
		case CLOSE:
			line = save();
			break;
		case EMPTY:
			line = clean();
			break;
		case GET_ADMINS:
			line = getAdmins();
			break;
		case SHOW_USER_RECIPES:
			line = showUserRecipes(text);
			break;
		case GET_USER_RECIPES:
			line = getUserRecipes(text);
			break;
		case END_CONNECTION:
			line = ""; connection = false;
			break;
		default:
			System.out.println("Not found");
			line = "Funcion no encontrada";
		}
		return line;
	}

	private String getUserRecipes(String text) {
		RegisteredUser u = parser.fromJson(text, RegisteredUser.class);
		RegisteredUserExtended user = system.getUser(u.getId());
		List<RecipePrinting> list = RecipePrinting.parse(user.getRecipesList());
		Type type = new TypeToken<LinkedList<RecipePrinting>>() {
		}.getType();
		return parser.toJson(list, type);
//		return "";
		// TODO Auto-generated method stub
	}

	private String showUserRecipes(String text) {
		RegisteredUser u = parser.fromJson(text, RegisteredUser.class);
		RegisteredUserExtended user = system.getUser(u.getId());
		List<RecipeExtended> list = user.getRecipesList();
		return system.showRecipes(list);
	}

	private String getAdmins() {
		List<Administrator> admins = new LinkedList<>(system.getAdminList());
		Type type = new TypeToken<LinkedList<Administrator>>() {
		}.getType();
		return parser.toJson(admins, type);
	}

	private String save() {
		System.out.println("saving data into files...");
		system.close();
		return "";
	}

	private String clean() {
		System.out.println("deleting everything...");
		system.empty();
		return "";
	}

	private String createRecipe(String text) {
		String line = null;
		System.out.println("creating recipe...");

		Gson parser = new Gson();
		Recipe r = parser.fromJson(text, Recipe.class);
		RecipeExtended re = new RecipeExtended(r);

		system.addRecipe(re);
		System.out.println("recipe has been created");
		line = Integer.toString(re.getId());
		return line;
	}

	private static String fromJson(String text) {
		try {
			return text.replace("\"", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private String showRecipes(String text) {
		System.out.println("showing recipes...");
		Gson parser = new Gson();
//        String jsonString = parser.toJson(system.showRecipes(text));
//
//        return jsonString;
		// TODO
		return null;
	}

	private String getAllRecipes(String text) {
		System.out.println("getting all recipes...");
		Gson parser = new Gson();
		String jsonString = parser.toJson(system.getAllRecipes());

		return jsonString;
	}

	private String addUser(String text) {
		System.out.println("adding user...");
		Gson parser = new Gson();
		RegisteredUser u = parser.fromJson(text, RegisteredUser.class);
		System.out.println(u);
		RegisteredUserExtended reg = new RegisteredUserExtended(u);
		system.addUser(reg);

		return Integer.toString(reg.getId());
	}

	private String addAdmin(String text) {
		System.out.println("adding admin...");

		Gson parser = new Gson();
		Administrator r = parser.fromJson(text, Administrator.class);
		system.addAdmin(r);
		;
		return Integer.toString(r.getId());
	}

	private String deleteRecipe(String text) {
		System.out.println("deleting recipe...");

		Gson parser = new Gson();
		Recipe r = parser.fromJson(text, Recipe.class);
		RecipeExtended re = new RecipeExtended(r);

		String line = Boolean.toString(system.removeRecipe(re));
		System.out.println("recipe has been deleted");

		return line;
	}

	public void exit() {
		connection = false;
	}

	@Override
	public void run() {
		try {
			inStream = new DataInputStream(socket.getInputStream());
			outStream = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connection = true;
		while (connection) {
			try {
				// Receiving input messages from the client using socket
				String line = inStream.readUTF();
				System.out.println("Received input:\n" + line);
				Message<String> message = Message.getMessage(line);
				line = executeFunction(message);
				System.out.println("Sending output:\n" + line);
				outStream.writeUTF(line);
			} catch (IOException io) {
				System.out.println(io);
			}
		}
		try {
			socket.close();
			inStream.close();
			outStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Connection Closed");
	}

}
