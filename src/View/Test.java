package View;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Test extends Application
{


   @Override
   public void start(Stage stage) throws Exception
   {
      // initiate and invoke the fxml loader
      FXMLLoader loader= new FXMLLoader();
      //set controller
      loader.setController(new TestControler());
      
      //set location of the fxml doc
      loader.setLocation(getClass().getResource("Test.fxml"));
      Parent root= loader.load();
            
      //Build the scene graph
      Scene scene = new Scene(root);
      
      //show the window using the scene graph
      stage.setTitle("Test");
      stage.setScene(scene);
      stage.show();
     
   }

   public static void main(String[] args)
   {
      launch(args);

   }

}
