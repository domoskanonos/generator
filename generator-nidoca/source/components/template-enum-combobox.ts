import { NidocaInputfield, InputfieldType } from '@domoskanonos/nidoca-core';
import { customElement } from 'lit-element';
import {TemplateBasicEnumRepository} from '../repo/template-enum-repository';

@customElement('template-combobox-enum-component')
export class TemplateCombobox extends NidocaInputfield {
   constructor() {
      super();
      this.inputfieldType = InputfieldType.COMBOBOX;
      this.optionKeyField = "key";
      this.optionValueField = "value";
   }

   protected update(changedProperties: Map<PropertyKey, unknown>): void {
        super.update(changedProperties);
        if (
            changedProperties.get('value') != undefined
        ) {
            this.options = TemplateBasicEnumRepository.getUniqueInstance().asI18nEnumKeyValueArray();
            //this.size = this.options.length;
        }
   }

}
