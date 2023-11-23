package com.fengx.mytest.external.picture;

import com.fengx.mytest.external.rabbitmq.AQProducer;
//import com.lowagie.text.pdf.BaseFont;
import com.itextpdf.text.pdf.BaseFont;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xhtmlrenderer.swing.Java2DRenderer;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestBoot {

    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void test() throws Exception {
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

    @Test
    public void test2() throws Exception {
        String imgPath = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAMgAAADIAQAAAACFI5MzAAAA1klEQVR42t2YQQ7DMAgE+f+nt6oNCyT9wJQcEnm4WMACibimkEL3tY1G9LVzeL4PlMQlec/rYB86GZH7D9JncJKZeMjPHGURK4Wfl4aQiMocP7nKoCTaoaTcMsgjTsIMlvsTl9RlI0/SHUlcViMlR3XhSOl3Xjtzcyo7jFjCi3dMiSTj90g/YYlG1FQzXrUoIMmArff7hENWy/U0NHswi/TV1KU1F0Ea2QNRbXtg4j28J24XGpdEzUIxJlcw0ZC6tb3iSElERP8wWTnKIr3W1YT32AFR5AMzN57gvGjQywAAAABJRU5ErkJggg==";
        Context context = new Context();
        context.setVariable("imgPath", imgPath);
        String html = templateEngine.process("test", context);
        ITextRenderer renderer = new ITextRenderer();
        renderer.getFontResolver().addFont("static/font/simsun.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        renderer.setDocumentFromString(html);
        renderer.layout();
        renderer.createPDF(new FileOutputStream(new File("output_pdf-test2.pdf")));
    }
}
