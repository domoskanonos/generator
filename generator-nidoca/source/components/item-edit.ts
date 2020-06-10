import {customElement, html, property, TemplateResult} from 'lit-element';
import { I18nService } from "@domoskanonos/frontend-basis";
import {NidocaInputfield, InputfieldType} from "@domoskanonos/nidoca-core";
import {NidocaAbstractComponentEdit} from '@domoskanonos/nidoca-app';
import {Property,Item,Project,Process,PropertyType,LanguageType,Template} from '../model/model'
import {ItemRemoteRepository} from "../repo/item-repository";

@customElement('item-edit-component')
export class ItemEditComponent extends NidocaAbstractComponentEdit<Item> {

    async getItemById(identifier: any): Promise<Item> {
        return ItemRemoteRepository.getUniqueInstance().findById(identifier);
    }

    async executeSave(item: Item): Promise<Item> {
        return ItemRemoteRepository.getUniqueInstance().persist(item);
    }

    async executeUpdate(identifier:any, item: Item): Promise<Item> {
        return ItemRemoteRepository.getUniqueInstance().update(identifier, item);
    }

    async executeDelete(identifier: any): Promise<void> {
        return ItemRemoteRepository.getUniqueInstance().delete(identifier);
    }

    @property()
    name : string = '';
    @property()
    deactivated : boolean = false;
    @property()
    idPropertyType : PropertyType | undefined = undefined;
    @property()
    template : Template[] = [];
    @property()
    properties : Property[] = [];

    renderFormFields(): TemplateResult {
        return html`
            <nidoca-inputfield
                    .value="${this.name}"
                    name="name"
                    inputfieldType="${InputfieldType.TEXT}"
                    label="${I18nService.getUniqueInstance().getValue('item_property_name')}"
            ></nidoca-inputfield>
            <nidoca-inputfield
                    .checked="${this.deactivated}"
                    name="deactivated"
                    inputfieldType="${InputfieldType.SWITCH}"
                    label="${I18nService.getUniqueInstance().getValue('item_property_deactivated')}"
            ></nidoca-inputfield>
            <nidoca-inputfield
                    .value="${this.idPropertyType}"
                    name="idPropertyType"
                    inputfieldType="${InputfieldType.TEXT}"
                    label="${I18nService.getUniqueInstance().getValue('item_property_idPropertyType')}"
            ></nidoca-inputfield>
            <nidoca-inputfield
                    .value="${this.template}"
                    name="template"
                    inputfieldType="${InputfieldType.COMBOBOX}"
                    label="${I18nService.getUniqueInstance().getValue('item_property_template')}"
            ></nidoca-inputfield>
            <nidoca-inputfield
                    .value="${this.properties}"
                    name="properties"
                    inputfieldType="${InputfieldType.COMBOBOX}"
                    label="${I18nService.getUniqueInstance().getValue('item_property_properties')}"
            ></nidoca-inputfield>
        `;
    }

    itemToProperties(item: Item): void {
        this.name = item.name;
        this.deactivated = item.deactivated;
        this.idPropertyType = item.idPropertyType;
        this.template = item.template;
        this.properties = item.properties;
    }

    getIdentifier(item: Item): any {
        return item.id;
    }


}
