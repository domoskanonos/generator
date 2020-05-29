package com.dbr.generator.springboot.ws.pdf.impl;

import com.dbr.generator.springboot.ws.pdf.PDFCreatorException;
import com.dbr.generator.springboot.ws.pdf.PDFCreatorInterface;
import com.dbr.generator.springboot.ws.pdf.PDFModel;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import java.io.ByteArrayOutputStream;

@AllArgsConstructor
public class ApplicationPropertiesPDFCreator implements PDFCreatorInterface {

    private Environment env;

    @Override
    public PDFModel get() {


        //TODO:Implement Template Engine Example

        String html = "<html></html>";

        PdfRendererBuilder builder = new PdfRendererBuilder();

        builder.withHtmlContent(html, "/");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        builder.toStream(outputStream);

        try {
            builder.run();
        } catch (Exception e) {
            throw new PDFCreatorException("error creating pdf: " + this.getClass().getSimpleName(), e);
        }

        return new PDFModel(outputStream.toByteArray(), "applicationProperties.pdf");

    }


}
