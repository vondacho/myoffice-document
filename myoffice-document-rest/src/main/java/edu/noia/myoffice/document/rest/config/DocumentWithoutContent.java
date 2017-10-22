package edu.noia.myoffice.document.rest.config;

import edu.noia.myoffice.document.domain.aggregate.Document;
import org.springframework.data.rest.core.config.Projection;

import java.util.UUID;

@Projection(name = "noContent", types = { Document.class })
interface DocumentWithoutContent {

    UUID getId();

    UUID getTemplateId();

    String getName();
}