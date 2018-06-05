package client.controller;

import client.model.Client;
import client.view.ClientView;

public class ClientController
{

   public static void main(String[] args)
   {
startGui();
      
      
   }

   private static void startGui() {
      ClientController xxx =new ClientController();
      ClientView gui = new ClientView(xxx);
      Thread startGui = new Thread((Runnable) gui);
      startGui.start();
   }

   public  void startServer() {
      Client client =new Client();
      Thread clientStart = new Thread(client);
      clientStart.start();
   }
   
}
