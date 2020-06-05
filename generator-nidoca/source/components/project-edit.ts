import {customElement, html, property, TemplateResult} from 'lit-element';
import { I18nService } from "@domoskanonos/frontend-basis";
import {InputfieldType} from "@domoskanonos/nidoca-core";
import {NidocaAbstractComponentEdit} from '@domoskanonos/nidoca-app';
import {Project} from '../model/project-model';
import {ProjectRemoteRepository} from "../repo/project-repository";

@customElement('project-edit-component')
export class ProjectEditComponent extends NidocaAbstractComponentEdit<Project> {

    getDialogDeleteTitle(): string {
        return I18nService.getUniqueInstance().getValue("${model.getI18nEditDialogDeleteTitleKey()}");
    }

    getDialogDeleteDescription(): string {
        return I18nService.getUniqueInstance().getValue("${model.getI18nEditDialogDeleteDescriptionKey()}");
    }

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
    itemEntities : any[] = [];
    @property()
    template : any = {};
    @property()
    technicalDescriptor : string = '';
    @property()
    javaBasePackage : string = '';

    renderFormFields(): TemplateResult {
        return html`
            <nidoca-inputfield
                    .value="${this.itemEntities}"
                    name="itemEntities"
                    inputfieldType="${InputfieldType.TEXT}"
                    label="${I18nService.getUniqueInstance().getValue('${property.getI18nKey()}')}"
            ></nidoca-inputfield>
            <nidoca-inputfield
                    .value="${this.template}"
                    name="template"
                    inputfieldType="${InputfieldType.TEXT}"
                    label="${I18nService.getUniqueInstance().getValue('${property.getI18nKey()}')}"
            ></nidoca-inputfield>
            <nidoca-inputfield
                    .value="${this.technicalDescriptor}"
                    name="technicalDescriptor"
                    inputfieldType="${InputfieldType.TEXT}"
                    label="${I18nService.getUniqueInstance().getValue('${property.getI18nKey()}')}"
            ></nidoca-inputfield>
            <nidoca-inputfield
                    .value="${this.javaBasePackage}"
                    name="javaBasePackage"
                    inputfieldType="${InputfieldType.TEXT}"
                    label="${I18nService.getUniqueInstance().getValue('${property.getI18nKey()}')}"
            ></nidoca-inputfield>
        `;
    }

    itemToProperties(project: Project): void {
        this.itemEntities = project.itemEntities;
        this.template = project.template;
        this.technicalDescriptor = project.technicalDescriptor;
        this.javaBasePackage = project.javaBasePackage;
    }

    getIdentifier(project: Project): any {
        return project.id;
    }


}
