import { NidocaInputfield, InputfieldType } from '@domoskanonos/nidoca-core';
import { customElement } from 'lit-element';
import {ProjectRemoteRepository} from '../repo/project-repository';

@customElement('project-combobox-component')
export class ProjectCombobox extends NidocaInputfield {
   constructor() {
      super();
      this.inputfieldType = InputfieldType.COMBOBOX;
      this.optionKeyField = "id";
      this.optionValueField = "technicalDescriptor";
         ProjectRemoteRepository.getUniqueInstance()
         .getAll()
         .then((value) => {
             this.options = value;
             this.size = this.options.length;
             console.debug("find options, size {}", this.size);
         });
   }
}
