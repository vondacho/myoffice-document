package edu.noia.myoffice.document.rest.endpoint;

import edu.noia.myoffice.common.rest.util.EntityPropertyEditorSupport;
import edu.noia.myoffice.common.rest.util.IdentifiantPropertyEditorSupport;
import edu.noia.myoffice.document.domain.aggregate.Document;
import edu.noia.myoffice.document.domain.repository.DocumentRepository;
import edu.noia.myoffice.document.rest.hateoas.DocumentIDResourceProcessor;
import edu.noia.myoffice.document.rest.hateoas.DocumentResourceProcessor;
import edu.noia.myoffice.document.rest.service.DocumentService;
import edu.noia.myoffice.document.rest.service.HTMLViewer;
import edu.noia.myoffice.document.rest.service.PDFViewer;
import edu.noia.myoffice.document.rest.vo.DocumentMedia;
import edu.noia.myoffice.document.rest.vo.DocumentSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/document/v1/documents")
public class DocumentEndpoint {

    @Autowired
    private DocumentRepository repository;
    @Autowired
    private DocumentService documentService;
    @Autowired
    private DocumentResourceProcessor resourceProcessor;
    @Autowired
    private DocumentIDResourceProcessor idResourceProcessor;
    @Autowired
    private PDFViewer pdfViewer;
    @Autowired
    private HTMLViewer htmlViewer;

    @PostMapping
    public ResponseEntity createDocument(
            @RequestBody @Valid DocumentSpecification specification) {

        return viewDocument(
                documentService.create(specification),
                DocumentMedia.JSON,
                HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity getDocument(
            @PathVariable("id") Document document,
            @RequestParam(value = "media", required = false, defaultValue = "HTML") String media) {

        return viewDocument(document,
                DocumentMedia.valueOf(media.toUpperCase()),
                HttpStatus.OK);
    }

    private ResponseEntity viewDocument(Document document, DocumentMedia documentMedia, HttpStatus status) {
        switch (documentMedia) {
            case PDF:
                return ResponseEntity
                        .status(status)
                        .contentType(documentMedia.getMediaType())
                        .cacheControl(CacheControl.noCache())
                        .body(pdfViewer.generate(document.getContent()));
            case HTML:
                return ResponseEntity
                        .status(status)
                        .contentType(documentMedia.getMediaType())
                        .cacheControl(CacheControl.noCache())
                        .body(htmlViewer.generate(document.getContent()));
            default:
                return HttpStatus.CREATED.equals(status) ?
                    ResponseEntity
                        .status(status)
                        .body(idResourceProcessor.process(new Resource(document.getId()))) :
                    ResponseEntity
                        .status(status)
                        .body(resourceProcessor.process(new Resource(document)));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteDocument(@PathVariable("id") Document document) {
        repository.delete(document);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @InitBinder
    public void dataBinding(WebDataBinder binder) {
        binder.registerCustomEditor(UUID.class,
                new IdentifiantPropertyEditorSupport<>(UUID::fromString));

        binder.registerCustomEditor(Document.class,
                new EntityPropertyEditorSupport<>(UUID::fromString,
                        repository::findById,
                        UUID::toString,
                        Document::getId,
                        Document.class));
    }
}
