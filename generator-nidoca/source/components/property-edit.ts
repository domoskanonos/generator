import {customElement, html, property, TemplateResult} from 'lit-element';
import { I18nService } from "@domoskanonos/frontend-basis";
import {InputfieldType} from "@domoskanonos/nidoca-core";
import {NidocaAbstractComponentEdit} from '@domoskanonos/nidoca-app';
import {Property} from '../model/property-model';
import {PropertyRemoteRepository} from "../repo/property-repository";

@customElement('property-edit-component')
export class PropertyEditComponent extends NidocaAbstractComponentEdit<Property> {

    async getItemById(identifier: any): Promise<Property> {
        return PropertyRemoteRepository.getUniqueInstance().findById(identifier);
    }

    async executeSave(item: Property): Promise<Property> {
        return PropertyRemoteRepository.getUniqueInstance().persist(item);
    }

    async executeUpdate(identifier:any, item: Property): Promise<Property> {
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
    mainProperty : boolean = false;
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
                    label="${I18nService.getUniqueInstance().getValue('property_property_name')}"
            ></nidoca-inputfield>
            <nidoca-inputfield
                    .value="${this.propertyType}"
                    name="propertyType"
                    inputfieldType="${InputfieldType.TEXT}"
                    label="${I18nService.getUniqueInstance().getValue('property_property_propertyType')}"
            ></nidoca-inputfield>
            <nidoca-inputfield
                    .value="${this.idProperty}"
                    name="idProperty"
                    inputfieldType="${InputfieldType.TEXT}"
                    label="${I18nService.getUniqueInstance().getValue('property_property_idProperty')}"
            ></nidoca-inputfield>
            <nidoca-inputfield
                    .value="${this.mainProperty}"
                    name="mainProperty"
                    inputfieldType="${InputfieldType.TEXT}"
                    label="${I18nService.getUniqueInstance().getValue('property_property_mainProperty')}"
            ></nidoca-inputfield>
            <nidoca-inputfield
                    .value="${this.nullable}"
                    name="nullable"
                    inputfieldType="${InputfieldType.TEXT}"
                    label="${I18nService.getUniqueInstance().getValue('property_property_nullable')}"
            ></nidoca-inputfield>
            <nidoca-inputfield
                    .value="${this.useJPAIdClazz}"
                    name="useJPAIdClazz"
                    inputfieldType="${InputfieldType.TEXT}"
                    label="${I18nService.getUniqueInstance().getValue('property_property_useJPAIdClazz')}"
            ></nidoca-inputfield>
            <nidoca-inputfield
                    .value="${this.length}"
                    name="length"
                    inputfieldType="${InputfieldType.TEXT}"
                    label="${I18nService.getUniqueInstance().getValue('property_property_length')}"
            ></nidoca-inputfield>
        `;
    }

    itemToProperties(property: Property): void {
        this.name = property.name;
        this.propertyType = property.propertyType;
        this.idProperty = property.idProperty;
        this.mainProperty = property.mainProperty;
        this.nullable = property.nullable;
        this.useJPAIdClazz = property.useJPAIdClazz;
        this.length = property.length;
    }

    getIdentifier(property: Property): any {
        return property.id;
    }


}
