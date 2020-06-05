package com.dbr.generator.gen.client.i18n.model;

import com.dbr.generator.basic.model.KeyValue;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@EqualsAndHashCode
public class I18nVM {

    public static String I18N_PROPERTY_SEPERATOR = "_";

    private String filename;
    private List<KeyValue> keyValues = new ArrayList<>();

    public I18nVM(String filename, List<KeyValue> keyValues) {
        this.typescriptRemoteRepositoryFilename = filename;
        this.keyValues = keyValues;
    }

    public void addKeyValue(KeyValue keyValue) {
        this.keyValues.add(keyValue);
    }

}
