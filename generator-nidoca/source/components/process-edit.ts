import {customElement, html, property, TemplateResult} from 'lit-element';
import { I18nService } from "@domoskanonos/frontend-basis";
import {NidocaInputfield, InputfieldType} from "@domoskanonos/nidoca-core";
import {NidocaAbstractComponentEdit} from '@domoskanonos/nidoca-app';
import {Property,Item,Project,Process,PropertyType,LanguageType,Template} from '../model/model'
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
    projects : Project[] = [];
    @property()
    processTempPath : string = '';
    @property()
    processParentPath : string = '';
    @property()
    technicalDescriptor : string = '';
    @property()
    deactivated : boolean = false;

    renderFormFields(): TemplateResult {
        return html`
            <project-combobox-component
                    .value="${this.projects}"
                    name="projects"
                    .multiple="${true}"
                    inputfieldType="${InputfieldType.COMBOBOX}"
                    label="${I18nService.getUniqueInstance().getValue('process_property_projects')}"
            ></project-combobox-component>
            <nidoca-inputfield
                    .value="${this.processTempPath}"
                    name="processTempPath"
                    .multiple="${false}"
                    inputfieldType="${InputfieldType.TEXT}"
                    label="${I18nService.getUniqueInstance().getValue('process_property_processTempPath')}"
            ></nidoca-inputfield>
            <nidoca-inputfield
                    .value="${this.processParentPath}"
                    name="processParentPath"
                    .multiple="${false}"
                    inputfieldType="${InputfieldType.TEXT}"
                    label="${I18nService.getUniqueInstance().getValue('process_property_processParentPath')}"
            ></nidoca-inputfield>
            <nidoca-inputfield
                    .value="${this.technicalDescriptor}"
                    name="technicalDescriptor"
                    .multiple="${false}"
                    inputfieldType="${InputfieldType.TEXT}"
                    label="${I18nService.getUniqueInstance().getValue('process_property_technicalDescriptor')}"
            ></nidoca-inputfield>
            <nidoca-inputfield
                    .checked="${this.deactivated}"
                    name="deactivated"
                    .multiple="${false}"
                    inputfieldType="${InputfieldType.SWITCH}"
                    label="${I18nService.getUniqueInstance().getValue('process_property_deactivated')}"
            ></nidoca-inputfield>
        `;
    }

    itemToProperties(process: Process): void {
        this.projects = process.projects;
        this.processTempPath = process.processTempPath;
        this.processParentPath = process.processParentPath;
        this.technicalDescriptor = process.technicalDescriptor;
        this.deactivated = process.deactivated;
    }

    getIdentifier(process: Process): any {
        return process.id;
    }


}
