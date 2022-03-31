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
    private TextField otpTextField;
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
    }

    @FXML
    void registrationBtnAction(ActionEvent event) {
        borderPane.setCenter(registrationPane);
    }

    @FXML
    void vaccineCardBtnAction(ActionEvent event) {
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
        System.out.println("Registration OTP Working");
    }

    @FXML
    private void completeRegistrationButton(ActionEvent event) {
        System.out.println("Registration Complete Working");
    }
}
