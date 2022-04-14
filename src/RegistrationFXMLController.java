import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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
    private TextField mailTF;
    
    @FXML
    private TextField nidTF;
    
    @FXML
    private TextField otpTF;
    @FXML
    private Button sendOtpButton;
    @FXML
    private Button registerButton;
    @FXML
    private ComboBox<String> dayComboBox;
    @FXML
    private ComboBox<String> monthComboBox;
    @FXML
    private ComboBox<String> yearComboBox;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        registrationBtn.setStyle("-fx-background-color: #00C897;");
        dayComboBox.getItems().addAll("01","02","03","04","05","06","07","08",
                "09","10","11","12","13","14","15","16","17","18","19","20",
                "21","22","23","24","25","26","27","28","29","30","31");
        monthComboBox.getItems().addAll("January","February","March","April",
                "May","June","July","August","September","October","November","December");
        yearComboBox.getItems().addAll("2022","2021","2020","2019","2018","2017","2016","2015",
                "2014","2013","2012","2011","2010","2009","2008","2007","2006","2005","2004","2003"
                ,"2002","2001","2000","1999","1998","1997","1996","1995","1994","1993","1992",
                "1991","1990","1989","1988","1987","1986","1985","1984","1983","1982","1981",
                "1980","1979","1978","1977","1976","1975","1974","1973","1972",
                "1971","1970","1969","1968","1967","1966","1965","1964","1963",
                "1962","1961","1960","1959","1958","1957","1956","1955","1954","1953","1952",
                "1951","1950","1949","1948","1947","1946","1945","1944","1943","1942","1941",
                "1940","1939","1938","1937","1936","1935","1934","1933","1933","1932","1931",
                "1930","1929","1928","1927","1926","1925","1924","1923","1922","1921","1920");
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
    void certificateBtnAction(ActionEvent event) {
        loadPage("CertificateFXML");
        certificateBtn.setStyle("-fx-background-color: #00C897;");
        registrationBtn.setStyle("");
        backToHomeBtn.setStyle("");
        vaccineCardBtn.setStyle("");
    }

    @FXML
    void registrationBtnAction(ActionEvent event) {
        borderPane.setCenter(registrationPane);
        certificateBtn.setStyle("");
        registrationBtn.setStyle("-fx-background-color: #00C897;");
        backToHomeBtn.setStyle("");
        vaccineCardBtn.setStyle("");
    }

    @FXML
    void vaccineCardBtnAction(ActionEvent event) {
        loadPage("VaccineCardFXML");
        certificateBtn.setStyle("");
        registrationBtn.setStyle("");
        backToHomeBtn.setStyle("");
        vaccineCardBtn.setStyle("-fx-background-color: #00C897;");
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
    
    Connection DBConnection = DataBaseConnection.connectDB();
    String sendOTPBySystem = "";
    String nidNumForAllOp = "";

    @FXML
    private void registrationSendOtpAction(ActionEvent event) throws Exception{       
        MonthConversion monthToNumeric = new MonthConversion();
        String month = monthToNumeric.monthNumeric(monthComboBox.getValue());
        
        String nidNumber = nidTF.getText();
        nidNumForAllOp = nidNumber;
        String mail = mailTF.getText();
       
        String dateOfBirth = yearComboBox.getValue()+"-"+month+"-"+dayComboBox.getValue();
        
        
        String query = "select firstName,register from nidInfo,vaccineInfo "
                + "where nidInfo.nidNumber=BINARY ? and vaccineInfo.nidNumber = BINARY ? and nidInfo.dateOfBirth = BINARY ?";
        PreparedStatement statement = DBConnection.prepareStatement(query);
        statement.setString(1, nidNumber);
        statement.setString(2, nidNumber);
        statement.setString(3, dateOfBirth);
        ResultSet result = statement.executeQuery();
        
        if(result.next()==false){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Covid 19 Vaccination System");
            alert.setHeaderText("Registration Error");
            alert.setContentText("Invalid Information !!");
            alert.showAndWait();
            
            sendOTPBySystem = "";
            nidNumForAllOp = "";
            nidTF.setText("");
            mailTF.setText("");
            dayComboBox.setValue("");
            monthComboBox.setValue("");
            yearComboBox.setValue("");
            
        }else{
            if(result.getString("register").equals("YES")){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Covid 19 Vaccination System");
                alert.setHeaderText("Registration Information : ");
                alert.setContentText("Already Registered !!");
                alert.showAndWait();
            
                sendOTPBySystem = "";
                nidNumForAllOp = "";
                nidTF.setText("");
                mailTF.setText("");
                otpTF.setText("");
                dayComboBox.setValue("");
                monthComboBox.setValue("");
                yearComboBox.setValue("");
                
            }else{
                OTP mailOTP = new OTP(mail);
                sendOTPBySystem = mailOTP.sendOTP(); 
            }           
        }               
    }

    @FXML
    private void completeRegistrationButton(ActionEvent event) throws Exception{
        String otpEnterByUser = otpTF.getText();
        if(otpEnterByUser.equals(sendOTPBySystem)&& otpEnterByUser!=""){
            
            String query = "UPDATE vaccineInfo SET register = 'YES' WHERE nidNumber = BINARY ?";
            PreparedStatement statement = DBConnection.prepareStatement(query);
            statement.setString(1, nidNumForAllOp); 
            
            statement.executeUpdate();
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Covid 19 Vaccination System");
            alert.setHeaderText("Registration Information : ");
            alert.setContentText("Registration is successfull\nPlease Download Your Vaccine Card");
            alert.showAndWait();
            
            sendOTPBySystem = "";
            nidNumForAllOp = "";
            nidTF.setText("");
            mailTF.setText("");
            otpTF.setText("");
            dayComboBox.setValue("");
            monthComboBox.setValue("");
            yearComboBox.setValue("");

        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Covid 19 Vaccination System");
            alert.setHeaderText("Registration Error");
            alert.setContentText("Invalid OTP !!");
            alert.showAndWait();
            
            sendOTPBySystem = "";
            nidNumForAllOp="";
            nidTF.setText("");
            mailTF.setText("");
            otpTF.setText("");
            dayComboBox.setValue("");
            monthComboBox.setValue("");
            yearComboBox.setValue("");
        }
    }
}
