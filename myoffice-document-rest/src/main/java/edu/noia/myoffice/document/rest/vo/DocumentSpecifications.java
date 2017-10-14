package edu.noia.myoffice.document.rest.vo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DocumentSpecifications {

    Boolean multiPages = true;

    @NotNull
    @Valid
    List<DocumentSpecification> specifications;
}
