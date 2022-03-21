import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;


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
    private ComboBox<?> doseOneComboBox;
    @FXML
    private Label dateOneLabel;
    @FXML
    private DatePicker datePickerOne;
    @FXML
    private Label doseTwoLabel;
    @FXML
    private ComboBox<?> doseTwoComboBox;
    @FXML
    private Label dateTwoLabel;
    @FXML
    private DatePicker datePickerTwo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void adminSearchBtnAction(ActionEvent event) {
    }

    @FXML
    private void adminUpdateBtnAction(ActionEvent event) {
    }

    @FXML
    private void adminLogoutBtnAction(ActionEvent event) {
    }
    
}
