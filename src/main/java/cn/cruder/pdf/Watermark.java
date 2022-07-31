package cn.cruder.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.Data;

/**
 * @Describe 水印
 * @Author dousx
 */
@Data
public class Watermark extends PdfPageEventHelper {
    /**
     * 水印字体
     */
    Font FONT = new Font(Font.FontFamily.HELVETICA, 30, Font.BOLD, new GrayColor(0.95f));

    /**
     * 水印内容
     */
    private String waterCont;

    public Watermark(String waterCont) {
        this.waterCont = waterCont;
    }

    /**
     * Called when a page is finished, just before being written to the document.
     *
     * @param writer   the <CODE>PdfWriter</CODE> for this document
     * @param document the document
     */
    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                ColumnText.showTextAligned(writer.getDirectContentUnder(),
                        Element.ALIGN_CENTER,
                        new Phrase(this.waterCont == null ? "HELLO WORLD" : this.waterCont, FONT),
                        (50.5f + i * 350),
                        (40.0f + j * 150),
                        writer.getPageNumber() % 2 == 1 ? 45 : -45);
            }
        }
    }
}
