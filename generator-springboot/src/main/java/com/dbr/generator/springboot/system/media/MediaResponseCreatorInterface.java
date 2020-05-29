package com.dbr.generator.springboot.system.media;

import freemarker.template.TemplateException;

import java.io.IOException;

/**
 * Interface to generate media response model to send content to client.
 *
 */
public interface MediaResponseCreatorInterface {

    MediaResponseModel get() throws IOException, TemplateException;

}
