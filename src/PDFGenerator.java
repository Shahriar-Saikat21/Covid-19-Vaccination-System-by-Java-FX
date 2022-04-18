import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import javafx.scene.control.Alert;


public class PDFGenerator {
    
    public void createPDF(String personalInfo,String fileNameType){
        String fileTitle = fileNameType+".pdf";
        String fileName = "C:\\Users\\User\\Downloads\\Documents\\" +fileTitle;
        Document document = new Document();
        document.setPageSize(PageSize.A4);

        try {
            PdfWriter.getInstance(document, new FileOutputStream(new File(fileName)));
            document.open();

            document.add(Image.getInstance("F:\\UIU\\Trimester 2.1\\CSE 2118 - Advanced Object Oriented Programming Laboratory\\Project\\Main Project\\Covid19VaccinationSystem\\src\\image\\mail-attach.png"));

            Paragraph paraOne = new Paragraph();
            paraOne.add(personalInfo);
            paraOne.setAlignment(Element.ALIGN_LEFT);            
            document.add(paraOne);          

            Font f = new Font();
            f.setStyle(Font.BOLD);
            f.setSize(30);                     

            document.close();
            
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Covid 19 Vaccination System");
            alert.setHeaderText("Error !!!");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
     
}
