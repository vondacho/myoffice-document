package edu.noia.myoffice.document.domain.aggregate;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Getter
@EqualsAndHashCode(of = {"id"}, callSuper = false)
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Template {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long primaryId;

    @NonNull
    UUID id;

    @NonNull
    String name;
}