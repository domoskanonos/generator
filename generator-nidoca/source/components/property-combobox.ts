import { NidocaInputfield, InputfieldType } from '@domoskanonos/nidoca-core';
import { customElement } from 'lit-element';
import {PropertyRemoteRepository} from '../repo/property-repository';

@customElement('property-combobox-component')
export class PropertyCombobox extends NidocaInputfield {
   constructor() {
      super();
      this.inputfieldType = InputfieldType.COMBOBOX;
      this.multiple = true;
      this.optionKeyField = "id";
      this.optionValueField = "name";
   }

   protected update(changedProperties: Map<PropertyKey, unknown>): void {
        super.update(changedProperties);
        if (
            changedProperties.get('value') != undefined
        ) {
            PropertyRemoteRepository.getUniqueInstance()
            .getAll()
            .then((value) => {
                this.options = value;
                this.size = this.options.length;
                console.debug("find options, size {}", this.size);
            });
        }
   }

}
