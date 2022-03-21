
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class MainClass extends  Application{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("IntroPageFXML.fxml"));             
        
        Scene introScene = new Scene(root);
        stage.setScene(introScene);
        Image appLogo = new Image("image/AppLogo.png");
        stage.getIcons().add(appLogo);
        stage.setTitle("Welcome to Covid-19 Vaccination Program");
        stage.setResizable(false);
        stage.show();       
        
    }
    
}
