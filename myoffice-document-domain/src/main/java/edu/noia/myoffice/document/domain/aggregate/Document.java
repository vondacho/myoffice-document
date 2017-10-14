package edu.noia.myoffice.document.domain.aggregate;

import edu.noia.myoffice.common.data.jpa.JpaAuditableEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Getter
@EqualsAndHashCode(of = {"id"}, callSuper = false)
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Document extends JpaAuditableEntity {

    @NotNull
    @NonNull
    UUID id;

    @NotNull
    @NonNull
    UUID templateId;

    @NotNull
    @NonNull
    @Basic(fetch= FetchType.LAZY)
    @Lob
    byte[] content;
}
