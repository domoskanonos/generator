package com.dbr.generator.gen.client.typescript.compound.model;

import com.dbr.generator.gen.client.typescript.model[0].ClazzTypescriptWrapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class TypescriptClientCVM {

    private Class[] clazzes;

    private String modelNameReplaceKey = "";
    private String modelNameReplaceValue = "";

    // modules
    private boolean generateSecurityModule = true;

    // i18n
    private boolean generateI18n = true;

    // app services
    private boolean generatePageService = true;

    // data services
    private boolean generateModel = true;
    private boolean generateMessageEntriesList = true;
    private boolean generateMessageEntriesEdit = true;
    private boolean generateBasicRemoteRepository = true;
    private boolean generateBasicRemoteService = true;

    // components app
    private boolean generateMenuComponent = true;

    // components clazzes
    private boolean generateEditComponent = true;
    private boolean generateSearchNidocaList = true;
    private boolean generateComboboxComponent = true;

    // pages
    private boolean generateEditPage = true;
    private boolean generateSearchListPage = true;
    private boolean generateTermsOfUse = true;

    public TypescriptClientCVM(Class... clazzes) {
        this("", clazzes);
    }

    public TypescriptClientCVM(String modelNameReplaceKey, Class... clazzes) {
        this(modelNameReplaceKey, "", clazzes);
    }

    public TypescriptClientCVM(String modelNameReplaceKey, String modelNameReplaceValue, Class... clazzes) {
        this.modelNameReplaceKey = modelNameReplaceKey;
        this.modelNameReplaceValue = modelNameReplaceValue;
        this.clazzes = clazzes;
    }

    public String getModelName(Class clazz) {
        return new ClazzTypescriptWrapper(clazz, this.modelNameReplaceKey, this.modelNameReplaceValue).getModelName();
    }

}
