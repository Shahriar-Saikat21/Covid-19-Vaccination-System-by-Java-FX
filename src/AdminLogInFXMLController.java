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
import java.sql.*;
import javafx.scene.control.Alert;

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
        //To Do
        
    }

    String otpSentByMail = "";
    
    @FXML
    private void adminLogInOTPAction(ActionEvent event) throws Exception{                
        Connection DBConnection = DataBaseConnection.connectDB();
        
        String userName = userNameTF.getText();
        String userPassword = passwordTF.getText();
        
        String query = "select mail from adminUsers where userName = BINARY ? and password = BINARY ?";
        PreparedStatement statement = DBConnection.prepareStatement(query);
        statement.setString(1, userName);
        statement.setString(2, userPassword);
        ResultSet result = statement.executeQuery();
        
        if(result.next()==false){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Covid 19 Vaccination System");
            alert.setHeaderText("Log In Error");
            alert.setContentText("Invalid Username or Password !!");
            alert.showAndWait();
            otpSentByMail = "";
            userNameTF.setText("");
            passwordTF.setText("");
        }else{
            OTP otpgenerate = new OTP(result.getString("mail"));
            otpSentByMail = otpgenerate.sendOTP();
        }
    }

    @FXML
    private void adminLogInAction(ActionEvent event) throws Exception{ 
        
        String otpEnterByUser = otpTF.getText();
        if(otpEnterByUser.equals(otpSentByMail) && otpEnterByUser !=""){
            Parent root = FXMLLoader.load(getClass().getResource("AdminPanelFXML.fxml"));             

            Scene adminPanelScene = new Scene(root);
            Stage stage = (Stage)logInButton.getScene().getWindow();
            stage.setScene(adminPanelScene);
            Image appLogo = new Image("image/AppLogo.png");
            stage.getIcons().add(appLogo);
            stage.setTitle("Admin Panel");
            adminPanelScene.getStylesheets().add(getClass().getResource("adminPanelStyle.css").toExternalForm());
            stage.show();
        }else{            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Covid 19 Vaccination System");
            alert.setHeaderText("Log In Error");
            alert.setContentText("Invalid OTP !!");
            alert.showAndWait();
        }       
               
        userNameTF.setText("");
        passwordTF.setText("");
        otpTF.setText("");
        otpSentByMail = "";
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
