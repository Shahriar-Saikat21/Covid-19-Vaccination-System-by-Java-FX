import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;


public class PDFGenerator {
    
    public void createPDF(String personalInfo,String nidNumber){
        String fileName = "C:\\Users\\User\\Downloads\\Documents\\"+ nidNumber +"Vaccine-Card.pdf";
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

            Paragraph paraTwo = new Paragraph();
            paraTwo.setAlignment(Element.ALIGN_LEFT);
            paraTwo.add("\n\n1st Dose : \nVaccine : \nDate : \nAuthorised Signature : "); 
            document.add(paraTwo);
            
            Paragraph paraThree = new Paragraph();
            paraThree.setAlignment(Element.ALIGN_LEFT);
            paraThree.add("\n\n2nd Dose : \nVaccine : \nDate : \nAuthorised Signature : "); 
            document.add(paraThree);

            Font f = new Font();
            f.setStyle(Font.BOLD);
            f.setSize(30);                     

            document.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
}
