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
         PropertyRemoteRepository.getUniqueInstance()
         .getAll()
         .then((value) => {
             this.options = value;
            this.size=this.options.length;
         });
   }
}
