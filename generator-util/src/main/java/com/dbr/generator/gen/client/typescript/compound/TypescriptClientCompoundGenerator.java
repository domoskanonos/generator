package com.dbr.generator.gen.client.typescript.compound;

import com.dbr.generator.gen.client.i18n.I18nJSONGenerator;
import com.dbr.generator.gen.client.i18n.model.I18nVM;
import com.dbr.generator.gen.client.i18n.model.KeyValue;
import com.dbr.generator.gen.client.typescript.*;
import com.dbr.generator.gen.client.typescript.compound.model.TypescriptClientCVM;
import com.dbr.generator.gen.client.typescript.model.*;
import com.dbr.generator.sample.dto.UserDTO;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TypescriptClientCompoundGenerator extends BasicTypescriptGenerator {

    private TypescriptClientCVM cvm;

    public TypescriptClientCompoundGenerator(TypescriptClientCVM cvm) {
        this.cvm = cvm;
    }

    public void writeDown() throws Exception {

        Class[] clazzes = this.cvm.getClazzes();

        List<String> pagesImportFile = new ArrayList<>();
        List<String> componentImportFile = new ArrayList<>();
        List<KeyValue> i18nKeyValues = new ArrayList<>();

        if (this.cvm.isGenerateSecurityModule()) {
            pagesImportFile.add(new StringBuilder().append("import './").append("page-dashboard';").toString());
            pagesImportFile.add(new StringBuilder().append("import './").append("page-settings';").toString());
            pagesImportFile.add(new StringBuilder().append("import './").append("page-register';").toString());
            pagesImportFile.add(new StringBuilder().append("import './").append("page-register-ok';").toString());
            pagesImportFile.add(new StringBuilder().append("import './").append("page-login';").toString());
            pagesImportFile.add(new StringBuilder().append("import './").append("page-logout';").toString());
            pagesImportFile.add(new StringBuilder().append("import './").append("page-change-password';").toString());
            pagesImportFile.add(new StringBuilder().append("import './").append("page-reset-password';").toString());
        }

        if(this.cvm.isGenerateTermsOfUse()){
            pagesImportFile.add(new StringBuilder().append("import './").append("page-terms-of-use';").toString());
            i18nKeyValues.add(new KeyValue("terms_of_use", "Nutzungsbedingungen"));
            //TODO: Copy simple files without replacement
        }

        for (Class clazz : clazzes) {

            String modelName = this.cvm.getModelName(clazz);

            if (this.cvm.isGenerateModel()) {
                Clazz2TypescriptModelVM clazz2TypescriptModelVM = new Clazz2TypescriptModelVM(clazz, modelName);
                Clazz2TypescriptModelGenerator clazz2TypescriptModelGenerator = new Clazz2TypescriptModelGenerator(clazz2TypescriptModelVM);
                clazz2TypescriptModelGenerator.writeDown();
            }

            if (this.cvm.isGenerateBasicRemoteRepository()) {
                RemoteRepositoryVM remoteRepositoryVM = new RemoteRepositoryVM(clazz, modelName);
                RemoteRepositoryGenerator remoteRepositoryGenerator = new RemoteRepositoryGenerator(remoteRepositoryVM);
                remoteRepositoryGenerator.writeDown();
            }

            if (this.cvm.isGenerateBasicRemoteService()) {
                RemoteServiceVM remoteServiceVM = new RemoteServiceVM(clazz, modelName);
                RemoteServiceGenerator remoteServiceGenerator = new RemoteServiceGenerator(remoteServiceVM);
                remoteServiceGenerator.writeDown();
            }

            ClazzTypescriptWrapper clazzTypescriptWrapper = new ClazzTypescriptWrapper(clazz, this.cvm.getModelNameReplaceKey(), this.cvm.getModelNameReplaceValue());

            if (clazzTypescriptWrapper.getIdFieldName() != null) {

                //add i18n keyValues for clazzes
                i18nKeyValues.addAll(clazzTypescriptWrapper.getI18nKeyValues());

                //component edit
                if (this.cvm.isGenerateEditComponent()) {
                    ClazzTypescriptWrapperVM clazzTypescriptWrapperVM = new ClazzTypescriptWrapperVM("client/typescript/component/edit.vm", clazzTypescriptWrapper.getFolderNameComponents(), "edit.ts", clazzTypescriptWrapper);
                    ClazzTypescriptWrapperGenerator clazzTypescriptWrapperGenerator = new ClazzTypescriptWrapperGenerator(clazzTypescriptWrapperVM);
                    clazzTypescriptWrapperGenerator.writeDown();
                    componentImportFile.add(new StringBuilder().append("import './").append(clazzTypescriptWrapper.getModelNameLowerCase()).append("/edit';").toString());
                }

                //component searchList
                if (this.cvm.isGenerateSearchNidocaList()) {
                    ClazzTypescriptWrapperVM clazzTypescriptWrapperVM = new ClazzTypescriptWrapperVM("client/typescript/component/search-list.vm", clazzTypescriptWrapper.getFolderNameComponents(), "search-list.ts", clazzTypescriptWrapper);
                    ClazzTypescriptWrapperGenerator clazzTypescriptWrapperGenerator = new ClazzTypescriptWrapperGenerator(clazzTypescriptWrapperVM);
                    clazzTypescriptWrapperGenerator.writeDown();
                    componentImportFile.add(new StringBuilder().append("import './").append(clazzTypescriptWrapper.getModelNameLowerCase()).append("/search-list';").toString());
                }

                //component inputfieldComboboxComponent
                if (this.cvm.isGenerateComboboxComponent()) {
                    ClazzTypescriptWrapperVM clazzTypescriptWrapperVM = new ClazzTypescriptWrapperVM("client/typescript/component/combobox.vm", clazzTypescriptWrapper.getFolderNameComponents(), "combobox.ts", clazzTypescriptWrapper);
                    ClazzTypescriptWrapperGenerator clazzTypescriptWrapperGenerator = new ClazzTypescriptWrapperGenerator(clazzTypescriptWrapperVM);
                    clazzTypescriptWrapperGenerator.writeDown();
                    componentImportFile.add(new StringBuilder().append("import './").append(clazzTypescriptWrapper.getModelNameLowerCase()).append("/combobox';").toString());
                }

                //page edit
                if (this.cvm.isGenerateEditPage()) {
                    ClazzTypescriptWrapperVM clazzTypescriptWrapperVM = new ClazzTypescriptWrapperVM("client/typescript/page/edit.vm", clazzTypescriptWrapper.getFolderNamePages(), "edit.ts", clazzTypescriptWrapper);
                    ClazzTypescriptWrapperGenerator clazzTypescriptWrapperGenerator = new ClazzTypescriptWrapperGenerator(clazzTypescriptWrapperVM);
                    clazzTypescriptWrapperGenerator.writeDown();
                    pagesImportFile.add(new StringBuilder().append("import './").append(clazzTypescriptWrapper.getModelNameLowerCase()).append("/edit';").toString());
                }

                //page searchList
                if (this.cvm.isGenerateSearchListPage()) {
                    ClazzTypescriptWrapperVM clazzTypescriptWrapperVM = new ClazzTypescriptWrapperVM("client/typescript/page/search-list.vm", clazzTypescriptWrapper.getFolderNamePages(), "search-list.ts", clazzTypescriptWrapper);
                    ClazzTypescriptWrapperGenerator clazzTypescriptWrapperGenerator = new ClazzTypescriptWrapperGenerator(clazzTypescriptWrapperVM);
                    clazzTypescriptWrapperGenerator.writeDown();
                    pagesImportFile.add(new StringBuilder().append("import './").append(clazzTypescriptWrapper.getModelNameLowerCase()).append("/search-list';").toString());
                }

            }

        }

        ClazzTypescriptWrapper[] clazzTypescriptWrappers = ClazzTypescriptWrapper.toWrapper(clazzes, this.cvm.getModelNameReplaceKey(), this.cvm.getModelNameReplaceValue());

        if (this.cvm.isGeneratePageService()) {
            ClazzTypescriptWrappersVM clazzTypescriptWrappersVM = new ClazzTypescriptWrappersVM(this.cvm, "client/typescript/app/page-service.vm", "service/", "page-service.ts", clazzTypescriptWrappers);
            ClazzTypescriptWrappersGenerator clazzTypescriptWrappersGenerator = new ClazzTypescriptWrappersGenerator(clazzTypescriptWrappersVM);
            clazzTypescriptWrappersGenerator.writeDown();
        }

        if (this.cvm.isGenerateMenuComponent()) {
            pagesImportFile.add("import './page-default';");
            ClazzTypescriptWrappersVM clazzTypescriptWrappersVM = new ClazzTypescriptWrappersVM(this.cvm, "client/typescript/app/page-default.vm", "pages/", "page-default.ts", clazzTypescriptWrappers);
            ClazzTypescriptWrappersGenerator clazzTypescriptWrappersGenerator = new ClazzTypescriptWrappersGenerator(clazzTypescriptWrappersVM);
            clazzTypescriptWrappersGenerator.writeDown();
        }

        if (this.cvm.isGenerateI18n()) {
            writeI18n("message-de.json", i18nKeyValues);
            writeI18n("message-en.json", i18nKeyValues);
        }

        //import.ts file pages
        StringBuilder importFilePagesContent = new StringBuilder();
        for (String imp : pagesImportFile) {
            importFilePagesContent.append(imp).append("\n");
        }
        BasicTypescriptGenerator.writeDownExternal(new File(BasicTypescriptGenerator.TYPESCRIPT_SOURCE_PATH_EXTENDED + "pages/", "import.ts"), importFilePagesContent.toString());

        //import.ts file component
        StringBuilder importFileComponentContent = new StringBuilder();
        for (String imp : componentImportFile) {
            importFileComponentContent.append(imp).append("\n");
        }
        BasicTypescriptGenerator.writeDownExternal(new File(BasicTypescriptGenerator.TYPESCRIPT_SOURCE_PATH_EXTENDED + "components/", "import.ts"), importFileComponentContent.toString());


    }

    private void writeI18n(String filename, List<KeyValue> keyValueList) throws Exception {
        I18nJSONGenerator i18NJSONGenerator = new I18nJSONGenerator(new I18nVM(filename, keyValueList));
        i18NJSONGenerator.writeDown();
    }


    public static void main(String[] args) throws Exception {
        TypescriptClientCVM typescriptClientCVM = new TypescriptClientCVM(UserDTO.class);
        TypescriptClientCompoundGenerator typescriptClientCompoundGenerator = new TypescriptClientCompoundGenerator(typescriptClientCVM);
        typescriptClientCompoundGenerator.writeDown();
    }

}
