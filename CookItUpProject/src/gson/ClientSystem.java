package gson;

import java.lang.reflect.Type;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.PortUnreachableException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import administrator.Administrator;
import recipe.*;
import report.Report;
import system.MySystem;
import system.RecipeExtended;
import system.data.DataSet;
import user.RegisteredUser;
import user.*;
import administrator.*;

import java.net.InetAddress;

public class ClientSystem extends Thread {

	private String IPservidor;
	private int puertoServidor;
	DatagramSocket serviceSocket;
	private Gson parser;

	public ClientSystem() {
		// direccion IP del servidor
		// IPservidor = "172.16.135.66";
		IPservidor = "10.10.52.87";
		try {
			IPservidor = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		parser = new Gson();

		// puerto del servidor
		puertoServidor = 10;

		// Declaramos el socket vacio
		serviceSocket = null;

		// Crear socket
		try {
			serviceSocket = new DatagramSocket();
		} catch (SocketException e) {
			System.err.println("Error: No se ha podido crear el socket");
			System.exit(-1);
		}

	}

	public String sendDatagram(String userInput) {

		// Comprobamos si el usuario quiere terminar el servicio
		if (userInput.compareTo("END") != 0) {

			try {
				/*
				 * Creamos un datagrama que mande la linea de texto introducida por el usuario
				 * al servidor indicado por los argumentos de la consola
				 */
				byte[] data = userInput.getBytes(StandardCharsets.UTF_8);
				DatagramPacket datagram = new DatagramPacket(data, data.length, InetAddress.getByName(IPservidor),
						puertoServidor);
				// Enviamos el datagrama recientemente creado

				serviceSocket.send(datagram);

				/*
				 * Crear e inicializar un datagrama vacio para recibir la respuestade como
				 * maximo 500 bytes
				 */
				byte[] buffer = new byte[500];
				DatagramPacket receivedDatagram = new DatagramPacket(buffer, buffer.length);

				// Recibimos el datagrama de respuesta
				serviceSocket.receive(receivedDatagram);

				// Extraemos la respuesta del datagrama en forma de String
				String line = new String(receivedDatagram.getData(), receivedDatagram.getOffset(),
						receivedDatagram.getLength(), StandardCharsets.UTF_8);

				return line;
			} catch (UnknownHostException | PortUnreachableException e) {
				System.err.println(e.getMessage());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		} else {

			serviceSocket.close();
		}
		return null;
	}

	public <T> String sendDatagram(Message<T> m) {
		return sendDatagram(m.toString());
	}

	@Override
	public void run() {
		
		UserInterface userInt = new UserInterface();
        userInt.registerNewAdminAccount("Jordan", "123446789");

//		MySystem system = new MySystem();
//
//		Recipe r = new Recipe("hola", system.getDefaultUser());
//		Message<Recipe> message = new Message<Recipe>(Function.CREATE_RECIPE, r);
//
//		System.out.println(sendDatagram(message));
	}

	public static void main(String[] args) {
		ClientSystem c = new ClientSystem();
		c.start();
		try {
			c.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("FINISHED");
	}
	/*
	 * public String createRecipe(String name, int idUser) { return
	 * sendDatagran(ServerSystem.CREATE_RECIPE+"\t"+name+"\t"+idUser); }
	 * 
	 * public String deleteRecipe(int idRecipe, int idUser) { return
	 * sendDatagran(ServerSystem.DELETE_RECIPE+"\t"+idRecipe+'\t'+idUser); }
	 * 
	 * public String getRecipes(int page, int idUser) { return
	 * sendDatagran(ServerSystem.LIST_OF_RECIPES+"\t"+page+"\t"+"\t"+idUser+"\t"+
	 * false+"\t"+false+"\t"+null); }
	 */
	
	public RegisteredUser findUser(String name) {
        Message<String> message = new Message<String>(Function.FIND_USER, name);
        String text = sendDatagram(message);
        RegisteredUser user = parser.fromJson(text, RegisteredUser.class);
        return user;
    }

    public Administrator findAdmin(String name) {
        Message<String> message = new Message<String>(Function.FIND_ADMIN, name);
        String text = sendDatagram(message);
        Administrator admin = parser.fromJson(text, Administrator.class);
        return admin;
    }

    public void addUser(RegisteredUser user) {
        Message<RegisteredUser> message = new Message<>(Function.ADD_USER, user);
        sendDatagram(message);
    }

    public void addAdmin(Administrator adm) {
        Message<Administrator> message = new Message<>(Function.ADD_ADMIN, adm);
        sendDatagram(message);
    }

    public List<Recipe> getAllRecipes() {
        Message<List<Recipe>> message = new Message<>(Function.GET_ALL_RECIPES, null);
        String text = sendDatagram(message);
        Type listOfMyClassObject = new TypeToken<ArrayList<Recipe>>() {}.getType();
        List<Recipe> list = parser.fromJson(text, listOfMyClassObject);
        return list;
    }

    public String showRecipes(List<Recipe> allRecipes) {
        Message<List<Recipe>> message = new Message<>(Function.SHOW_RECIPES, allRecipes);
        String text = sendDatagram(message);
        return text;
    }
public int addRecipe(Recipe r) {
        Message<Recipe> message = new Message<>(Function.CREATE_RECIPE, r);
        String text = sendDatagram(message);
        int id = parser.fromJson(text, Integer.class);
        return id;
    }

    public boolean removeRecipe(Recipe r) {
        Message<Recipe> message = new Message<>(Function.DELETE_RECIPE, r);
        String text = sendDatagram(message);
        boolean bool = parser.fromJson(text, Boolean.class);
        return bool;
    }

	

	public void close() {
		// TODO Auto-generated method stub

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

	public Set<Recipe> getRecipes() {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<Administrator> getAdminList() {
		// TODO Auto-generated method stub
		return null;
	}

	public void empty() {
		// TODO Auto-generated method stub

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

	public String showUserRecipes(RegisteredUser reg) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Recipe> getUserRecipes(RegisteredUser reg) {
		// TODO Auto-generated method stub
		return null;
	}
}
