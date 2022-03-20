

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;


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
    private void registrationBtnAction(ActionEvent event) {
        System.out.println("Registration Working");
    }

    @FXML
    private void adminLogInBtnAction(ActionEvent event) {
        System.out.println("Admin Panel Working");
    }
    
}
