package CheckersFin;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;

public class TCPserver implements Runnable {
	
	 private int serverPort;
	 private static int serverSocketTimeout;

	    private static ServerSocket ss;

	    private static Socket socket;

	    private static ObjectOutputStream oos;

	    private static ObjectInputStream ois;
	    
	    public static void main(String[] args)
		{
			
			createServer(2720);
		}
	   
	  
	    
	    public static void createServer(int port) {
	        try {
	            ss = new ServerSocket(port);
	            ss.setSoTimeout(serverSocketTimeout);
	            socket = ss.accept();
	            oos = new ObjectOutputStream(socket.getOutputStream());
	            ois = new ObjectInputStream(socket.getInputStream());
	        } catch (IOException e) {
	            System.out.println("Error while creating server.");
	            JOptionPane.showMessageDialog(null, e.getMessage());
	        }
	    }
	 

	    public Board listen() {
	    	        boolean whiteTurn = true;
	        Board checkers = null;
	        while (whiteTurn) {
	            try {
	                checkers = (Board) ois.readObject();
	                whiteTurn = false;
	            } catch (IOException e) {
	                whiteTurn = false;
	                disconnect();
	                System.out.println("Error while listening to clients");
	                JOptionPane.showMessageDialog(null, e.getMessage());
	            } catch (ClassNotFoundException e) {
	                System.out.println("Error while listening to clients");
	                JOptionPane.showMessageDialog(null, e.getMessage());
	            }
	
	        }
	        return checkers;
	    }
	
	    public void send(Board checkers) {
	        try {
	    	            oos.writeObject(checkers);
	            oos.flush();
	        } catch (IOException ex) {
	            JOptionPane.showMessageDialog(null, ex.getMessage());
	        }
	    }
	
	    public void run() {
	        try {
	            ss = new ServerSocket(serverPort);
	            socket = ss.accept();
	            oos = new ObjectOutputStream(socket.getOutputStream());
	            ois = new ObjectInputStream(socket.getInputStream());
	
	        } catch (IOException ex) {
	            System.out.println("Error");
	            JOptionPane.showMessageDialog(null, ex.getMessage());
	        }
	    }
	
	    public void disconnect() {
	        try {
	            oos.close();
	            ois.close();
	            socket.close();
	            ss.close();
	        } catch (IOException e) {
	            System.out.println("Could not disconnect");
	        }
	    }
	
}


