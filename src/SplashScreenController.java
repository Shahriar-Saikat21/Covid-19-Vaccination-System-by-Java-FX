import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class SplashScreenController implements Initializable {

    @FXML
    private ProgressBar progressBar;
    @FXML
    private Label progressLabel;
    @FXML
    private AnchorPane splashPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new SplashScreen().start();
        progressBar.setStyle("-fx-accent: #39b54a");
        progressLabel.setText("Loading ....");
    }
       
    class SplashScreen extends Thread{
        @Override
        public void run(){
            try {
                double progress = 0.0;
                while(progress<=1.0){
                    progressBar.setProgress(progress);
                    Thread.sleep(2000);
                    progress+=0.1;
                }        
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Parent root = null;
                        try {             
                            root = FXMLLoader.load(getClass().getResource("IntroPageFXML.fxml"));
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }

                        Scene openScene = new Scene(root);
                        Stage stage = new Stage();
                        stage.setScene(openScene);
                        Image appLogo = new Image("image/AppLogo.png");
                        stage.getIcons().add(appLogo);
                        stage.setTitle("Welcome to Covid-19 Vaccination Program");

                        openScene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

                        stage.setResizable(false);
                        stage.show();

                        splashPane.getScene().getWindow().hide(); 
                    }
                });               
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }   
}
