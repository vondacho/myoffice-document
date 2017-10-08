package edu.noia.myoffice.document.rest.endpoint;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xhtmlrenderer.resource.FSEntityResolver;

import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/document/v1/documents")
public class DocumentResource {

    @Autowired
    ResourceLoader resourceLoader;

    @GetMapping
    public void create(@RequestParam("template") String template, HttpServletResponse response) throws Exception {
        StringWriter writer = new StringWriter();
        renderTemplate(getTemplateReader(template + ".html"), getData(), writer);
        createPdf(
            new ByteArrayInputStream(writer.toString().getBytes(StandardCharsets.UTF_8.name())),
            response.getOutputStream());
    }

    private Reader getTemplateReader(String name) throws IOException {
        Resource resource = resourceLoader.getResource("classpath:\\templates\\" + name);
        return new InputStreamReader(resource.getInputStream());
    }

    private Map<String, String> getData() {
        Map<String, String> ctx = new HashMap<>();
        ctx.put("salutation", "Monsieur");
        ctx.put("firstName", "Olivier");
        ctx.put("lastName", "von Dach");
        ctx.put("streetNo", "Précossy 37");
        ctx.put("zip", "1260");
        ctx.put("city", "Nyon");
        ctx.put("issuer", "ovo");
        ctx.put("refNo", "f-1234");
        ctx.put("tvaNo", "123456");
        ctx.put("date", "2017-10-08");
        return ctx;
    }

    private void createPdf(InputStream input, OutputStream output) throws Exception {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        builder.setEntityResolver(FSEntityResolver.instance());

        Document doc = builder.parse(input);

        ITextRenderer renderer = new ITextRenderer();
        renderer.getSharedContext().setMedia("pdf");
        renderer.setDocument(doc, "one title");
        renderer.layout();
        renderer.createPDF(output);
    }

    private void renderTemplate(Reader input, Map<String, String> context, Writer output) throws IOException {
        Context ctx = new VelocityContext();
        context.forEach(ctx::put);
        new VelocityEngine().evaluate(ctx, output, "VELOG", input);
        input.close();
    }
}
