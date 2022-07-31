package com.u2c.pdf;

import cn.cruder.pdf.*;
import cn.cruder.pdf.Header;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import com.itextpdf.text.pdf.draw.LineSeparator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 * @Describe
 * @Author dousx
 */
public class PdfReportTest {


    public static void main(String[] args) throws Exception {
        try {
            // 1.新建document对象
            Document document = new Document(PageSize.A4);

            // 2.建立一个书写器(Writer)与document对象关联
            String pdfFileName = getResourcesPath() + File.separator + DateUtil.format(new Date(), DatePattern.PURE_DATETIME_MS_PATTERN) + ".pdf";
            File file = new File(pdfFileName);
            file.createNewFile();
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
            // 设置水印
            writer.setPageEvent(new Watermark("水印"));
            // 设置 页眉/页脚
            writer.setPageEvent(new Header(" 页眉"));
            writer.setPageEvent(new Footer(" 页脚11"));

            // 3.打开文档
            document.open();
            document.addTitle("Title@PDF-Java");// 标题
            document.addAuthor("Author@umiz");// 作者
            document.addSubject("Subject@iText pdf sample");// 主题
            document.addKeywords("Keywords@iTextpdf");// 关键字
            document.addCreator("Creator@umiz`s");// 创建者

            // 4.向文档中添加内容
            writeDocumentContent(document);

            // 5.关闭文档
            document.close();
            // 6.输入文件路径
            System.out.println(file.getPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 定义全局的字体静态变量
    private static Font titlefont;
    private static Font headfont;
    private static Font keyfont;
    private static Font textfont;
    // 最大宽度
    private static final int maxWidth = 520;

    // 静态代码块
    static {
        try {
            // 不同字体（这里定义为同一种字体：包含不同字号、不同style）
            BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            titlefont = new Font(bfChinese, 16, Font.BOLD);
            headfont = new Font(bfChinese, 14, Font.BOLD);
            keyfont = new Font(bfChinese, 10, Font.BOLD);
            textfont = new Font(bfChinese, 10, Font.NORMAL);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成PDF文件
     *
     * @param document {@link Document}
     * @throws Exception
     */
    public static void writeDocumentContent(Document document) throws Exception {

        // 段落
        Paragraph paragraph = new Paragraph("美好的一天从早起开始！", titlefont);
        paragraph.setAlignment(1); //设置文字居中 0靠左   1，居中     2，靠右
        paragraph.setIndentationLeft(12); //设置左缩进
        paragraph.setIndentationRight(12); //设置右缩进
        paragraph.setFirstLineIndent(24); //设置首行缩进
        paragraph.setLeading(20f); //行间距
        paragraph.setSpacingBefore(5f); //设置段落上空白
        paragraph.setSpacingAfter(10f); //设置段落下空白




        // 超链接
        Anchor anchor = new Anchor("baidu");
        anchor.setReference("www.baidu.com");

        // 定位
        Anchor gotoP = new Anchor("goto");
        gotoP.setReference("#top");

        // 添加图片
        Image image = Image.getInstance("https://img-blog.csdn.net/20180801174617455?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl8zNzg0ODcxMA==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70");
        image.setAlignment(Image.ALIGN_CENTER);
        image.scalePercent(40); //依照比例缩放

        // 表格
        PdfPTable table = TableUtil.createTable(new float[]{40, 120, 120, 120, 80, 80});
        table.addCell(CellUtil.createCell("美好的一天", headfont, Element.ALIGN_LEFT, 6, false));
        table.addCell(CellUtil.createCell("早上9:00", keyfont, Element.ALIGN_CENTER));
        table.addCell(CellUtil.createCell("中午11:00", keyfont, Element.ALIGN_CENTER));
        table.addCell(CellUtil.createCell("中午13:00", keyfont, Element.ALIGN_CENTER));
        table.addCell(CellUtil.createCell("下午15:00", keyfont, Element.ALIGN_CENTER));
        table.addCell(CellUtil.createCell("下午17:00", keyfont, Element.ALIGN_CENTER));
        table.addCell(CellUtil.createCell("晚上19:00", keyfont, Element.ALIGN_CENTER));
        Integer totalQuantity = 0;
        for (int i = 0; i < 5; i++) {
            table.addCell(CellUtil.createCell("起床", textfont));
            table.addCell(CellUtil.createCell("吃午饭", textfont));
            table.addCell(CellUtil.createCell("午休", textfont));
            table.addCell(CellUtil.createCell("下午茶", textfont));
            table.addCell(CellUtil.createCell("回家", textfont));
            table.addCell(CellUtil.createCell("吃晚饭", textfont));
            totalQuantity++;
        }
        table.addCell(CellUtil.createCell("总计", keyfont));
        table.addCell(CellUtil.createCell("", textfont));
        table.addCell(CellUtil.createCell("", textfont));
        table.addCell(CellUtil.createCell("", textfont));
        table.addCell(CellUtil.createCell(String.valueOf(totalQuantity) + "件事", textfont));
        table.addCell(CellUtil.createCell("", textfont));

        document.add(paragraph);
        document.add(anchor);
        PDFWriter.DottedLineSeparator(document);
        document.add(gotoP);
        PDFWriter.addLineSeparator(document);
        document.add(table);
        document.add(image);
    }




/**--------------------------创建表格的方法start------------------- ---------*/

/**--------------------------创建表格的方法end------------------- ---------*/
    /**
     * 获取文件路径
     *
     * @return resources path路径
     * @throws IOException io异常
     */
    public static String getResourcesPath() throws IOException {
        String resourcesPath = "src" + File.separator + "main" + File.separator + "resources";
        File directory = new File(resourcesPath);
        return directory.getCanonicalPath();

    }

}




