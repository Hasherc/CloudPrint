package util;


import com.itextpdf.text.pdf.PdfReader;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hslf.usermodel.SlideShow;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author hasherc
 * @ 17-8-13
 */
public class OfficeUtil {
    public static int getPages(String filePath, String fileType) {
        int pages = 0;
        try {
            switch (fileType) {
                case "doc":
                    WordExtractor docFile = new WordExtractor(new FileInputStream(filePath));
                    pages = docFile.getSummaryInformation().getPageCount();// 总页数
                    break;
                case ".docx":
                    XWPFDocument docxFile = new XWPFDocument(POIXMLDocument.openPackage(filePath));
                    pages = docxFile.getProperties().getExtendedProperties().getUnderlyingProperties().getPages();// 总页数
                    break;
                case ".ppt":
                    InputStream isPPT = new FileInputStream(filePath);
                    SlideShow pptFile = new SlideShow(isPPT);
                    pages = pptFile.getSlides().length;
                    break;
                case ".pptx":
                    XMLSlideShow pptxFile = new XMLSlideShow(new FileInputStream(filePath));
                    pages = pptxFile.getSlides().length;
                    break;

                //txt  xls均转化为pdf后再判断页数
                case ".txt":
                case ".xls":
                case ".xlsx":
//                    DocConverter converter=new DocConverter(filePath);
//                    converter.conver();
//                    PdfReader otherFile=new PdfReader(converter.getpdfPath());
//                    pages=otherFile.getNumberOfPages();
//                    break;

                case ".pdf":
                    PdfReader pdfFile = new PdfReader(filePath);
                    pages = pdfFile.getNumberOfPages();
                    break;
                default:
                    pages = 0;
                    break;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return pages;
    }
}