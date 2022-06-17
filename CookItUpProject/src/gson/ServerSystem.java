package gson;

//import statements
import java.net.*;

import com.google.gson.Gson;

import administrator.Administrator;
import system.MySystem;
import user.RegisteredUser;

import java.io.*;

public class ServerSystem extends Thread {
	
	private MySystem system;
	private ServerSocket srvr = null;
	private static final int port = 21;
	private boolean running = true;
	
	public ServerSystem(MySystem system) {
		this.system = system;
	}
	
	public ServerSystem() {
		this(new MySystem());
	}

	public static void main(String[] args) throws InterruptedException {
		Thread t = new Thread(new ServerSystem());
		t.start();
		t.join();
	}

	@Override
	public void run() {
		//create a server socket
		try {
			srvr = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (srvr == null) {
			System.err.println("Couldn't start the server");
			return;
		}
		System.out.println("Server starts");
		
		//accept connections while running
		running = true;
		while(running) {
			try {
				ServerThread t = new ServerThread(system, this, srvr.accept());
				t.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//close the server
		try {
			srvr.close();
			System.out.println("Server closed");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void exit() {
		running = false;
	}
}