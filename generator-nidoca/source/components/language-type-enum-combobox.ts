import { NidocaInputfield, InputfieldType } from '@domoskanonos/nidoca-core';
import { customElement } from 'lit-element';
import {LanguageTypeBasicEnumRepository} from '../repo/language-type-enum-repository';

@customElement('components/language-type-enum-combobox-combobox-enum-component')
export class LanguageTypeCombobox extends NidocaInputfield {
   constructor() {
      super();
      this.inputfieldType = InputfieldType.COMBOBOX;
      this.multiple = true;
      this.optionKeyField = "key";
      this.optionValueField = "value";
   }

   protected update(changedProperties: Map<PropertyKey, unknown>): void {
        super.update(changedProperties);
        if (
            changedProperties.get('value') != undefined
        ) {
            this.options = LanguageTypeBasicEnumRepository.getUniqueInstance().asI18nEnumKeyValueArray();
            this.size = this.options.length;
        }
   }

}
