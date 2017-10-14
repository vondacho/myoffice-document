package edu.noia.myoffice.document.rest.vo;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;

@Getter
@RequiredArgsConstructor
public enum DocumentMedia {
    JSON(MediaType.APPLICATION_JSON),
    HTML(MediaType.TEXT_HTML),
    PDF(MediaType.APPLICATION_PDF);

    @NonNull
    private MediaType mediaType;
}
