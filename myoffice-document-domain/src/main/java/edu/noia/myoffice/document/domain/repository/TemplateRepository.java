package edu.noia.myoffice.document.domain.repository;

import edu.noia.myoffice.document.domain.aggregate.Template;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TemplateRepository extends PagingAndSortingRepository<Template, Long> {
}
