package com.dbr.generator.springboot.ws.pdf;

/**
 * Interface to generate PDF's with openhtmltopdf
 *
 * Attention: You can use Google Fonts, try this:
 *
 * <code>
 *     File file = FontResourceUtil.getGoogleFontStreamByName("Anton");
 *     builder.useFont(file, "Anton");
 * </code>
 *
 */
public interface PDFCreatorInterface {

    PDFModel get();

}
