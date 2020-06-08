import { NidocaInputfield, InputfieldType } from '@domoskanonos/nidoca-core';
import { customElement } from 'lit-element';
import {ItemRemoteRepository} from '../repo/item-repository';

@customElement('item-combobox-component')
export class ItemCombobox extends NidocaInputfield {
   constructor() {
      super();
      this.inputfieldType = InputfieldType.COMBOBOX;
      this.optionKeyField = "id";
      this.optionValueField = "id";
         ItemRemoteRepository.getUniqueInstance()
         .getAll()
         .then((value) => {
             this.options = value;
         });
   }
}
