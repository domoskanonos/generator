import { NidocaInputfield, InputfieldType } from '@domoskanonos/nidoca-core';
import { customElement } from 'lit-element';
import {AuthUserRemoteRepository} from '../repo/authuser-repository';

@customElement('authuser-combobox-component')
export class AuthUserCombobox extends NidocaInputfield {
   constructor() {
      super();
      this.inputfieldType = InputfieldType.COMBOBOX;
      this.multiple = true;
      this.optionKeyField = "id";
      this.optionValueField = "id";
         AuthUserRemoteRepository.getUniqueInstance()
         .getAll()
         .then((value) => {
             this.options = value;
         });
   }
}
