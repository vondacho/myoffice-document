package edu.noia.myoffice.document.rest.hateoas;

import edu.noia.myoffice.document.domain.aggregate.Document;
import edu.noia.myoffice.document.rest.endpoint.DocumentEndpoint;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Component
public class DocumentResourceProcessor implements ResourceProcessor<Resource<Document>> {

    @Override
    public Resource<Document> process(Resource<Document> resource) {
        resource.add(linkTo(DocumentEndpoint.class)
                .slash(resource.getContent().getId())
                .withSelfRel());
        return resource;
    }
}
