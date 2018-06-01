package server.model;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Server implements ServerIF {
	private static ServerSocket listeningSocket;
	private static final int PORT = 5000;
	String message;
	
	static ArrayList<String> onlineUsers;
	static ArrayList<PrintWriter> clientOutputStreams;

	// create server socket listening at the given port.
	public Server() {
		try {
			setClientOutputStreams(new ArrayList<PrintWriter>());
			onlineUsers = new ArrayList<String>();

			// Step 1: Creating the server welcoming socket
			listeningSocket = new ServerSocket(PORT);
			System.out.println("ServerSocket: " + listeningSocket);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see domain.server.ServerIF#listenToClient()
	 */
	@Override
	public void run() {
		Socket connSocket = null;
		while (true) { // run until you terminate the program
			try {
				// Block until a connection is made.
				connSocket = listeningSocket.accept();
				System.out.println("Socket: " + connSocket);
				// Start a new thread for each client
				PrintWriter writer = new PrintWriter(connSocket.getOutputStream());
				getClientOutputStreams().add(writer);

				Thread ct = new Thread(new ClientHandler(connSocket, writer));
				ct.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


	public static ArrayList<PrintWriter> getClientOutputStreams() {
		return clientOutputStreams;
	}

	/* (non-Javadoc)
	 * @see domain.server.ServerIF#setClientOutputStreams(java.util.ArrayList)
	 */
	@Override
	public void setClientOutputStreams(ArrayList<PrintWriter> clientOutputStreams) {
		Server.clientOutputStreams = clientOutputStreams;
	}

	
}