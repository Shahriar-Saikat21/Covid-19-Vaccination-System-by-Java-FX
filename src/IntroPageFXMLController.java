import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class IntroPageFXMLController implements Initializable {

    @FXML
    private Button registrationBtn;
    @FXML
    private Button adminLogInBtn;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void registrationBtnAction(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("RegistrationFXML.fxml"));
               
        Scene registrationScene = new Scene(root);
        Stage stage = (Stage) adminLogInBtn.getScene().getWindow();
        stage.setScene(registrationScene);
        Image appLogo = new Image("image/AppLogo.png");
        stage.getIcons().add(appLogo);
        stage.setTitle("User Page");
        registrationScene.getStylesheets().add(getClass().getResource("registrationPageStyle.css").toExternalForm());
        stage.show();
    }

    @FXML
    private void adminLogInBtnAction(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("AdminLogInFXML.fxml"));
               
        Scene adminLogInScene = new Scene(root);
        Stage stage = (Stage) adminLogInBtn.getScene().getWindow();
        stage.setScene(adminLogInScene);
        Image appLogo = new Image("image/AppLogo.png");
        stage.getIcons().add(appLogo);
        stage.setTitle("Admin Log In Page");
        adminLogInScene.getStylesheets().add(getClass().getResource("adminLogInPageStyle.css").toExternalForm());
        stage.show();        
    }   
}
