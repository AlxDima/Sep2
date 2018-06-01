package CheckersFin;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CheckersMain extends Application {
   

    
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(Board.createContent());
        primaryStage.setTitle("CheckersApp");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    



	public static void main(String[] args) {
        launch(args);
    }
}