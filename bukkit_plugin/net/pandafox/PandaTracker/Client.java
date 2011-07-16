package net.pandafox.PandaTracker;

import java.io.*;
import java.net.*;

public class Client implements Runnable {
	Socket requestSocket;
	ObjectOutputStream out;
	ObjectInputStream in;
	String message;

	Client() {

	}

	public void run() {
		try {
			// for example new Socket("example.com", 1337)
			requestSocket = new Socket("YOUR HOST NAME GOES HERE", 1337); 
			System.out.println("Connected to server!");

			out = new ObjectOutputStream(requestSocket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(requestSocket.getInputStream());

			do {
				try{
					message = (String)in.readObject();
					System.out.println("From server: " + message);
				} catch(ClassNotFoundException classNot){
					System.err.println("data received in unknown format");
				}

				} while(!message.equals("bye"));

			} catch(UnknownHostException unknownHost) {
				System.err.println("You are trying to connect to an unknown host!");
			} catch(IOException ioException) {
				ioException.printStackTrace();
			} finally {
				try {
					in.close();
					out.close();
					requestSocket.close();
				} catch(IOException ioException) {
				ioException.printStackTrace();
			}
		}
	}

	void sendLocation(String player, double x, double y, double z) {
		try{
			out.writeObject(player + " " + x + " " + y + " " + z + "\n");
			out.flush();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}