package gson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.InetAddress;
import java.net.PortUnreachableException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import administrator.Administrator;
import recipe.*;
import report.Report;
import system.MySystem;
import system.RecipeExtended;
import system.RecipePrinting;
import system.data.DataSet;
import user.RegisteredUser;
import user.*;
import administrator.*;

import java.net.InetAddress;

public class ClientSystem {
	
	private DataOutputStream dataOut = null;
	private DataInputStream dataIn = null;
	private Socket skt = null;
	private static final String address = "localhost";
	private static final int port = 21;
	private boolean running = true;
	private Gson parser = new Gson();

	public ClientSystem() {
		// Establishing connection with server
		try {
			// creating an object of socket
			skt = new Socket(address, port);
			// opening output stream on the socket
			dataOut = new DataOutputStream(skt.getOutputStream());
			dataIn = new DataInputStream(skt.getInputStream());
		} catch (UnknownHostException uh) {
			throw new RuntimeException("Couldn't connect to the system due to unkown host exception");
		} catch (IOException io) {
			throw new RuntimeException("Couldn't connect to the system due to input or output exception");
		}
	}

	public <T> String sendMessage(Message<T> message) {
		String line = null;
		if (running) {
			try {
				dataOut.writeUTF(message.toString());
				line = dataIn.readUTF();
			} catch (IOException e) {
				throw new RuntimeException("Error sending message: " + message);
			}
		}
		return line;
	}

	public void endConnection() {
		sendMessage(new Message<String>(Function.END_CONNECTION, ""));
		running = false;
		// for closing the connection
		try {
			dataOut.close();
			dataIn.close();
			skt.close();
		} catch (IOException io) {
			System.out.println(io);
		}
	}

	public RegisteredUser findUser(String name) {
		Message<String> message = new Message<String>(Function.FIND_USER, name);
		String text = sendMessage(message);
		RegisteredUser user = parser.fromJson(text, RegisteredUser.class);
		return user;
	}

	public Administrator findAdmin(String name) {
		Message<String> message = new Message<String>(Function.FIND_ADMIN, name);
		String text = sendMessage(message);
		Administrator admin = parser.fromJson(text, Administrator.class);
		return admin;
	}

	public void addAdmin(Administrator adm) {
		Message<Administrator> message = new Message<>(Function.ADD_ADMIN, adm);
		sendMessage(message);
	}

	public List<Recipe> getAllRecipes() {
		Message<List<Recipe>> message = new Message<>(Function.GET_ALL_RECIPES, null);
		String text = sendMessage(message);
		Type listOfMyClassObject = new TypeToken<ArrayList<Recipe>>() {
		}.getType();
		List<Recipe> list = parser.fromJson(text, listOfMyClassObject);
		return list;
	}

	public String showRecipes(List<Recipe> allRecipes) {
		Message<List<Recipe>> message = new Message<>(Function.SHOW_RECIPES, allRecipes);
		String text = sendMessage(message);
		return text;
	}

	public int addRecipe(Recipe r) {
		Message<Recipe> message = new Message<>(Function.CREATE_RECIPE, r);
		String text = sendMessage(message);
		int id = parser.fromJson(text, Integer.class);
		return id;
	}

	public boolean removeRecipe(Recipe r) {
		Message<Recipe> message = new Message<>(Function.DELETE_RECIPE, r);
		String text = sendMessage(message);
		boolean bool = parser.fromJson(text, Boolean.class);
		return bool;
	}

	public void addStep(Step step, Recipe recipe, int order) {
		// TODO Auto-generated method stub

	}

	public void replaceStep(Step step, Recipe recipe, int order) {
		// TODO Auto-generated method stub

	}

	public Ingredient addIngredient(Ingredient ingredient) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Recipe> filterRecipes(Set<Ingredient> fridge, List<RegisteredUser> following,
			List<RegisteredUser> blocked, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean addQuestion(Question question) {
		// TODO Auto-generated method stub
		return false;
	}

	public void addComment(Comment comment) {
		// TODO Auto-generated method stub

	}

	public void addReport(Report report) {
		// TODO Auto-generated method stub

	}

	public RegisteredUser getDefaultUser() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean removeUser(RegisteredUser reg) {
		// TODO Auto-generated method stub
		return true;
	}

	public void addReview(Review review) {
		// TODO Auto-generated method stub

	}

	public void addBlockedUser(RegisteredUser us0) {
		// TODO Auto-generated method stub

	}

	public void removeBlockedUser(RegisteredUser us0) {
		// TODO Auto-generated method stub

	}

	public boolean removeAdmin(Administrator adm2) {
		// TODO Auto-generated method stub
		return false;
	}

	public Set<Report> getReports() {
		// TODO Auto-generated method stub
		return null;
	}

	public Recipe findRecipe(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Recipe> filter(Set<Ingredient> fridge, List<RegisteredUser> following, List<RegisteredUser> blocked,
			String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<RegisteredUser> getBlockedUserList() {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<RegisteredUser> getUserList() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean hasRecipe(Recipe rec) {
		// TODO Auto-generated method stub
		return false;
	}

	public Set<Question> getQuestions() {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<Comment> getComments() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Tested
	 */
	public int addUser(RegisteredUser user) {
		Message<RegisteredUser> message = new Message<>(Function.ADD_USER, user);
		String text = sendMessage(message);
		return Integer.parseInt(text);
	}

	public void close() {
		Message<String> message = new Message<>(Function.CLOSE, null);
		sendMessage(message);
	}

	public void empty() {
		Message<String> message = new Message<>(Function.EMPTY, null);
		String text = sendMessage(message);
		System.out.println(text);
	}

	public List<Administrator> getAdminList() {
		Message<String> message = new Message<>(Function.GET_ADMINS, null);
		String text = sendMessage(message);
		Type type = new TypeToken<LinkedList<Administrator>>() {}.getType();
		return parser.fromJson(text, type);
	}

	public String showUserRecipes(RegisteredUser user) {
		Message<RegisteredUser> message = new Message<>(Function.SHOW_USER_RECIPES, user);
		return sendMessage(message);
	}

	/**
	 * Inbal's
	 */

	public List<RecipePrinting> getUserRecipes(RegisteredUser user) {
		Message<RegisteredUser> message = new Message<>(Function.GET_USER_RECIPES, user);
		String text = sendMessage(message);
		System.out.println(text);
		Type type = new TypeToken<LinkedList<RecipePrinting>>() {}.getType();
		return parser.fromJson(text, type);
//		return null;
		//TODO
	}	

	public boolean block(RegisteredUser reg, RegisteredUser us2) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isBlocked(RegisteredUser reg, RegisteredUser us2) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean unblock(RegisteredUser reg, RegisteredUser us1) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean follow(RegisteredUser reg, RegisteredUser us2) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isFollowed(RegisteredUser reg, RegisteredUser us2) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean unfollow(RegisteredUser reg, RegisteredUser us1) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<RegisteredUser> getBlockList(RegisteredUser reg) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<RegisteredUser> getFollowList(RegisteredUser reg) {
		// TODO Auto-generated method stub
		return null;
	}
}
