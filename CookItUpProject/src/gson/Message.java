package gson;

import java.lang.reflect.Type;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class  Message<E> {
	private Function function;
	private E element;
	
	public Message(Function function, E element) {
		this.function = function;
		this.element = element;
	}
	
	public static Message<String> getMessage(String text) {
		Scanner scan = new Scanner(text);
		scan.useDelimiter("\t");
		Gson gson = new Gson();
		Function function = gson.fromJson(scan.next(), Function.class);
		String string = scan.next();
		scan.close();
		return new Message<String>(function, string);
	}

	@Override
	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(function) +
				"\t" +
				gson.toJson(element);
	}

	public Function getFunction() {
		return function;
	}

	public E getElement() {
		return element;
	}

}
