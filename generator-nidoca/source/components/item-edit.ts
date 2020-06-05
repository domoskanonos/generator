import {customElement, html, property, TemplateResult} from 'lit-element';
import { I18nService } from "@domoskanonos/frontend-basis";
import {InputfieldType} from "@domoskanonos/nidoca-core";
import {NidocaAbstractComponentEdit} from '@domoskanonos/nidoca-app';
import {Item} from '../model/item-model';
import {ItemRemoteRepository} from "../repo/item-repository";

@customElement('item-edit-component')
export class ItemEditComponent extends NidocaAbstractComponentEdit<Item> {

    getDialogDeleteTitle(): string {
        return I18nService.getUniqueInstance().getValue("${model.getI18nEditDialogDeleteTitleKey()}");
    }

    getDialogDeleteDescription(): string {
        return I18nService.getUniqueInstance().getValue("${model.getI18nEditDialogDeleteDescriptionKey()}");
    }

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
    projectEntity : any = {};
    @property()
    name : string = '';
    @property()
    idTypeEnum : string  = '';
    @property()
    template : any = {};
    @property()
    properties : any = {};

    renderFormFields(): TemplateResult {
        return html`
            <nidoca-inputfield
                    .value="${this.projectEntity}"
                    name="projectEntity"
                    inputfieldType="${InputfieldType.TEXT}"
                    label="${I18nService.getUniqueInstance().getValue('${property.getI18nKey()}')}"
            ></nidoca-inputfield>
            <nidoca-inputfield
                    .value="${this.name}"
                    name="name"
                    inputfieldType="${InputfieldType.TEXT}"
                    label="${I18nService.getUniqueInstance().getValue('${property.getI18nKey()}')}"
            ></nidoca-inputfield>
            <nidoca-inputfield
                    .value="${this.idTypeEnum}"
                    name="idTypeEnum"
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
                    .value="${this.properties}"
                    name="properties"
                    inputfieldType="${InputfieldType.TEXT}"
                    label="${I18nService.getUniqueInstance().getValue('${property.getI18nKey()}')}"
            ></nidoca-inputfield>
        `;
    }

    itemToProperties(item: Item): void {
        this.projectEntity = item.projectEntity;
        this.name = item.name;
        this.idTypeEnum = item.idTypeEnum;
        this.template = item.template;
        this.properties = item.properties;
    }

    getIdentifier(item: Item): any {
        return item.id;
    }


}
