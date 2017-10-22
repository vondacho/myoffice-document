package edu.noia.myoffice.document.domain.repository;

import edu.noia.myoffice.document.domain.aggregate.Template;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Optional;
import java.util.UUID;

public interface TemplateRepository extends PagingAndSortingRepository<Template, Long>/*,
        QueryDslPredicateExecutor<Template>,
        QuerydslBinderCustomizer<QTemplate>*/
{

    @RestResource(exported = false)
    Optional<Template> findById(UUID id);

    @RestResource(path = "name", rel = "findByName")
    Page<Template> findByNameContainingIgnoreCase(@Param("term") String term, Pageable pageable);
/*
    @Override
    default public void customize(QuerydslBindings bindings, QTemplate template) {

        bindings.bind(user.nationality).first((path, value) -> path.equalsIgnoreCase(value)); // 1
        bindings.bind(String.class).first((StringPath path, String value) -> path.containsIgnoreCase(value)); // 2
        bindings.excluding(template.content);
    }*/
}
