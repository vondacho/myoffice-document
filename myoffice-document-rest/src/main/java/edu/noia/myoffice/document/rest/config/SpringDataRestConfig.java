package edu.noia.myoffice.document.rest.config;

import edu.noia.myoffice.document.domain.aggregate.Template;
import edu.noia.myoffice.document.domain.repository.TemplateRepository;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SpringDataRestConfig extends RepositoryRestConfigurerAdapter {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config
            .withEntityLookup()
            .forRepository(
                TemplateRepository.class,
                (Template template) -> template.getId().toString(),
                (TemplateRepository repository, String uuid) -> repository.findById(UUID.fromString(uuid)));

        config
            .getProjectionConfiguration()
            .addProjection(TemplateWithoutContent.class)
            .addProjection(DocumentWithoutContent.class);
    }
}
