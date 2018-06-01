package server.controler;

import server.model.Server;
import server.model.ServerIF;
import server.view.ServerView;
import server.view.ServerViewIF;

public class controler {


	public static void main(String[] args) {
		//	startServer();
		startGui();
		
		
	}

	private static void startGui() {
		controler ctrl =new controler();
		ServerViewIF gui = new ServerView(ctrl);
		Thread startGui = new Thread(gui);
		startGui.start();
	}

	public  void startServer() {
		ServerIF server =new Server();
		Thread serverStart = new Thread(server);
		serverStart.start();
	}
	
}
