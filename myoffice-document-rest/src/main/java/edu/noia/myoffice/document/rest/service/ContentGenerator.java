package edu.noia.myoffice.document.rest.service;

public interface ContentGenerator<T,U> {

    U generate(T specification);
}
