package gson;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import recipe.Recipe;
import system.MySystem;
import system.RecipeExtended;
import system.RegisteredUserExtended;
import user.RegisteredUser;

import com.google.gson.Gson;
import com.google.gson.JsonParser;

import administrator.Administrator;

public class ServerSystem extends MySystem {
	
	public final static int RECIPES_PER_PAGE = 5;
	private Gson parser;

	public static void main(String[] args) {
		int port = 10; // puerto del servidor

		// SOCKET
		DatagramSocket server = null;
		ServerSystem system = new ServerSystem();
		system.parser = new Gson();

		// Crear e inicalizar el socket del servidor
		try {
			server = new DatagramSocket(port);
		} catch (SocketException e) {
			System.out.println(e.getMessage());
			System.exit(-1);
		}

		// Funcion PRINCIPAL del servidor
		while (true) {
			/*
			 * Crear e inicializar un datagrama VACIO para recibir la respuesta de maximo
			 * 500 bytes
			 */
			System.out.println("ESTADO: esperando datagramas");
			byte[] buffer = new byte[500];
			DatagramPacket receivedDatagram = new DatagramPacket(buffer, buffer.length);

			try {
				// Recibir datagrama
				server.receive(receivedDatagram);

				// Obtener texto recibido
				String line = new String(receivedDatagram.getData(), receivedDatagram.getOffset(),
						receivedDatagram.getLength(), StandardCharsets.UTF_8);
				
				Message<String> message = Message.getMessage(line);
				
				/*
				 * Mostrar por pantalla la direccion socket (IP y puerto) del cliente y su texto
				 */
				System.out.println("Direccion socket: " + receivedDatagram.getSocketAddress() + "\nTexto: " + line);

				DatagramPacket datagram = null;
				
				switch (message.getFunction()) {
				case CREATE_RECIPE:
                    line = system.createRecipe(message.getElement());
                    break;
                case DELETE_RECIPE:
                    line = system.deleteRecipe(message.getElement());
                    break;
                case FIND_USER:
                    line = system.parser.toJson(system.findUser(fromJson(message.getElement())), RegisteredUser.class);
                    break;
                case FIND_ADMIN:
                    line = system.parser.toJson(system.findAdmin(fromJson(message.getElement())), Administrator.class);
                    break;
                case ADD_USER:
                    line = system.addUser(message.getElement());
                    break;
                case ADD_ADMIN:
                    line = system.addAdmin(message.getElement());
                    break;
                case GET_ALL_RECIPES:
                    line = system.getAllRecipes(message.getElement());
                    break;
                case SHOW_RECIPES:
                    line = system.showRecipes(message.getElement());
				default:
					System.out.println("Not found");
					line = "Funcion no encontrada";
				}

				byte[] data = line.getBytes(StandardCharsets.UTF_8);
				datagram = new DatagramPacket(data, data.length, receivedDatagram.getSocketAddress());

				System.out.println("ESTADO: enviando datagrama de respuesta");

				// Enviar datagrama de respuesta
				try {
					server.send(datagram);
				} catch (IOException e) {
					System.out.println("ERROR: no se ha podido enviar el datagrama respuesta");
				}
			} catch (IOException e) {
				System.out.println("ERROR: al recibir un datagrama");
			}
		} // Fin del bucle del servicio

		// system.close();
	}

	private String createRecipe(String text) {
		String line = null;
		System.out.println("creating recipe...");

		Gson parser = new Gson();
		Recipe r = parser.fromJson(text, Recipe.class);
		RecipeExtended re = new RecipeExtended(r);
		
		this.addRecipe(re);
		System.out.println("recipe has been created");
		line = Integer.toString(re.getId());
		
		
		//TODO
		
//		String name = sp.next();
//		int idUser = sp.nextInt();
//		Recipe r = findRecipe(name);
//		if (r == null) {
//			r = new Recipe(name, getUser(idUser));
//			addRecipe(r);
//			line = "You created the recipe with id " + r.getId() + "\n" + r.toStringExtended();
//		} else {
//			line = "You cannot create this recipe because it has already been done";
//		}
				
		return line;
	}
	
	private static String fromJson(String text) {
		try {
			return text.replace("\"", "");
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private String showRecipes(String text) {
        System.out.println("showing recipes...");
        Gson parser = new Gson();
        String jsonString = parser.toJson(this.showRecipes(text));

        return jsonString;
    }

    private String getAllRecipes(String text) {
        System.out.println("getting all recipes...");
        Gson parser = new Gson();
        String jsonString = parser.toJson(this.getAllRecipes());

        return jsonString;
    }

    private String addUser(String text) {
        System.out.println("adding user...");
        Gson parser = new Gson();
        RegisteredUser u = parser.fromJson(text, RegisteredUser.class);
        RegisteredUserExtended reg = new RegisteredUserExtended(u);
        this.addUser(reg);

        return Integer.toString(reg.getId());
    }

    private String addAdmin(String text) {
        System.out.println("adding admin...");

        Gson parser = new Gson();
        Administrator r = parser.fromJson(text, Administrator.class);
        this.addAdmin(r);;
        return Integer.toString(r.getId());
    }

    private String deleteRecipe(String text) {
        System.out.println("deleting recipe...");

        Gson parser = new Gson();
        Recipe r = parser.fromJson(text, Recipe.class);
        RecipeExtended re = new RecipeExtended(r);

        String line = Boolean.toString(this.removeRecipe(re));
        System.out.println("recipe has been deleted");

        return line;
    }
	
	/*
	// No se como vamos a pasar la receta
	private String deleteRecipe(MyStringSplitter sp) {
		String line = null;
		System.out.println("deleting recipe...");
		int idRecipe = sp.nextInt();
		int idUser = sp.nextInt();
		Recipe r = getRecipe(idRecipe);
		if (r == null) {
			line = "The recipe doesn't exist";
		} else {
			if (r.getUser().getId() == idUser) {
				removeRecipe(r);
				line = "You succesfully deleted your own recipe";
			} else {
				line = "You cannot delete a recipe that is not yours";
			}
		}

		return line;
	}

	private String listOfRecipes(MyStringSplitter sp) {
		String line = null;
		System.out.println("sending the list of recipes...");

		int page = sp.nextInt();
		int idUser = sp.nextInt();
		boolean useFridge = sp.nextBoolean();
		boolean onlyFollowing = sp.nextBoolean();
		String name = sp.nextOrNull();
		
		List<Recipe> list = this.filterRecipes(idUser, useFridge, onlyFollowing, name);
				
		//we return all of this
		boolean prevPage;
		boolean nextPage;
		int numShown;
		int[] idRecipes;
		String recipes;
		
		//TODO complete this
		
		return line;
	}

	private String doSomethign(MyStringSplitter sp) {
		String line = null;
		System.out.println("doing something...");
		
		return line;
	}
	*/
}
