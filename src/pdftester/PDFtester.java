package pdftester;

import com.ibm.icu.text.ArabicShaping;
import com.ibm.icu.text.ArabicShapingException;
import java.io.File;
import java.io.IOException;
import org.apache.fontbox.ttf.TrueTypeFont;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDTrueTypeFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDTextField;
import org.apache.pdfbox.text.PDFTextStripper;

public class PDFtester {

    public static void main(String[] args) throws IOException, ArabicShapingException {

//        PDDocument doc = PDDocument.load(new File("c:\\data\\immigration_letters\\EmployeeTransfer.pdf"));
        PDDocument doc = PDDocument.load(new File("C:\\data\\immigration_letters\\EmployeeTransfer.pdf"));
        PDAcroForm acroForm = doc.getDocumentCatalog().getAcroForm();
        PDResources formResources = acroForm.getDefaultResources();
        PDType0Font font = PDType0Font.load(doc, new File("C:\\data\\arial.ttf"));

        formResources.put(COSName.getPDFName("F0"), font);
        String s = "نحن معشر الانبياء امرنا ان نخاطب الناس على قدر عقولهم";
        PDTextField formField = (PDTextField) acroForm.getField("15");
        formField.setDefaultAppearance("/F0 0 Tf 0 g");
        formField.setValue(new StringBuilder(new ArabicShaping(ArabicShaping.LETTERS_SHAPE_TASHKEEL_ISOLATED).shape(s)).reverse().toString());
//        formField.setDefaultAppearance("/Helv 0 Tf 0 g");
        acroForm.getField("14").setValue(String.valueOf("28273601580".charAt(0)));
        acroForm.getField("13").setValue(String.valueOf("28273601580".charAt(0)));
        acroForm.getField("12").setValue(String.valueOf("28273601580".charAt(0)));
        acroForm.getField("11").setValue(String.valueOf("28273601580".charAt(0)));
//        formField.setReadOnly(true);

        doc.save("C:\\data\\immigration_letters\\filling.pdf");
        doc.close();
    }
}
