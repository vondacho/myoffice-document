package edu.noia.myoffice.document.ui;

import edu.noia.myoffice.document.rest.MyOfficeDocumentRestApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import({
		MyOfficeDocumentRestApplication.class,
})
@SpringBootApplication
public class MyOfficeDocumentUiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyOfficeDocumentUiApplication.class, args);
	}
}
