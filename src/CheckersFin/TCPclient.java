package CheckersFin;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;



public class TCPclient {
	
	public static void main(String[] args)
	{
		
		connect("192.36.51.207", 2720);
	}

	private static Socket socket;

    private static ObjectInputStream ois;

    private static ObjectOutputStream oos;

    public static void connect(String address, int port) {
        try {
            socket = new Socket(address, port);
            ois = new ObjectInputStream(socket.getInputStream());
            oos = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            System.out.println("Error while connecting to com.bestsoft.checkers.server");
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
  public Board listen() {
  Board checkers = null;
  boolean whiteTurn = true;
  while (whiteTurn) {
	  try {
          checkers = (Board) ois.readObject();
          whiteTurn = false;
          } catch (IOException e) {
          whiteTurn = false;
          disconnect();
          System.out.println("Error while listening to server");
          JOptionPane.showMessageDialog(null, e.getMessage());
      } catch (ClassNotFoundException e) {
          System.out.println("Error while listening to server");
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
    System.out.println("Error while sending information to server");
    JOptionPane.showMessageDialog(null, ex.getMessage());
  		}
  }

  		public void disconnect() {
  			try {
    	ois.close();
    	oos.close();
    	socket.close();
		} catch (IOException e) {
			System.out.println("Error while disconecting client");
			 JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
  		
}

	
