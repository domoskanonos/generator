import { NidocaInputfield, InputfieldType } from '@domoskanonos/nidoca-core';
import { customElement } from 'lit-element';
import {PropertyRemoteRepository} from '../repo/property-repository';

@customElement('property-combobox-component')
export class PropertyCombobox extends NidocaInputfield {
   constructor() {
      super();
      this.inputfieldType = InputfieldType.COMBOBOX;
      this.multiple = true;
         PropertyRemoteRepository.getUniqueInstance()
         .getAll()
         .then((value) => {
            this.options = NidocaInputfield.object2KeyValueDataArray(
               value,
               'id',
               'id',
               true
            );
         });
   }
}
