import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Random;
import javafx.scene.control.Alert;

public class OTP {
    
    String mailToSend;

    public OTP(String mailToSend) {
        this.mailToSend = mailToSend;
    }

    public String sendOTP() {
            final String username = "covidvaccineuiu@gmail.com";
            final String password = "UIU@1234";
            String fromEmail = "covidvaccineuiu@gmail.com";
            String toEmail = mailToSend;

            Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");

            Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username,password);
                    }
            });
            //Start our mail message
            MimeMessage msg = new MimeMessage(session);
            Random randomNumber = new Random();
            int otpNumber = randomNumber.nextInt(999999);
            try {
                    msg.setFrom(new InternetAddress(fromEmail));
                    msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
                    msg.setSubject("Covid 19 Vaccination System OTP");
                    String mailBody = "<h2>Your One Time Password</h2><br/><h1>"+otpNumber+"</h1>";
                    msg.setContent(mailBody,"text/html");
                    //msg.setText(otpNumber+"");

                    Transport.send(msg);                    
                    
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Covid 19 Vaccination System");
                    alert.setHeaderText("OTP");
                    alert.setContentText("Your OTP has been sent to your email");
                    alert.showAndWait();
                    
            } catch (MessagingException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Covid 19 Vaccination System");
                    alert.setHeaderText("Error !!!");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
            } 
            
            return otpNumber+"";

    }

}
