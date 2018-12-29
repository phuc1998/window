/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdf;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Component;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JPanel;



/**
 *
 * @author PHUC
 */
public class PDFPrint {
    public static void generatePDF(JPanel jPanel, File directory, String fileName) throws FileNotFoundException, DocumentException, IOException{
        Document document = new Document();
        File file = new File(directory.getAbsolutePath() + File.separatorChar + fileName + ".pdf");
        if(!file.exists())
            file.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(file, false);
        PdfWriter pdfWriter = PdfWriter.getInstance(document, fileOutputStream);
        document.open();
        PdfContentByte contentByte = pdfWriter.getDirectContent();
        PdfTemplate template = contentByte.createTemplate(500, 700);
        Graphics2D gd = template.createGraphicsShapes(500, 700);
        jPanel.setOpaque(false);
        jPanel.printAll(gd);
        gd.dispose();
        contentByte.addTemplate(template, 50, 50);
        jPanel.setOpaque(true);
        document.close();
    }

}
