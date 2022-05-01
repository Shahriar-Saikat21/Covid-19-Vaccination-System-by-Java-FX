
import com.mysql.cj.jdbc.Blob;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
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
    private DatePicker datePickerTwo;

    @FXML
    private ComboBox<String> doseNumberComboBox;

    @FXML
    private Label doseOneDetailLabel;

    @FXML
    private Label doseOneLabel;

    @FXML
    private ComboBox<String> doseTwoComboBox;

    @FXML
    private Label doseTwoDetailLabel;

    @FXML
    private Label doseTwoLabel;

    @FXML
    private ImageView imageView;

    @FXML
    private Label infoLabel;

    @FXML
    private Button logoutButton;

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchTF;

    @FXML
    private Button updateButton;

    @FXML
    private Label updateVaxInfoLabel;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        infoLabel.setText("");
        doseOneDetailLabel.setText("");
        doseTwoDetailLabel.setText("");
        
        doseNumberComboBox.getItems().addAll("Dose One","Dose Two","Dose Number");
        doseTwoComboBox.getItems().addAll("Moderna","Pfizer","AstraZeneca","Sinopharm","Janssen","Select Vaccine");
    }    

    String searchNID = "";
    String doseOneDate = "";
    String doseOneVaccine = "";
    String doseTwoDate = "";
    String doseTwoVaccine = "";
    
    @FXML
    private void adminSearchBtnAction(ActionEvent event){
        try {
            Connection DBConnection = DataBaseConnection.connectDB();
        
            searchNID = searchTF.getText();

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

                doseOneDate = result.getString("doseOneDate");
                doseTwoDate = result.getString("doseTwoDate");
                doseOneVaccine = result.getString("doseOne");
                doseTwoVaccine = result.getString("doseTwo");

                infoLabel.setText("Name : "+result.getString("firstName")+" "+result.getString("lastName")+
                        "\nNID : "+result.getString("nidNumber")+"\nGender : "+result.getString("gender")+
                        "\nAddress : "+result.getString("address")+"\nVaccine Center : "+result.getString("center")); 

                doseOneDetailLabel.setText("Date : "+result.getString("doseOneDate")+",  Vaccine : "+result.getString("doseOne"));
                doseTwoDetailLabel.setText("Date : "+result.getString("doseTwoDate")+",  Vaccine : "+result.getString("doseTwo"));

                searchTF.setText("");
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Covid 19 Vaccination System");
                alert.setHeaderText("Information Error");
                alert.setContentText("Information is not found !!!!");
                alert.showAndWait();

                searchNID = "";
                doseOneDate = "";
                doseOneVaccine = "";
                doseTwoDate = "";
                doseTwoVaccine = "";
                searchTF.setText("");
                infoLabel.setText("");
                imageView.setImage(null);
                doseNumberComboBox.setValue("Dose Number");
                doseOneDetailLabel.setText("");
                doseTwoDetailLabel.setText("");
                datePickerTwo.setValue(null);
                doseTwoComboBox.setValue("Select Vaccine");
            }
            DBConnection.close();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Covid 19 Vaccination System");
            alert.setHeaderText("Error !!!");
            alert.setContentText("Something is going wrong !!");
            alert.showAndWait();
            
            searchNID = "";
            doseOneDate = "";
            doseOneVaccine = "";
            doseTwoDate = "";
            doseTwoVaccine = "";
            searchTF.setText("");
            infoLabel.setText("");
            imageView.setImage(null);
            doseNumberComboBox.setValue("Dose Number");
            doseOneDetailLabel.setText("");
            doseTwoDetailLabel.setText("");
            datePickerTwo.setValue(null);
            doseTwoComboBox.setValue("Select Vaccine");
        }
        
    }

    @FXML
    private void adminUpdateBtnAction(ActionEvent event) {
        try {
            Connection DBConnection = DataBaseConnection.connectDB();

            LocalDate date = datePickerTwo.getValue();
            String doseVaule = doseNumberComboBox.getValue();

            if(doseVaule.equals("Dose One")){
                doseOneDate = date.toString();
                doseOneVaccine = doseTwoComboBox.getValue();
            }else{
                doseTwoDate = date.toString();
                doseTwoVaccine = doseTwoComboBox.getValue();
            }        

            String query = "UPDATE vaccineInfo SET doseOne=?,doseTwo=?,"
                    + "doseOneDate=?,doseTwoDate=? WHERE nidNumber=?";

            PreparedStatement statement = DBConnection.prepareStatement(query);
            statement.setString(1, doseOneVaccine);
            statement.setString(2, doseTwoVaccine);
            statement.setString(3, doseOneDate);
            statement.setString(4, doseTwoDate);
            statement.setString(5, searchNID);

            statement.executeUpdate();
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Covid 19 Vaccination System");
            alert.setHeaderText("Update Successfull");
            alert.setContentText("Information is updated");
            alert.showAndWait();
            
            DBConnection.close();
            
            searchNID = "";
            doseOneDate = "";
            doseOneVaccine = "";
            doseTwoDate = "";
            doseTwoVaccine = "";
            searchTF.setText("");
            infoLabel.setText("");
            imageView.setImage(null);
            doseNumberComboBox.setValue("Dose Number");
            doseOneDetailLabel.setText("");
            doseTwoDetailLabel.setText("");
            datePickerTwo.setValue(null);
            doseTwoComboBox.setValue("Select Vaccine");
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Covid 19 Vaccination System");
            alert.setHeaderText("Update Error !!!");
            alert.setContentText("Something is going wrong !!");
            alert.showAndWait();
            
            searchNID = "";
            doseOneDate = "";
            doseOneVaccine = "";
            doseTwoDate = "";
            doseTwoVaccine = "";
            searchTF.setText("");
            infoLabel.setText("");
            imageView.setImage(null);
            doseNumberComboBox.setValue("Dose Number");
            doseOneDetailLabel.setText("");
            doseTwoDetailLabel.setText("");
            datePickerTwo.setValue(null);
            doseTwoComboBox.setValue("Select Vaccine");
        }
      
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
