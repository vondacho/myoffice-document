package edu.noia.myoffice.document.rest.service;

import edu.noia.myoffice.common.domain.exception.ResourceNotFoundException;
import edu.noia.myoffice.document.domain.aggregate.Document;
import edu.noia.myoffice.document.domain.aggregate.Template;
import edu.noia.myoffice.document.domain.repository.DocumentRepository;
import edu.noia.myoffice.document.domain.repository.TemplateRepository;
import edu.noia.myoffice.document.rest.vo.DocumentSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private TemplateRepository templateRepository;
    @Autowired
    private HTMLContentGenerator htmlContentGenerator;

    @Transactional
    public Document create(DocumentSpecification specification) {
        return templateRepository.findById(specification.getTemplateId())
            .map(template -> Document.of(UUID.randomUUID(), template.getId(),
                    htmlContentGenerator.generate(Pair.of(specification, template))))
            .map(document -> documentRepository.save(document))
            .orElseThrow(() -> new ResourceNotFoundException(
                String.format("No %s identified by %s has been found", Template.class, specification.getTemplateId()))
            );
    }
}
