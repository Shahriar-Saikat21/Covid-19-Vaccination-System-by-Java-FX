import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AdminLogInFXMLController implements Initializable{

    @FXML
    private Label adminLogInLabel;

    @FXML
    private TextField userNameTF;

    @FXML
    private Label passwordLabel;

    @FXML
    private PasswordField passwordTF;

    @FXML
    private Button otpButton;

    @FXML
    private Label adminLogInOTPLabel;
    
    @FXML
    private TextField otpTF;
    
    @FXML
    private Button logInButton;
    
    @FXML
    private Button adminLogInBackButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    private void adminLogInOTPAction(ActionEvent event) {
        System.out.println("OTP Working");
    }

    @FXML
    private void adminLogInAction(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("AdminPanelFXML.fxml"));             
        
        Scene adminPanelScene = new Scene(root);
        Stage stage = (Stage)logInButton.getScene().getWindow();
        stage.setScene(adminPanelScene);
        Image appLogo = new Image("image/AppLogo.png");
        stage.getIcons().add(appLogo);
        stage.setTitle("Admin Panel");
        adminPanelScene.getStylesheets().add(getClass().getResource("adminPanelStyle.css").toExternalForm());
        stage.show();
    }

    @FXML
    private void adminLogInBackAction(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("IntroPageFXML.fxml"));             
        
        Scene introScene = new Scene(root);
        Stage stage = (Stage)adminLogInBackButton.getScene().getWindow();
        stage.setScene(introScene);
        Image appLogo = new Image("image/AppLogo.png");
        stage.getIcons().add(appLogo);
        stage.setTitle("Welcome to Covid-19 Vaccination Program");
        introScene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.show();
    }
}
