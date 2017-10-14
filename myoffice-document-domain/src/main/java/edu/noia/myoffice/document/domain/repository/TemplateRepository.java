package edu.noia.myoffice.document.domain.repository;

import edu.noia.myoffice.document.domain.aggregate.Template;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;
import java.util.UUID;

public interface TemplateRepository extends PagingAndSortingRepository<Template, Long> {

    Optional<Template> findById(UUID id);
}
