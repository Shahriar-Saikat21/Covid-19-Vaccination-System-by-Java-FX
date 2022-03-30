import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class RegistrationFXMLController implements Initializable {

    @FXML
    private Button backToHomeBtn;

    @FXML
    private BorderPane borderPane;

    @FXML
    private Button certificateBtn;

    @FXML
    private Button registrationBtn;

    @FXML
    private AnchorPane registrationPane;

    @FXML
    private Button vaccineCardBtn;
    @FXML
    private Label otpLabel;
    @FXML
    private TextField otpTextField;
    @FXML
    private Button sendOtpButton;
    @FXML
    private Button registerButton;
    @FXML
    private Label nidLabel;
    @FXML
    private Label dateOfBirthLabel;
    @FXML
    private ComboBox<String> dayComboBox;
    @FXML
    private ComboBox<String> monthComboBox;
    @FXML
    private ComboBox<String> yearComboBox;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dayComboBox.getItems().addAll("01","02","03","04","05","06","07","08","09","10","11","12","13","14","15");
        monthComboBox.getItems().addAll("January");
        yearComboBox.getItems().addAll("1920");
    }   
    
    @FXML
    void backToHomeBtnAction(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("IntroPageFXML.fxml"));             
        
        Scene introScene = new Scene(root);
        Stage stage = (Stage)backToHomeBtn.getScene().getWindow();
        stage.setScene(introScene);
        Image appLogo = new Image("image/AppLogo.png");
        stage.getIcons().add(appLogo);
        stage.setTitle("Welcome to Covid-19 Vaccination Program");
        introScene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.show();
    }

    @FXML
    void certificateBtnAction(MouseEvent event) {
        loadPage("CertificateFXML");
    }

    @FXML
    void registrationBtnAction(MouseEvent event) {
        borderPane.setCenter(registrationPane);
    }

    @FXML
    void vaccineCardBtnAction(MouseEvent event) {
        loadPage("VaccineCardFXML");
    }
    
    private void loadPage(String pageName){
        Parent root = null;
        try{
        root = FXMLLoader.load(getClass().getResource(pageName+".fxml"));
        }catch(Exception e){
            e.printStackTrace();
        }             
        borderPane.setCenter(root);
    }   

    @FXML
    private void registrationSendOtpAction(ActionEvent event) {
    }

    @FXML
    private void completeRegistrationButton(ActionEvent event) {
    }
}
