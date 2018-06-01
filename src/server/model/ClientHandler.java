package server.model;

import java.io.*;
import java.net.Socket;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

public class ClientHandler implements Runnable {
	private Socket socket;
	private InputStreamReader inFromClient;
	private BufferedReader reader;
	private PrintWriter client;
	
	public ClientHandler(Socket socket, PrintWriter user) {
		this.socket = socket;
		client = user;
		
	}

	@Override
	public void run() {
		
		try {
			inFromClient = new InputStreamReader(socket.getInputStream());
			reader = new BufferedReader(inFromClient);
			Server.getClientOutputStreams().add(client);
			String message, connect = "Connect", disconnect = "Disconnect", chat = "Chat";
			String[] data;
			while ((message = reader.readLine()) != null) {
				// outputPane.append("Received: " + message + "\n");
				data = message.split(":");
				for (String token : data) {
					// outputPane.append
					System.out.println(token + "\n");
				}
				if (data[2].equals(connect)) {
					tellEveryone(getTime() + (data[0] + ":" + data[1] + ":" + chat));
					userAdd(data[0]);
					System.out.println(Server.onlineUsers);
				} else if (data[2].equals(disconnect)) {
					tellEveryone(getTime() + (data[0] + ":has disconnected." + ":" + chat));
					userRemove(data[0]);
				} else if (data[2].equals(chat)) {
					tellEveryone(getTime() + message);
				} else {
					// outputPane.append("No Conditions were met. \n");
				}
			} // end while
		} // end try
		catch (Exception ex) {
			// outputPane.append("Lost a connection. \n");
			ex.printStackTrace();
			Server.clientOutputStreams.remove(client);
		} // end catch

	} // end run()

	public void userAdd(String data) {
		String message, add = ": :Connect", done = "Server: :Done", name = data;
		// outputPane.append("Before " + name + " added. \n");
		Server.onlineUsers.add(name);
		// outputPane.append
		System.out.println(Server.onlineUsers);
		String[] tempList = new String[(Server.onlineUsers.size())];
		Server.onlineUsers.toArray(tempList);

		for (String token : tempList) {

			message = (token + add);
			tellEveryone(message);
		}
		tellEveryone(done);
	}

	public void userRemove(String data) {
		String message, add = ": :Connect", done = "Server: :Done", name = data;
		Server.onlineUsers.remove(name);
		String[] tempList = new String[(Server.onlineUsers.size())];
		Server.onlineUsers.toArray(tempList);

		for (String token : tempList) {

			message = (token + add);
			tellEveryone(message);
		}
		tellEveryone(done);
	}

	public void tellEveryone(String message) {
		// sends message to everyone connected to server
		Iterator<PrintWriter> it = Server.getClientOutputStreams().iterator();

		while (it.hasNext()) {
			try {
				PrintWriter writer = it.next();
				writer.println(message);
				// outputPane.append("Sending: " + message + "\n");
				writer.flush();
				// outputPane.setCaretPosition(outputPane.getDocument().getLength());

			} // end try
			catch (Exception ex) {
				// outputPane.append("Error telling everyone. \n");
			} // end catch
		} // end while
	} // end tellEveryone()

	private String getTime() {
		LocalTime localTime = LocalTime.now();
		String time = localTime.format(DateTimeFormatter.ofPattern("HH.mm.ss")) + " ";
		return time;
	}

}