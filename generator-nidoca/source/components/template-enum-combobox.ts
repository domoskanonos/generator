import {NidocaInputfield, InputfieldType} from '@domoskanonos/nidoca-core';
import {customElement} from 'lit-element';
import {TemplateBasicEnumRepository} from '../repo/template-enum-repository';
import {Template} from "../model/model";

@customElement('components/template-enum-combobox-combobox-enum-component')
export class TemplateCombobox extends NidocaInputfield {
    constructor() {
        super();
        this.inputfieldType = InputfieldType.COMBOBOX;
        this.multiple = true;
        this.optionKeyField = "id";
        this.optionValueField = "id";
    }

    protected update(changedProperties: Map<PropertyKey, unknown>): void {
        super.update(changedProperties);
        if (
            changedProperties.get('value') != undefined
        ) {
            this.options = TemplateBasicEnumRepository.getUniqueInstance().asEnumKeyValueArrayI18n(Template);
            this.size = this.options.length;

        }
    }

}
