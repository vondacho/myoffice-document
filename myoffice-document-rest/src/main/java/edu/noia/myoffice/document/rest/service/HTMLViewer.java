package edu.noia.myoffice.document.rest.service;

import org.springframework.stereotype.Component;

@Component
public class HTMLViewer implements ContentGenerator<byte[], byte[]> {

    @Override
    public byte[] generate(byte[] htmlInput) {
        return htmlInput;
    }
}
