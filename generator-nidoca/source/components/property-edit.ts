import {customElement, html, property, TemplateResult} from 'lit-element';
import { I18nService } from "@domoskanonos/frontend-basis";
import {InputfieldType} from "@domoskanonos/nidoca-core";
import {NidocaAbstractComponentEdit} from '@domoskanonos/nidoca-app';
import {PropertyModel} from '../model/property-model';
import {PropertyRemoteRepository} from "../repo/property-repository";

@customElement('property-edit-component')
export class PropertyModelEditComponent extends NidocaAbstractComponentEdit<PropertyModel> {

    getDialogDeleteTitle(): string {
        return I18nService.getUniqueInstance().getValue("${model.getI18nEditDialogDeleteTitleKey()}");
    }

    getDialogDeleteDescription(): string {
        return I18nService.getUniqueInstance().getValue("${model.getI18nEditDialogDeleteDescriptionKey()}");
    }

    async getItemById(identifier: any): Promise<PropertyModel> {
        return PropertyRemoteRepository.getUniqueInstance().findById(identifier);
    }

    async executeSave(item: PropertyModel): Promise<PropertyModel> {
        return PropertyRemoteRepository.getUniqueInstance().persist(item);
    }

    async executeUpdate(identifier:any, item: PropertyModel): Promise<PropertyModel> {
        return PropertyRemoteRepository.getUniqueInstance().update(identifier, item);
    }

    async executeDelete(identifier: any): Promise<void> {
        return PropertyRemoteRepository.getUniqueInstance().delete(identifier);
    }

    @property()
    name : string = '';
    @property()
    propertyType : string  = '';
    @property()
    idProperty : boolean = false;
    @property()
    searchable : boolean = false;
    @property()
    nullable : boolean = false;
    @property()
    useJPAIdClazz : boolean = false;
    @property()
    length : number = 0;

    renderFormFields(): TemplateResult {
        return html`
            <nidoca-inputfield
                    .value="${this.name}"
                    name="name"
                    inputfieldType="${InputfieldType.TEXT}"
                    label="${I18nService.getUniqueInstance().getValue('${property.getI18nKey()}')}"
            ></nidoca-inputfield>
            <nidoca-inputfield
                    .value="${this.propertyType}"
                    name="propertyType"
                    inputfieldType="${InputfieldType.TEXT}"
                    label="${I18nService.getUniqueInstance().getValue('${property.getI18nKey()}')}"
            ></nidoca-inputfield>
            <nidoca-inputfield
                    .value="${this.idProperty}"
                    name="idProperty"
                    inputfieldType="${InputfieldType.TEXT}"
                    label="${I18nService.getUniqueInstance().getValue('${property.getI18nKey()}')}"
            ></nidoca-inputfield>
            <nidoca-inputfield
                    .value="${this.searchable}"
                    name="searchable"
                    inputfieldType="${InputfieldType.TEXT}"
                    label="${I18nService.getUniqueInstance().getValue('${property.getI18nKey()}')}"
            ></nidoca-inputfield>
            <nidoca-inputfield
                    .value="${this.nullable}"
                    name="nullable"
                    inputfieldType="${InputfieldType.TEXT}"
                    label="${I18nService.getUniqueInstance().getValue('${property.getI18nKey()}')}"
            ></nidoca-inputfield>
            <nidoca-inputfield
                    .value="${this.useJPAIdClazz}"
                    name="useJPAIdClazz"
                    inputfieldType="${InputfieldType.TEXT}"
                    label="${I18nService.getUniqueInstance().getValue('${property.getI18nKey()}')}"
            ></nidoca-inputfield>
            <nidoca-inputfield
                    .value="${this.length}"
                    name="length"
                    inputfieldType="${InputfieldType.TEXT}"
                    label="${I18nService.getUniqueInstance().getValue('${property.getI18nKey()}')}"
            ></nidoca-inputfield>
        `;
    }

    itemToProperties(propertymodel: PropertyModel): void {
        this.name = propertymodel.name;
        this.propertyType = propertymodel.propertyType;
        this.idProperty = propertymodel.idProperty;
        this.searchable = propertymodel.searchable;
        this.nullable = propertymodel.nullable;
        this.useJPAIdClazz = propertymodel.useJPAIdClazz;
        this.length = propertymodel.length;
    }

    getIdentifier(propertymodel: PropertyModel): any {
        return propertymodel.id;
    }


}
