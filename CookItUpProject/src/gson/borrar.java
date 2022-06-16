package gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import administrator.Administrator;
import recipe.Comment;
import recipe.Ingredient;
import recipe.Question;
import recipe.Recipe;
import recipe.Review;
import recipe.Step;
import report.Report;
import system.MySystem;
import user.RegisteredUser;

public class borrar {

	public static void main(String[] args) {
		MySystem system = new MySystem();
		List<Administrator> input = new ArrayList<Administrator>(system.getAdminList());
		System.out.println(input);
		
		Type listOfMyClassObject = new TypeToken<ArrayList<Administrator>>() {}.getType();
		
		Gson gson = new Gson();
	    
	    String string = gson.toJson(input, listOfMyClassObject);
	    System.out.println(string);
	    
	    List<Administrator> output = gson.fromJson(string, listOfMyClassObject);
	    System.out.println(output);
		
//		List<SimpleClassBorrar> input = new LinkedList<>();
//		input.add(new SimpleClassBorrar(0)); input.add(new SimpleClassBorrar(10));
//		System.out.println(input);
//		
//		Type listOfMyClassObject = new TypeToken<LinkedList<SimpleClassBorrar>>() {}.getType();
//
//	    Gson gson = new Gson();
//	    
//	    String string = gson.toJson(input, listOfMyClassObject);
//	    System.out.println(string);
//	    
//	    List<SimpleClassBorrar> outputList = gson.fromJson(string, listOfMyClassObject);
//	    System.out.println(outputList);
		
//		MySystem system = new MySystem();
//		RegisteredUser user = system.getDefaultUser();
//		System.out.println(user.getClass());
//		Recipe r = new Recipe("patata", user);
//		Comment c = new Comment(system.getUser(0), "hola", r);
//		
//		List<Comment> input = new LinkedList<>();
//		input.add(c);
//		System.out.println(input);
//		
//		Type listOfMyClassObject = new TypeToken<LinkedList<Comment>>() {}.getType();
//
//	    Gson gson = new Gson();
//	    
//	    String string = gson.toJson(input, listOfMyClassObject);
//	    System.out.println(string);
//	    
//	    List<Comment> outputList = gson.fromJson(string, listOfMyClassObject);
//	    System.out.println(outputList);
	}

}
