import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class MainClass extends  Application{   
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("SplashScreenFXML.fxml"));             
        
        Scene introScene = new Scene(root);
        stage.setScene(introScene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();       
        
    }
    
}
