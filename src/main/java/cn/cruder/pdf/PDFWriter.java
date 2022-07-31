package cn.cruder.pdf;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import com.itextpdf.text.pdf.draw.LineSeparator;


public class PDFWriter {

    public static Document addLineSeparator(Document document) throws DocumentException {
        Paragraph paragraph = new Paragraph();
        paragraph.add(new Chunk(new LineSeparator()));
        return document.add(paragraph) ? document : new Document();
    }

    public static Document DottedLineSeparator(Document document) throws DocumentException {
        Paragraph paragraph = new Paragraph();
        paragraph.add(new Chunk(new DottedLineSeparator()));
        return document.add(paragraph) ? document : new Document();
    }



    
}
