import {customElement, html, property, TemplateResult} from 'lit-element';
import { I18nService } from "@domoskanonos/frontend-basis";
import {InputfieldType} from "@domoskanonos/nidoca-core";
import {NidocaAbstractComponentEdit} from '@domoskanonos/nidoca-app';
import {ProcessModel} from '../model/process-model';
import {ProcessRemoteRepository} from "../repo/process-repository";

@customElement('process-edit-component')
export class ProcessModelEditComponent extends NidocaAbstractComponentEdit<ProcessModel> {

    getDialogDeleteTitle(): string {
        return I18nService.getUniqueInstance().getValue("${model.getI18nEditDialogDeleteTitleKey()}");
    }

    getDialogDeleteDescription(): string {
        return I18nService.getUniqueInstance().getValue("${model.getI18nEditDialogDeleteDescriptionKey()}");
    }

    async getItemById(identifier: any): Promise<ProcessModel> {
        return ProcessRemoteRepository.getUniqueInstance().findById(identifier);
    }

    async executeSave(item: ProcessModel): Promise<ProcessModel> {
        return ProcessRemoteRepository.getUniqueInstance().persist(item);
    }

    async executeUpdate(identifier:any, item: ProcessModel): Promise<ProcessModel> {
        return ProcessRemoteRepository.getUniqueInstance().update(identifier, item);
    }

    async executeDelete(identifier: any): Promise<void> {
        return ProcessRemoteRepository.getUniqueInstance().delete(identifier);
    }

    @property()
    projectEntities : any[] = [];
    @property()
    processTempPath : string = '';
    @property()
    processParentPath : string = '';
    @property()
    technicalDescriptor : string = '';

    renderFormFields(): TemplateResult {
        return html`
            <nidoca-inputfield
                    .value="${this.projectEntities}"
                    name="projectEntities"
                    inputfieldType="${InputfieldType.TEXT}"
                    label="${I18nService.getUniqueInstance().getValue('${property.getI18nKey()}')}"
            ></nidoca-inputfield>
            <nidoca-inputfield
                    .value="${this.processTempPath}"
                    name="processTempPath"
                    inputfieldType="${InputfieldType.TEXT}"
                    label="${I18nService.getUniqueInstance().getValue('${property.getI18nKey()}')}"
            ></nidoca-inputfield>
            <nidoca-inputfield
                    .value="${this.processParentPath}"
                    name="processParentPath"
                    inputfieldType="${InputfieldType.TEXT}"
                    label="${I18nService.getUniqueInstance().getValue('${property.getI18nKey()}')}"
            ></nidoca-inputfield>
            <nidoca-inputfield
                    .value="${this.technicalDescriptor}"
                    name="technicalDescriptor"
                    inputfieldType="${InputfieldType.TEXT}"
                    label="${I18nService.getUniqueInstance().getValue('${property.getI18nKey()}')}"
            ></nidoca-inputfield>
        `;
    }

    itemToProperties(processmodel: ProcessModel): void {
        this.projectEntities = processmodel.projectEntities;
        this.processTempPath = processmodel.processTempPath;
        this.processParentPath = processmodel.processParentPath;
        this.technicalDescriptor = processmodel.technicalDescriptor;
    }

    getIdentifier(processmodel: ProcessModel): any {
        return processmodel.id;
    }


}
