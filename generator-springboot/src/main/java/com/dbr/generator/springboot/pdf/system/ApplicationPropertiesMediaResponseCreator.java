package com.dbr.generator.springboot.pdf.system;

import com.dbr.generator.springboot.system.media.MediaResponseCreatorException;
import com.dbr.generator.springboot.system.media.MediaResponseCreatorInterface;
import com.dbr.generator.springboot.system.media.MediaResponseModel;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import freemarker.template.TemplateException;
import lombok.AllArgsConstructor;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Iterator;

@AllArgsConstructor
public class ApplicationPropertiesMediaResponseCreator implements MediaResponseCreatorInterface {

    private Environment env;

    @Override
    public MediaResponseModel get() throws IOException, TemplateException {

        ApplicationPropertiesPDFModel applicationPropertiesPDFModel = new ApplicationPropertiesPDFModel();
        for (Iterator it = ((AbstractEnvironment) env).getPropertySources().iterator(); it.hasNext(); ) {
            PropertySource propertySource = (PropertySource) it.next();
            if (propertySource instanceof MapPropertySource) {
                applicationPropertiesPDFModel.getProperties().putAll(((MapPropertySource) propertySource).getSource());
            }
        }
        ApplicationPropertiesPDFMergerInterface applicationPropertiesPDFMerger = new ApplicationPropertiesPDFMergerInterface();
        String html = applicationPropertiesPDFMerger.create(applicationPropertiesPDFModel);

        PdfRendererBuilder builder = new PdfRendererBuilder();

        builder.withHtmlContent(html, "/");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        builder.toStream(outputStream);

        try {
            builder.run();
        } catch (Exception e) {
            throw new MediaResponseCreatorException("error creating pdf: " + this.getClass().getSimpleName(), e);
        }

        return new MediaResponseModel(outputStream.toByteArray(), "applicationProperties.pdf");

    }

}
