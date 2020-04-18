package pl.dmcs.bujazdowski.selenium;

import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class PdfTest extends AbstractSeleniumTest {

    @Test
    public void shouldGeneratePdf() throws Exception {
        WebDriver driver = loginAsAdmin();
        String yoursBillsTablePath = "//table[@id='yours-bills-table']/tbody/tr[1]";

        String address = driver.findElement(By.xpath(yoursBillsTablePath + "/td[1]")).getText();
        String pdfLink = driver.findElement(By.xpath(yoursBillsTablePath + "/td[5]/a")).getAttribute("href");

        String text = readPDFContent(pdfLink);

        Assertions.assertTrue(
                text.contains(address),
                "Should contain address"
        );
    }

    public String readPDFContent(String appUrl) throws Exception {
        URL url = new URL(appUrl);
        InputStream is = url.openStream();
        BufferedInputStream fileToParse = new BufferedInputStream(is);
        PDDocument document = null;
        String output = null;
        try {
            document = PDDocument.load(fileToParse);
            output = new PDFTextStripper().getText(document);
            System.out.println(output);
        } finally {
            if ( document != null ) {
                document.close();
            }
            fileToParse.close();
            is.close();
        }
        return output;
    }


}
