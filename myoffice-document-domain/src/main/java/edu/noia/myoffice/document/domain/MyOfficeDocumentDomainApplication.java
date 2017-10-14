package edu.noia.myoffice.document.domain;

import edu.noia.myoffice.common.data.MyOfficeCommonDataApplication;
import edu.noia.myoffice.common.domain.MyOfficeCommonDomainApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import({
        MyOfficeCommonDataApplication.class,
        MyOfficeCommonDomainApplication.class
})
@SpringBootApplication
public class MyOfficeDocumentDomainApplication {
}
