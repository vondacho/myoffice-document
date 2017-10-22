package edu.noia.myoffice.document.rest.config;

import edu.noia.myoffice.document.domain.aggregate.Template;
import org.springframework.data.rest.core.config.Projection;

import java.util.UUID;

@Projection(name = "noContent", types = { Template.class })
interface TemplateWithoutContent {

    UUID getId();

    String getName();
}