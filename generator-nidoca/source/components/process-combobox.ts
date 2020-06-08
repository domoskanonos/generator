import { NidocaInputfield, InputfieldType } from '@domoskanonos/nidoca-core';
import { customElement } from 'lit-element';
import {ProcessRemoteRepository} from '../repo/process-repository';

@customElement('process-combobox-component')
export class ProcessCombobox extends NidocaInputfield {
   constructor() {
      super();
      this.inputfieldType = InputfieldType.COMBOBOX;
      this.optionKeyField = "id";
      this.optionValueField = "id";
         ProcessRemoteRepository.getUniqueInstance()
         .getAll()
         .then((value) => {
             this.options = value;
         });
   }
}
