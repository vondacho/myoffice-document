package edu.noia.myoffice.document.rest.service;

import edu.noia.myoffice.document.domain.aggregate.Template;
import edu.noia.myoffice.document.rest.exception.ApplicationException;
import edu.noia.myoffice.document.rest.vo.DocumentSpecification;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;

@Component
public class HTMLContentGenerator implements ContentGenerator<Pair<DocumentSpecification, Template>, byte[]> {

    @Autowired
    private VelocityEngine templateEngine;

    @Override
    public byte[] generate(Pair<DocumentSpecification, Template> specification) {
        try (Reader input = new StringReader(specification.getSecond().getContent());
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             Writer output = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8)) {

            Context context = new VelocityContext();
            specification.getFirst().getValues().forEach(context::put);

            templateEngine.evaluate(context, output, HTMLContentGenerator.class.getName(), input);
            output.flush();

            return outputStream.toByteArray();

        } catch (IOException e) {
            throw new ApplicationException(e);
        }
    }
}
