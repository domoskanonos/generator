import {customElement, html, property, TemplateResult} from 'lit-element';
import { I18nService } from "@domoskanonos/frontend-basis";
import {NidocaInputfield, InputfieldType} from "@domoskanonos/nidoca-core";
import {NidocaAbstractComponentEdit} from '@domoskanonos/nidoca-app';
import {Property,Item,Project,Process,PropertyType,LanguageType,Template} from '../model/model'
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
    items : Item[] = [];
    @property()
    template : Template[] = [];
    @property()
    technicalDescriptor : string = '';
    @property()
    namespace : string = '';
    @property()
    deactivated : boolean = false;

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
                    .value="${this.namespace}"
                    name="namespace"
                    inputfieldType="${InputfieldType.TEXT}"
                    label="${I18nService.getUniqueInstance().getValue('project_property_namespace')}"
            ></nidoca-inputfield>
            <nidoca-inputfield
                    .checked="${this.deactivated}"
                    name="deactivated"
                    inputfieldType="${InputfieldType.SWITCH}"
                    label="${I18nService.getUniqueInstance().getValue('project_property_deactivated')}"
            ></nidoca-inputfield>
        `;
    }

    itemToProperties(project: Project): void {
        this.items = project.items;
        this.template = project.template;
        this.technicalDescriptor = project.technicalDescriptor;
        this.namespace = project.namespace;
        this.deactivated = project.deactivated;
    }

    getIdentifier(project: Project): any {
        return project.id;
    }


}
