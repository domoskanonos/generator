package com.dbr.generator.gen.client.typescript.model;

import com.dbr.generator.basic.model.TypescriptProperty;
import com.dbr.generator.gen.client.i18n.model.I18nVM;
import com.dbr.generator.basic.model.KeyValue;
import com.dbr.generator.basic.model.GeneratorUtil;
import com.dbr.util.StringUtil;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Data
public class ClazzTypescriptWrapper {

    private Class clazz;
    private String modelNameReplaceKey;
    private String modelNameReplaceValue;
    private List<TypescriptProperty> properties;

    public ClazzTypescriptWrapper(Class clazz, String modelNameReplaceKey, String modelNameReplaceValue) {
        this.clazz = clazz;
        this.modelNameReplaceKey = modelNameReplaceKey;
        this.modelNameReplaceValue = modelNameReplaceValue;
        this.properties = GeneratorUtil.getTypescriptProperties(this.clazz.getDeclaredFields());
    }

    public static ClazzTypescriptWrapper[] toWrapper(Class[] clazzes, String modelNameReplaceKey,
            String modelNameReplaceValue) {
        ClazzTypescriptWrapper[] clazzTypescriptWrappers = new ClazzTypescriptWrapper[clazzes.length];
        int index = 0;
        for (Class clazz : clazzes) {
            clazzTypescriptWrappers[index] = new ClazzTypescriptWrapper(clazz, modelNameReplaceKey,
                    modelNameReplaceValue);
            index++;
        }
        return clazzTypescriptWrappers;
    }

    public String getIdFieldName() {
        for (TypescriptProperty typescriptProperty : properties) {
            if (typescriptProperty.getIdField()) {
                return typescriptProperty.getFieldName();
            }
        }
        return null;
    }

    public String getComboboxComponentTagName() {
        return new StringBuilder().append(this.getModelNameLowerCase()).append("-combobox-component").toString();
    }

    public String getEditComponentTagName() {
        return new StringBuilder().append(this.getModelNameLowerCase()).append("-edit-component").toString();
    }

    public String getSearchNidocaListTagName() {
        return new StringBuilder().append(this.getModelNameLowerCase()).append("-search-list-component").toString();
    }

    public String getEditPageTagName() {
        return new StringBuilder().append(this.getModelNameLowerCase()).append("-edit-page").toString();
    }

    public String getSearchListPageTagName() {
        return new StringBuilder().append(this.getModelNameLowerCase()).append("-search-list-page").toString();
    }

    public String getModelNameLowerCase() {
        return getModelName().toLowerCase();
    }

    public String getModelName() {
        String simpleName = clazz.getSimpleName();
        return StringUtils.isNotBlank(modelNameReplaceKey)
                ? simpleName.replace(modelNameReplaceKey, modelNameReplaceValue) : simpleName;
    }

    public String getI18nSearchListPageTitleKey() {
        return new StringBuilder().append(getModelNameLowerCase()).append("_list_title").toString();
    }

    public String getI18nSearchListPageTitleValue() {
        return new StringBuilder().append("Suche ").append(this.getModelName()).toString();
    }

    public String getI18nSearchListKey() {
        return new StringBuilder().append(getModelNameLowerCase()).append("_search_list").toString();
    }

    public String getI18nSearchListValue() {
        return new StringBuilder().append(this.getModelName()).append(" suchen").toString();
    }

    public String getI18nEditPageTitleKey() {
        return new StringBuilder().append(getModelNameLowerCase()).append("_edit_title").toString();
    }

    public String getI18nEditPageTitleValue() {
        return new StringBuilder().append("Bearbeite ").append(getModelName()).toString();
    }

    public String getI18nEditKey() {
        return new StringBuilder().append(getModelNameLowerCase()).append("_edit").toString();
    }

    public String getI18nEditValue() {
        return new StringBuilder().append(getModelName()).append(" bearbeiten").toString();
    }

    public String getI18nAddKey() {
        return new StringBuilder().append(getModelNameLowerCase()).append("_add").toString();
    }

    public String getI18nAddValue() {
        return new StringBuilder().append(getModelName()).append(" hinzufügen").toString();
    }

    public String getI18nEditDialogDeleteTitleKey() {
        return new StringBuilder().append(getModelNameLowerCase()).append("_edit_dialog_delete_title").toString();
    }

    public String getI18nEditDialogDeleteTitleValue() {
        return new StringBuilder().append(getModelName()).append(" löschen").toString();
    }

    public String getI18nEditDialogDeleteDescriptionKey() {
        return new StringBuilder().append(getModelNameLowerCase()).append("_edit_dialog_delete_description").toString();
    }

    public String getI18nEditDialogDeleteDescriptionValue() {
        return new StringBuilder().append("Der ").append(getModelName()).append(" wird unwiederuflich gelöscht")
                .toString();
    }

    public String getModelListPageUrl() {
        return getI18nSearchListKey();
    }

    public String getModelEditPageUrl() {
        return getI18nEditKey();
    }

    public String getFolderNameComponents() {
        return new StringBuilder().append("components/").append(getModelNameLowerCase()).toString();
    }

    public String getFolderNamePages() {
        return new StringBuilder().append("pages/").append(getModelNameLowerCase()).toString();
    }

    public List<KeyValue> getI18nKeyValues() {
        List<KeyValue> keyValues = new ArrayList<>();
        keyValues.add(new KeyValue(getModelNameLowerCase(), getModelName()));
        keyValues.add(new KeyValue(getI18nEditKey(), getI18nEditValue()));
        keyValues.add(new KeyValue(getI18nAddKey(), getI18nAddValue()));
        keyValues.add(new KeyValue(getI18nSearchListKey(), getI18nSearchListValue()));
        keyValues.add(new KeyValue(getI18nEditPageTitleKey(), getI18nEditPageTitleValue()));
        keyValues.add(new KeyValue(getI18nSearchListPageTitleKey(), getI18nSearchListPageTitleValue()));
        keyValues.add(new KeyValue(getI18nEditDialogDeleteTitleKey(), getI18nEditDialogDeleteTitleValue()));
        keyValues.add(new KeyValue(getI18nEditDialogDeleteDescriptionKey(), getI18nEditDialogDeleteDescriptionValue()));
        properties.forEach(typescriptProperty -> {
            keyValues.add(addI18nKeyValueProperty(typescriptProperty));
        });
        return keyValues;
    }

    private KeyValue addI18nKeyValueProperty(TypescriptProperty typescriptProperty) {
        String key = getModelNameLowerCase() + I18nVM.I18N_PROPERTY_SEPERATOR + typescriptProperty.getFieldName();
        KeyValue i18nKeyValue = new KeyValue(key, StringUtil.firstLetterToUpperCase(typescriptProperty.getFieldName()));
        typescriptProperty.setI18nKeyValue(i18nKeyValue);
        return i18nKeyValue;
    }

}
