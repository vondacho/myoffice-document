package edu.noia.myoffice.document.rest;

import edu.noia.myoffice.common.rest.MyOfficeCommonRestApplication;
import edu.noia.myoffice.document.domain.MyOfficeDocumentDomainApplication;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@Import({
        MyOfficeDocumentDomainApplication.class,
        MyOfficeCommonRestApplication.class
})
@SpringBootApplication
public class MyOfficeDocumentRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyOfficeDocumentRestApplication.class, args);
    }

    @Bean
    public VelocityEngine templateEngine() {
        return new VelocityEngine();
    }
}
