package com.dbr.generator.springboot.pdf.system;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ApplicationPropertiesPDFModel {

    private Map<String, Object> properties = new HashMap<>();;

}
