import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class VaccineCardFXMLController implements Initializable{

    @FXML
    private ComboBox<String> comboboxdate;

    @FXML
    private ComboBox<String> comboboxmonth;

    @FXML
    private ComboBox<String> comboboxyear;

    @FXML
    private TextField mailTF;

    @FXML
    private TextField nidTF;

    @FXML
    private TextField otpTF;

    @FXML
    private Button sendVaccineCardbtn;

    @FXML
    private Button vaccineCardOtpBtn;

    @FXML
    void sendVaccineCardbtnAction(ActionEvent event) {
        System.out.println("Send VC Working");
    }

    @FXML
    void vaccineCardOtpBtnAction(ActionEvent event) {
        System.out.println("VC OTP Working");
    }
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboboxdate.getItems().addAll("01","02","03","04","05","06","07","08",
                "09","10","11","12","13","14","15","16","17","18","19","20",
                "21","22","23","24","25","26","27","28","29","30","31");
        comboboxmonth.getItems().addAll("January","February","March","April",
                "May","June","July","August","September","October","November","December");
        comboboxyear.getItems().addAll("2022","2021","2020","2019","2018","2017","2016","2015",
                "2014","2013","2012","2011","2010","2009","2008","2007","2006","2005","2004","2003"
                ,"2002","2001","2000","1999","1998","1997","1996","1995","1994","1993","1992",
                "1991","1990","1989","1988","1987","1986","1985","1984","1983","1982","1981",
                "1980","1979","1978","1977","1976","1975","1974","1973","1972",
                "1971","1970","1969","1968","1967","1966","1965","1964","1963",
                "1962","1961","1960","1959","1958","1957","1956","1955","1954","1953","1952",
                "1951","1950","1949","1948","1947","1946","1945","1944","1943","1942","1941",
                "1940","1939","1938","1937","1936","1935","1934","1933","1933","1932","1931",
                "1930","1929","1928","1927","1926","1925","1924","1923","1922","1921","1920");
        
        
        sendVaccineCardbtn.setDisable(true);
        
    }
    
    @FXML
    void anyKeyPressedAction(KeyEvent event) {
        sendVaccineCardbtn.setDisable(false);
        if(otpTF.getText().equals("")){
            sendVaccineCardbtn.setDisable(true);
        }
    }

}

