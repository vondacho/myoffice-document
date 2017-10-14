package edu.noia.myoffice.document.rest.hateoas;

import edu.noia.myoffice.document.rest.endpoint.DocumentEndpoint;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Component
public class DocumentIDResourceProcessor implements ResourceProcessor<Resource<UUID>> {

    @Override
    public Resource<UUID> process(Resource<UUID> resource) {
        resource.add(linkTo(DocumentEndpoint.class)
                .slash(resource.getContent())
                .withSelfRel());
        return resource;
    }
}
