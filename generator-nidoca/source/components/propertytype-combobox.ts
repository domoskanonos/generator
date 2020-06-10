import { NidocaInputfield, InputfieldType } from '@domoskanonos/nidoca-core';
import { customElement } from 'lit-element';
import {PropertyTypeRemoteRepository} from '../repo/propertytype-repository';

@customElement('propertytype-combobox-component')
export class PropertyTypeCombobox extends NidocaInputfield {
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
            PropertyTypeRemoteRepository.getUniqueInstance()
            .getAll()
            .then((value) => {
                this.options = value;
                this.size = this.options.length;
                console.debug("find options, size {}", this.size);
            });
        }
   }

}
