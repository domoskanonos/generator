import { NidocaInputfield, InputfieldType } from '@domoskanonos/nidoca-core';
import { customElement } from 'lit-element';
import {PropertyTypeBasicEnumRepository} from '../repo/property-type-enum-repository';

@customElement('property-type-combobox-enum-component')
export class PropertyTypeCombobox extends NidocaInputfield {
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
            this.options = PropertyTypeBasicEnumRepository.getUniqueInstance().asI18nEnumKeyValueArray();
            //this.size = this.options.length;
        }
   }

}
