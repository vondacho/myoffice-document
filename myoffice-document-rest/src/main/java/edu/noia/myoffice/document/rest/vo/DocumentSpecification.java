package edu.noia.myoffice.document.rest.vo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DocumentSpecification {

    @NotNull
    UUID templateId;
    @NotNull
    Map<String, Object> values;
}
