package com.dbr.generator.springboot.system.media;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpHeaders;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Data
@AllArgsConstructor
public class MediaResponseModel {

    private byte[] content;

    private String filename;

    public int getLength() {
        return content.length;
    }

    public InputStream get() {
        return new ByteArrayInputStream(content);
    }

    public HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=" + filename + "");
        return headers;
    }
}
