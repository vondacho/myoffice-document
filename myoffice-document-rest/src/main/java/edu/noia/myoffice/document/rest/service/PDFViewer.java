package edu.noia.myoffice.document.rest.service;

import edu.noia.myoffice.document.rest.exception.ApplicationException;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xhtmlrenderer.resource.FSEntityResolver;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@Component
public class PDFViewer implements ContentGenerator<byte[], byte[]> {

    @Override
    public byte[] generate(byte[] htmlInput) {
        try (ByteArrayInputStream input = new ByteArrayInputStream(htmlInput);
            ByteArrayOutputStream output = new ByteArrayOutputStream()) {

            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            builder.setEntityResolver(FSEntityResolver.instance());

            Document doc = builder.parse(input);

            ITextRenderer renderer = new ITextRenderer();
            renderer.getSharedContext().setMedia("pdf");
            renderer.setDocument(doc, "one title");
            renderer.layout();
            renderer.createPDF(output);

            output.flush();
            return output.toByteArray();

        } catch (Exception e) {
            throw new ApplicationException(e);
        }
    }
}
