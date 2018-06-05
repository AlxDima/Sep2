package test;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import Data.Insert_into;

import java.lang.System;

public class TestControler
{
   // system

   @FXML
   private TabPane outputPanel;
   @FXML
   private AnchorPane gamePanel;
   @FXML
   private AnchorPane chatPanel;
   @FXML
   private AnchorPane administartorPanel;
   @FXML
   private Tab chatClient;
   @FXML
   private Tab gameClient;
   @FXML
   private Tab administratorClient;
   @FXML
   private Pane System;
   // chat label + text fields + buttons + list view
   @FXML
   private Label lblUsername;
   @FXML
   private Label lblOnlineUser;
   @FXML
   private Button btnConnect;
   @FXML
   private Button btnDisconnect;
   @FXML
   private Button btnSend;
   @FXML
   private TextField txtFieldUsername;
   @FXML
   private ListView listText11;
   @FXML
   private ListView listText12;
   @FXML
   private TextArea listText13;
   // game label + text fields + buttons + listview
   @FXML
   private Label lblOnlineUsers;
   @FXML
   private Label lblAvailableGame;
   @FXML
   private Button btnCreateGame;
   @FXML
   private Button btnJoin;
   @FXML
   private Button btnResign;
   @FXML
   private Button btnSend2;
   @FXML
   private Button btnViewStat;
   @FXML
   private ListView listText23;
   @FXML
   private ListView listText22;
   @FXML
   private ListView listText21;
   @FXML
   private TextArea listText24;
   @FXML
   private ListView listText25;
   @FXML
   private Pane panelGame;
   // administrator label + text fields + buttons + listview
   @FXML
   private Label lblUserList;
   @FXML
   private Label lblChatView;
   @FXML
   private Label lblUserStatistic;
   @FXML
   private Label lblDailyLog;
   @FXML
   private Label lblUserAlias;
   @FXML
   private Label lblUserId;
   @FXML
   private Label lblUserName;
   @FXML
   private Button btnCreateUser;
   @FXML
   private Button btnRemoveUser;
   @FXML
   private Button btnShowUsers;
   @FXML
   private Button btnViewLog;
   @FXML
   private Button btnViewStatistic;
   @FXML
   private TextField txtField31;
   @FXML
   private TextField txtField32;
   @FXML
   private TextField txtField33;
   @FXML
   private TextArea listText34;
   @FXML
   private ListView listText31;
   @FXML
   private ListView listText33;
   @FXML
   private ListView listText32;
   // arrays to store strings in listText1
   private String[] tempSelected;
   private String selectedItemInList;
   Insert_into db;

   //

   @FXML
   public void initialize()
   {
      // change listener for users listText31
      db = new Insert_into();
      listText31.getSelectionModel().selectedItemProperty()
            .addListener(new ChangeListener()
            {
               @Override
               public void changed(ObservableValue observable, Object oldValue,
                     Object newValue)
               {
                  if (newValue != null)
                  {
                     selectedItemInList = (String) listText31
                           .getSelectionModel().getSelectedItem();
                     tempSelected = selectedItemInList.split(",");

                  }
                  txtField31.setText(tempSelected[0]);
                  txtField32.setText(tempSelected[1]);
                  txtField33.setText(tempSelected[2]);

               }
            });
   }

   // event listeners to the chat buttons
   @FXML
   void connectUser(ActionEvent event)
   {

   }

   @FXML
   void disconnectUser(ActionEvent event)
   {

   }

   // event listeners to the game buttons
   @FXML
   void createGame(ActionEvent event)
   {

   }

   @FXML
   void joinGame(ActionEvent event)
   {

   }

   @FXML
   void resignGame(ActionEvent event)
   {

   }

   @FXML
   void viewStatistic(ActionEvent event)
   {

   }

   @FXML
   void sendMessage(ActionEvent event)
   {

   }
// event listeners to the administrator buttons
   @FXML
   void createUser(ActionEvent event)
   {
      String tmpId = txtField31.getText();
      String tmpName = txtField32.getText();
      String tmpAlias = txtField33.getText();

      String userInfo = String.format("%s, %s, %s", tmpId, tmpName, tmpAlias);

      try (FileWriter fw = new FileWriter("users.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out1 = new PrintWriter(bw))
      {
         out1.println(userInfo);

      }
      catch (IOException e)
      {
         // System.out.println("Exception ! ");
      }

      db.addUser(tmpId, tmpName, tmpAlias);

   }

   @FXML
   void removeUser(ActionEvent event) throws IOException
   {

      String userInfo = (String) listText31.getSelectionModel()
            .getSelectedItem();
      // System.out.println(userInfo);

      try
      {
         FileReader reader = new FileReader("users.txt");
         Scanner s = new Scanner(reader);
         String read;

         ArrayList<String> items = new ArrayList<String>();
         while (s.hasNextLine())
         {
            read = s.nextLine();
            if (!(userInfo.equals(read)))
            {
               items.add(read);
            }
         }
         try (FileWriter fw = new FileWriter("users.txt", false);
               BufferedWriter bw = new BufferedWriter(fw);
               PrintWriter out1 = new PrintWriter(bw))
         {
            for (String a : items)
            {
               out1.println(a);
            }
         }
         catch (IOException e)
         {
            // System.out.println("Exception ");
         }
      }
      catch (FileNotFoundException e)
      {

         e.printStackTrace();
      }
   }

   @FXML
   void ShowUsers(ActionEvent event) throws FileNotFoundException
   {
      listText31.getItems().clear();
      int i = 0;
      while (i < db.showUser().size())
      { listText31.getItems().add(  db.showUser().get(i));
        i++;
      }
   }

   @FXML
   void viewUserStatistic(ActionEvent event)
   {

   }

   @FXML
   void viewDailyLog(ActionEvent event)
   {

   }

}
