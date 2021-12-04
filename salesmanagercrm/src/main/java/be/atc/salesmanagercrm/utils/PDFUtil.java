package be.atc.salesmanagercrm.utils;

import be.atc.salesmanagercrm.entities.UsersEntity;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import javax.faces.context.FacesContext;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * @author Younes Arifi
 * This class is for generate PDF
 */
public final class PDFUtil {

    private PDFUtil() {
    }

    public static void generatePDF(UsersEntity usersEntity, String password) {
        String namePDF = usersEntity.getFirstname() + "-" + usersEntity.getLastname() + "_" + "Account" + usersEntity.getId() + "_SalesManager.pdf";
        Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

        Document doc = new Document();

        try {
            PdfWriter.getInstance(doc, new FileOutputStream(Constants.FILE_OUT_PUT_STREAM + namePDF));
            doc.open();

            doc.add(new Paragraph(JsfUtils.returnMessage(locale, "pdf.firstname") + ": " + usersEntity.getFirstname()));
            doc.add(new Paragraph(JsfUtils.returnMessage(locale, "pdf.lastname") + ": " + usersEntity.getLastname()));
            doc.add(new Paragraph(JsfUtils.returnMessage(locale, "pdf.email") + ": " + usersEntity.getEmail()));
            doc.add(new Paragraph(JsfUtils.returnMessage(locale, "pdf.registerDate") + ": " + usersEntity.getRegisterDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
            doc.add(new Paragraph("--------------------------------------------------------------"));
            doc.add(new Paragraph("--------------------------------------------------------------"));
            doc.add(new Paragraph(JsfUtils.returnMessage(locale, "pdf.connection") + ": "));
            doc.add(new Paragraph(JsfUtils.returnMessage(locale, "pdf.username") + ": " + usersEntity.getUsername()));
            doc.add(new Paragraph(JsfUtils.returnMessage(locale, "pdf.password") + ": " + password));
            doc.add(new Paragraph("--------------------------------------------------------------"));
            doc.add(new Paragraph("--------------------------------------------------------------"));
            doc.add(new Paragraph(JsfUtils.returnMessage(locale, "pdf.role") + ": " + usersEntity.getRolesByIdRoles().getLabel()));
            doc.add(new Paragraph("--------------------------------------------------------------"));

            doc.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
