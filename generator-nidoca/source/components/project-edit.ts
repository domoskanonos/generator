import {customElement, html, property, TemplateResult} from 'lit-element';
import { I18nService } from "@domoskanonos/frontend-basis";
import {NidocaInputfield, InputfieldType} from "@domoskanonos/nidoca-core";
import {NidocaAbstractComponentEdit} from '@domoskanonos/nidoca-app';
import {Project} from '../model/project-model';
import {ProjectRemoteRepository} from "../repo/project-repository";

@customElement('project-edit-component')
export class ProjectEditComponent extends NidocaAbstractComponentEdit<Project> {

    async getItemById(identifier: any): Promise<Project> {
        return ProjectRemoteRepository.getUniqueInstance().findById(identifier);
    }

    async executeSave(item: Project): Promise<Project> {
        return ProjectRemoteRepository.getUniqueInstance().persist(item);
    }

    async executeUpdate(identifier:any, item: Project): Promise<Project> {
        return ProjectRemoteRepository.getUniqueInstance().update(identifier, item);
    }

    async executeDelete(identifier: any): Promise<void> {
        return ProjectRemoteRepository.getUniqueInstance().delete(identifier);
    }

    @property()
    items : any[] = [];
    @property()
    template : any[] = [];
    @property()
    technicalDescriptor : string = '';
    @property()
    javaBasePackage : string = '';

    renderFormFields(): TemplateResult {
        return html`
            <nidoca-inputfield
                    .value="${this.items}"
                    name="items"
                    inputfieldType="${InputfieldType.COMBOBOX}"
                    label="${I18nService.getUniqueInstance().getValue('project_property_items')}"
            ></nidoca-inputfield>
            <nidoca-inputfield
                    .value="${this.template}"
                    name="template"
                    inputfieldType="${InputfieldType.COMBOBOX}"
                    label="${I18nService.getUniqueInstance().getValue('project_property_template')}"
            ></nidoca-inputfield>
            <nidoca-inputfield
                    .value="${this.technicalDescriptor}"
                    name="technicalDescriptor"
                    inputfieldType="${InputfieldType.TEXT}"
                    label="${I18nService.getUniqueInstance().getValue('project_property_technicalDescriptor')}"
            ></nidoca-inputfield>
            <nidoca-inputfield
                    .value="${this.javaBasePackage}"
                    name="javaBasePackage"
                    inputfieldType="${InputfieldType.TEXT}"
                    label="${I18nService.getUniqueInstance().getValue('project_property_javaBasePackage')}"
            ></nidoca-inputfield>
        `;
    }

    itemToProperties(project: Project): void {
        this.items = NidocaInputfield.object2KeyValueDataArray(
        project.items,
        'id',
        'id',
        true
        );
        this.template = NidocaInputfield.object2KeyValueDataArray(
        project.template,
        'id',
        'id',
        true
        );
        this.technicalDescriptor = project.technicalDescriptor;
        this.javaBasePackage = project.javaBasePackage;
    }

    getIdentifier(project: Project): any {
        return project.id;
    }


}
