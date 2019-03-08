package Utils;

import Domain.Validare.ValidationException;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.*;

public interface PDFCreator {
    public static void createPdf(String dest, String text)
            throws DocumentException, IOException {
        //Document document = new Document();
        // PdfWriter.getInstance(document, new FileOutputStream(dest));
        //document.open();
        FileOutputStream file = null;
        Stage stage = new Stage ();
        if(dest.equals("")){
            return;
        }
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Open Resource File");
            File selectedDirectory = directoryChooser.showDialog(stage);
            if(selectedDirectory == null) //daca nu e selectat un director
                throw new ValidationException("Nu a fost selectat nici un director");

            String path = selectedDirectory.toPath().toString().replace( '\\','/');

            File filePath = new File(path+"/"+dest+".pdf");
            if(filePath.exists()){
                filePath.delete();
            }
            file = new FileOutputStream(filePath);

            Document document = new Document();
            PdfWriter.getInstance(document, file);

            document.open();
            BufferedReader br = new BufferedReader(new StringReader(text));
            String line;
            Paragraph p;
            Font normal = new Font(Font.FontFamily.TIMES_ROMAN, 12);
            Font bold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
            boolean title = true;
            while ((line = br.readLine()) != null) {
                p = new Paragraph(line, title ? bold : normal);
                p.setAlignment(Element.ALIGN_JUSTIFIED);
                title = line.isEmpty();
                document.add(p);

            }

            //document.add(new Paragraph("Hello World, iText"));

            /// document.add(new Paragraph(new Date().toString()));


            document.close();

            file.close();

//        BufferedReader br = new BufferedReader(new StringReader(text));
//        String line;
//        Paragraph p;
//        Font normal = new Font(Font.FontFamily.TIMES_ROMAN, 12);
//        Font bold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
//        boolean title = true;
//        while ((line = br.readLine()) != null) {
//            p = new Paragraph(line, title ? bold : normal);
//            p.setAlignment(Element.ALIGN_JUSTIFIED);
//            title = line.isEmpty();
//            document.add(p);
//        }
        //document.close();
    }
}
