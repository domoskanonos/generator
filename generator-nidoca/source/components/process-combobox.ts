import { NidocaInputfield, InputfieldType } from '@domoskanonos/nidoca-core';
import { customElement } from 'lit-element';
import {ProcessRemoteRepository} from '../repo/process-repository';

@customElement('process-combobox-component')
export class ProcessCombobox extends NidocaInputfield {
   constructor() {
      super();
      this.inputfieldType = InputfieldType.COMBOBOX;
         ProcessRemoteRepository.getUniqueInstance()
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
