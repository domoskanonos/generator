import {customElement, html, property, TemplateResult} from 'lit-element';
import { I18nService } from "@domoskanonos/frontend-basis";
import {NidocaInputfield, InputfieldType} from "@domoskanonos/nidoca-core";
import {NidocaAbstractComponentEdit} from '@domoskanonos/nidoca-app';
import {Process} from '../model/process-model';
import {ProcessRemoteRepository} from "../repo/process-repository";

@customElement('process-edit-component')
export class ProcessEditComponent extends NidocaAbstractComponentEdit<Process> {

    async getItemById(identifier: any): Promise<Process> {
        return ProcessRemoteRepository.getUniqueInstance().findById(identifier);
    }

    async executeSave(item: Process): Promise<Process> {
        return ProcessRemoteRepository.getUniqueInstance().persist(item);
    }

    async executeUpdate(identifier:any, item: Process): Promise<Process> {
        return ProcessRemoteRepository.getUniqueInstance().update(identifier, item);
    }

    async executeDelete(identifier: any): Promise<void> {
        return ProcessRemoteRepository.getUniqueInstance().delete(identifier);
    }

    @property()
    projects : any[] = [];
    @property()
    processTempPath : string = '';
    @property()
    processParentPath : string = '';
    @property()
    technicalDescriptor : string = '';

    renderFormFields(): TemplateResult {
        return html`
            <nidoca-inputfield
                    .value="${this.projects}"
                    name="projects"
                    inputfieldType="${InputfieldType.COMBOBOX}"
                    label="${I18nService.getUniqueInstance().getValue('process_property_projects')}"
            ></nidoca-inputfield>
            <nidoca-inputfield
                    .value="${this.processTempPath}"
                    name="processTempPath"
                    inputfieldType="${InputfieldType.TEXT}"
                    label="${I18nService.getUniqueInstance().getValue('process_property_processTempPath')}"
            ></nidoca-inputfield>
            <nidoca-inputfield
                    .value="${this.processParentPath}"
                    name="processParentPath"
                    inputfieldType="${InputfieldType.TEXT}"
                    label="${I18nService.getUniqueInstance().getValue('process_property_processParentPath')}"
            ></nidoca-inputfield>
            <nidoca-inputfield
                    .value="${this.technicalDescriptor}"
                    name="technicalDescriptor"
                    inputfieldType="${InputfieldType.TEXT}"
                    label="${I18nService.getUniqueInstance().getValue('process_property_technicalDescriptor')}"
            ></nidoca-inputfield>
        `;
    }

    itemToProperties(process: Process): void {
        this.projects = NidocaInputfield.object2KeyValueDataArray(
        process.projects,
        'id',
        'id',
        true
        );
        this.processTempPath = process.processTempPath;
        this.processParentPath = process.processParentPath;
        this.technicalDescriptor = process.technicalDescriptor;
    }

    getIdentifier(process: Process): any {
        return process.id;
    }


}
