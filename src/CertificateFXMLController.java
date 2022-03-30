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
        comboboxdate.getItems().addAll("01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31");
        comboboxmonth.getItems().addAll("January","February","March","April","May","June","July","August","September","October","November","December");
        comboboxyear.getItems().addAll("1920","1921","1922","1923","1924","1925","1926","1927","1928","1929","1930","1931","1932","1933","1933","1934","1935","1936","1937","1938","1939","1940","1941","1942","1943","1944","1945","1946","1947","1948","1949","1950","1951","1952","1953","1954","1955","1956","1957","1958","1959","1960","1961","1962","1963","1964","1965","1966","1967","1968","1969","1970","1971","1972","1973","1974","1975","1976","1977","1978","1979","1980","1981");
    }

}

