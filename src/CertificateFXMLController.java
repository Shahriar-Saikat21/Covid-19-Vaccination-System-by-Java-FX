import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class CertificateFXMLController implements Initializable{

    @FXML
    private Button certificateSendOtpBtn;

    @FXML
    private ComboBox<String> comboBoxDate;

    @FXML
    private ComboBox<String> comboBoxMonth;

    @FXML
    private ComboBox<String> comboBoxYear;

    @FXML
    private TextField mailTF;

    @FXML
    private TextField nidTF;

    @FXML
    private TextField otpTF;

    @FXML
    private Button sendCertificateBtn;
    
    String sendOTPBySystem = "";
    String nidNumForAllOp = ""; 
    
    String detailInfo = "";
    String nidNumberForCertificate = "";

    @FXML
    void certificateSendOtpBtnAction(ActionEvent event) {
        try {
            Connection DBConnection = DataBaseConnection.connectDB();
            MonthConversion monthToNumeric = new MonthConversion();
            String month = monthToNumeric.monthNumeric(comboBoxMonth.getValue());

            String nidNumber = nidTF.getText();
            nidNumForAllOp = nidNumber;
            String mail = mailTF.getText();

            String dateOfBirth = comboBoxYear.getValue()+"-"+month+"-"+comboBoxDate.getValue();


            String query = "select * from nidInfo,vaccineInfo "
                    + "where nidInfo.nidNumber=BINARY ? and vaccineInfo.nidNumber = BINARY ? and nidInfo.dateOfBirth = BINARY ?";
            PreparedStatement statement = DBConnection.prepareStatement(query);
            statement.setString(1, nidNumber);
            statement.setString(2, nidNumber);
            statement.setString(3, dateOfBirth);
            ResultSet result = statement.executeQuery();

            if(result.next()==false){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Covid 19 Vaccination System");
                alert.setHeaderText("Download Error");
                alert.setContentText("Information is not found !!");
                alert.showAndWait();

                sendOTPBySystem = "";
                nidNumForAllOp = "";
                nidTF.setText("");
                mailTF.setText("");
                comboBoxDate.setValue("Date");
                comboBoxMonth.setValue("Month");
                comboBoxYear.setValue("Year");

            }else{
                if(result.getString("register").equals("NO")){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Covid 19 Vaccination System");
                    alert.setHeaderText("Download Information : ");
                    alert.setContentText("Unregisted ID !!");
                    alert.showAndWait();

                    sendOTPBySystem = "";
                    nidNumForAllOp = "";
                    nidTF.setText("");
                    mailTF.setText("");
                    otpTF.setText("");
                    comboBoxDate.setValue("Date");
                    comboBoxMonth.setValue("Month");
                    comboBoxYear.setValue("Year");

                }else{               
                    detailInfo = "Vaccine Certificate\n\n"+"Name : "+result.getString("firstName")+" "+result.getString("lastName")
                            +"\nDate Of Birth : "+result.getString("dateOfBirth")+
                            "\nAddress : "+result.getString("address")+"\nGender : "+result.getString("gender")
                            +"\n\n1st Dose : "+result.getString("doseOne")+"\nDate: "+result.getString("doseOneDate")+
                            "\n\n2nd Dose : "+result.getString("doseTwo")+"\nDate: "+result.getString("doseTwoDate");
                    nidNumberForCertificate = nidTF.getText();
                    OTP mailOTP = new OTP(mail);
                    sendOTPBySystem = mailOTP.sendOTP(); 
                    System.out.println(sendOTPBySystem);
                }
                DBConnection.close();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Covid 19 Vaccination System");
            alert.setHeaderText("Error !!!");
            alert.setContentText("Invalid Information");
            alert.showAndWait();
            sendOTPBySystem = "";
            nidNumForAllOp = "";
            nidTF.setText("");
            mailTF.setText("");
            otpTF.setText("");
            comboBoxDate.setValue("Date");
            comboBoxMonth.setValue("Month");
            comboBoxYear.setValue("Year");
        }             
    }

    @FXML
    void sendCertificateBtnAction(ActionEvent event) {
        String fileNameType = nidNumberForCertificate + "-Vaccine Certificate";
        String otpEnterByUser = otpTF.getText();
        if(otpEnterByUser.equals(sendOTPBySystem)&& otpEnterByUser!=""){
            
            PDFGenerator pdf = new PDFGenerator();
            pdf.createPDF(detailInfo, fileNameType);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Covid 19 Vaccination System");
            alert.setHeaderText("Download Information : ");
            alert.setContentText("Your Vaccine Certificate has been downloaded !!");
            alert.showAndWait();
            
            sendOTPBySystem = "";
            nidNumForAllOp = "";
            nidTF.setText("");
            mailTF.setText("");
            otpTF.setText("");
            comboBoxDate.setValue("Date");
            comboBoxMonth.setValue("Month");
            comboBoxYear.setValue("Year");

        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Covid 19 Vaccination System");
            alert.setHeaderText("Download Error");
            alert.setContentText("Invalid OTP !!");
            alert.showAndWait();
            
            sendOTPBySystem = "";
            nidNumForAllOp="";
            nidTF.setText("");
            mailTF.setText("");
            otpTF.setText("");
            comboBoxDate.setValue("Date");
            comboBoxMonth.setValue("Month");
            comboBoxYear.setValue("Year");
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboBoxDate.getItems().addAll("01","02","03","04","05","06","07","08","09","10",
                "11","12","13","14","15","16","17","18","19","20","21","22","23","24",
                "25","26","27","28","29","30","31","Date");
        comboBoxMonth.getItems().addAll("January","February","March","April","May",
                "June","July","August","September","October","November","December","Month");
        comboBoxYear.getItems().addAll("2022","2021","2020","2019","2018","2017","2016",
                "2015","2014","2013","2012","2011","2010","2009","2008","2007",
                "2006","2005","2004","2003","2002","2001","2000","1999","1998","1997",
                "1996","1995","1994","1993","1992","1991","1990","1989","1988","1987",
                "1986","1985","1984","1983","1982","1981","1980","1979","1978","1977",
                "1976","1975","1974","1973","1972","1971","1970","1969","1968","1967",
                "1966","1965","1964","1963","1962","1961","1960","1959","1958","1957",
                "1956","1955","1954","1953","1952","1951","1950","1949","1948","1947",
                "1946","1945","1944","1943","1942","1941","1940","1939","1938","1937",
                "1936","1935","1934","1933","1933","1932","1931","1930","1929","1928",
                "1927","1926","1925","1924","1923","1922","1921","1920","Year");

    }

}

