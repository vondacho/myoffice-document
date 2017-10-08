package edu.noia.myoffice.document.rest;

import edu.noia.myoffice.document.domain.MyOfficeDocumentDomainApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import({
        MyOfficeDocumentDomainApplication.class
})
@SpringBootApplication
public class MyOfficeDocumentRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyOfficeDocumentRestApplication.class, args);
    }
}
