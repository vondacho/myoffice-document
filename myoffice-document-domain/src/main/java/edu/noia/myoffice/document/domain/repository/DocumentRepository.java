package edu.noia.myoffice.document.domain.repository;

import edu.noia.myoffice.document.domain.aggregate.Document;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface DocumentRepository extends CrudRepository<Document, Long> {

    Optional<Document> findById(UUID id);
}
