import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class CertificateFXMLController implements Initializable{

    @FXML
    private Label certificateDateofBirth;

    @FXML
    private Label certificateEmailAddress;

    @FXML
    private Label certificateOTP;

    @FXML
    private Label certificateYourNID;

    @FXML
    private Button certificatesentOTPbtn;

    @FXML
    private Button sendcertificatebtn;
    
    @FXML
    private ComboBox<String> comboboxdate;

    @FXML
    private ComboBox<String> comboboxmonth;

    @FXML
    private ComboBox<String> comboboxyear;

    @FXML
    void certificatesentOTPAction(ActionEvent event) {

    }

    @FXML
    void sendcertificateAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboboxdate.getItems().addAll("01");
        comboboxmonth.getItems().addAll("January");
        comboboxyear.getItems().addAll("1920");
    }

}

