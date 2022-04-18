
import com.mysql.cj.jdbc.Blob;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
    private void adminSearchBtnAction(ActionEvent event) throws Exception{
        Connection DBConnection = DataBaseConnection.connectDB();
        
        String searchNID = searchTF.getText();
        
        String query = "select * from nidInfo,vaccineInfo,nidPicture "
                + "where nidInfo.nidNumber = BINARY ?"
                + "and vaccineInfo.nidNumber = BINARY ? and nidPicture.nidNumber = BINARY ?";
        PreparedStatement statement = DBConnection.prepareStatement(query);
        statement.setString(1, searchNID);
        statement.setString(2, searchNID);
        statement.setString(3, searchNID);
        ResultSet result = statement.executeQuery();
        
        if(result.next()){
            Blob blob = (Blob) result.getBlob(16);
            InputStream inputStream = blob.getBinaryStream();
            Image image = new Image(inputStream);
            imageView.setImage(image);
            
            System.out.println("Done");
            
            infoLabel.setText("Name : "+result.getString("firstName")+" "+result.getString("lastName")+
                    "\nNID : "+result.getString("nidNumber")+" Gender : "+result.getString("gender")+
                    "\nAddress : "+result.getString("address")+"\nVaccine Center : "+result.getString("center")+
                    "\n1st Dose : "+result.getString("doseOneDate")+",  Vaccine : "+result.getString("doseOne")+
                    "\n2nd Dose : "+result.getString("doseTwoDate")+",  Vaccine : "+result.getString("doseTwo")); 
            
            searchTF.setText("");
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Covid 19 Vaccination System");
            alert.setHeaderText("Information Error");
            alert.setContentText("Information is not found !!!!");
            alert.showAndWait();
            
            searchTF.setText("");
            infoLabel.setText("");
            imageView.setImage(null);
        }
        DBConnection.close();
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
