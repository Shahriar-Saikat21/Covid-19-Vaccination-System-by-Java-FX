import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


public class AdminPanelFxmlController implements Initializable {

    @FXML
    private Label searchLabel;
    @FXML
    private TextField searchTF;
    @FXML
    private Button searchButton;
    @FXML
    private Label infoLabel;
    @FXML
    private ImageView imageView;
    @FXML
    private Button updateButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Label doseOneLabel;
    @FXML
    private ComboBox<String> doseOneComboBox;
    @FXML
    private Label dateOneLabel;
    @FXML
    private DatePicker datePickerOne;
    @FXML
    private Label doseTwoLabel;
    @FXML
    private ComboBox<String> doseTwoComboBox;
    @FXML
    private Label dateTwoLabel;
    @FXML
    private DatePicker datePickerTwo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        infoLabel.setText("");
        
        doseOneComboBox.getItems().addAll("Moderna","Pfizer","AstraZeneca","Sinopharm","Janssen");
        doseTwoComboBox.getItems().addAll("Moderna","Pfizer","AstraZeneca","Sinopharm","Janssen");
    }    

    @FXML
    private void adminSearchBtnAction(ActionEvent event) {
        System.out.println("Search Working");
    }

    @FXML
    private void adminUpdateBtnAction(ActionEvent event) {
        System.out.println("Update Working");
    }

    @FXML
    private void adminLogoutBtnAction(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("IntroPageFXML.fxml"));             
        
        Scene introScene = new Scene(root);
        Stage stage = (Stage)logoutButton.getScene().getWindow();
        stage.setScene(introScene);
        Image appLogo = new Image("image/AppLogo.png");
        stage.getIcons().add(appLogo);
        stage.setTitle("Welcome to Covid-19 Vaccination Program");
        introScene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.show();
    } 
}
