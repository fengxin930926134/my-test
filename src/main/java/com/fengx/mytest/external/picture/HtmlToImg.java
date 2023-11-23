package com.fengx.mytest.external.picture;

import com.itextpdf.text.pdf.BaseFont;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;
import org.springframework.util.Base64Utils;
import org.springframework.web.util.HtmlUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xhtmlrenderer.extend.FSImage;
import org.xhtmlrenderer.pdf.ITextFSImage;
import org.xhtmlrenderer.pdf.ITextOutputDevice;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xhtmlrenderer.render.BlockBox;
import org.xhtmlrenderer.render.RenderingContext;
import org.xhtmlrenderer.simple.Graphics2DRenderer;
import org.xhtmlrenderer.simple.PDFRenderer;
import org.xhtmlrenderer.simple.extend.XhtmlNamespaceHandler;
import org.xhtmlrenderer.swing.*;
import org.xhtmlrenderer.util.ImageUtil;
import org.xhtmlrenderer.util.XRLog;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Base64;
import java.util.logging.Level;

public class HtmlToImg {


    public static void main(String[] args) throws Exception {
        test2();
    }
    private static void test3() throws Exception {
        TemplateEngine templateEngine = new TemplateEngine();
        String imgPath = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAMgAAADIAQAAAACFI5MzAAAA1klEQVR42t2YQQ7DMAgE+f+nt6oNCyT9wJQcEnm4WMACibimkEL3tY1G9LVzeL4PlMQlec/rYB86GZH7D9JncJKZeMjPHGURK4Wfl4aQiMocP7nKoCTaoaTcMsgjTsIMlvsTl9RlI0/SHUlcViMlR3XhSOl3Xjtzcyo7jFjCi3dMiSTj90g/YYlG1FQzXrUoIMmArff7hENWy/U0NHswi/TV1KU1F0Ea2QNRbXtg4j28J24XGpdEzUIxJlcw0ZC6tb3iSElERP8wWTnKIr3W1YT32AFR5AMzN57gvGjQywAAAABJRU5ErkJggg==";
        Context context = new Context();
        context.setVariable("imgPath", imgPath);
        String html = templateEngine.process("test.html", context);
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(html.getBytes()));
        Java2DRenderer imageRenderer = new Java2DRenderer(doc,  1024, -1);
//        imageRenderer.getSharedContext().setReplacedElementFactory(new ImgReplacedElementFactory());
        imageRenderer.setBufferedImageType(BufferedImage.TYPE_INT_RGB);
        BufferedImage image = imageRenderer.getImage();
        // 将BufferedImage写入本地文件
        File output = new File("output_image.png");
        // 使用 FileImageOutputStream
        try (FileImageOutputStream outputStream = new FileImageOutputStream(output)) {
            ImageIO.write(image, "png", outputStream);
            System.out.println("Image saved to " + output.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void test2() throws Exception {
        String qrCodeHtml = getQrCodeHtml();
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(qrCodeHtml.getBytes()));
        Java2DRenderer imageRenderer = new Java2DRenderer(doc,  300, -1);
        imageRenderer.setBufferedImageType(BufferedImage.TYPE_INT_RGB);
        BufferedImage image = imageRenderer.getImage();
        // 将BufferedImage写入本地文件
        File output = new File("output_image.png");
        // 使用 FileImageOutputStream
        try (FileImageOutputStream outputStream = new FileImageOutputStream(output)) {
            ImageIO.write(image, "png", outputStream);
            System.out.println("Image saved to " + output.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(getQrCodeHtml());
        renderer.getFontResolver().addFont("static/font/simsun.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
//        renderer.getSharedContext().setReplacedElementFactory(new ImgReplacedElementFactory());
        renderer.layout();
        renderer.createPDF(new FileOutputStream(new File("output_pdf-test.pdf")));

//        ITextRenderer renderer = new ITextRenderer();
//        renderer.setDocumentFromString(getQrCodeHtml());
//        renderer.getSharedContext().setReplacedElementFactory(new ImgReplacedElementFactory());
//        renderer.layout();
//        BufferedImage bufferedImage = new BufferedImage(400, 400, BufferedImage.TYPE_INT_RGB);
//        ITextOutputDevice outputDevice = renderer.getOutputDevice();
//        ITextFSImage fsImage = new ITextFSImage();
//        outputDevice.drawImage(image, 400, 400);
    }


    private static String getQrCodeHtml() {
        return "<!DOCTYPE html>" +
                "<html lang=\"en\">" +
                "<head>" +
                "    <meta charset=\"UTF-8\"></meta>" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"></meta>" +
                "    <title></title>" +
                "    <style>" +
                "        * {" +
                "            padding: 6px;" +
                "            margin: 0;" +
                "        }" +
                "        body {" +
                "            font-family: SimSun, fangzhengcuqian, serif;" +
                "            text-align: center;" +
                "        }" +
                "        .image {" +
                "            width: 200px;" +
                "            height: 200px;" +
                "        }" +
                "    </style>" +
                "</head>" +
                "<body>" +
                "    <h5 class=\"title\" id=\"titlePlaceholder\">防晒喷防晒防晒防晒防晒防晒防晒防晒防/120ml/60%/cas002111111防晒防晒防晒防晒防晒防晒防晒防晒防晒防晒防晒防晒防晒防晒防晒防晒防晒防晒防晒</h5>" +
                "<img class=\"image\" src=\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAMgAAADIAQAAAACFI5MzAAAA1klEQVR42t2YQQ7DMAgE+f+nt6oNCyT9wJQcEnm4WMACibimkEL3tY1G9LVzeL4PlMQlec/rYB86GZH7D9JncJKZeMjPHGURK4Wfl4aQiMocP7nKoCTaoaTcMsgjTsIMlvsTl9RlI0/SHUlcViMlR3XhSOl3Xjtzcyo7jFjCi3dMiSTj90g/YYlG1FQzXrUoIMmArff7hENWy/U0NHswi/TV1KU1F0Ea2QNRbXtg4j28J24XGpdEzUIxJlcw0ZC6tb3iSElERP8wWTnKIr3W1YT32AFR5AMzN57gvGjQywAAAABJRU5ErkJggg==\" alt=\"Image\" id=\"imagePlaceholder\"></img>" +
                "<h5 class=\"subtitle\" id=\"subtitlePlaceholder\">dawdawdwadawdw132</h5>" +
                "</body>" +
                "</html>";
    }

}
