import { NidocaInputfield, InputfieldType } from '@domoskanonos/nidoca-core';
import { customElement } from 'lit-element';
import {ProjectRemoteRepository} from '../repo/project-repository';

@customElement('project-combobox-component')
export class ProjectCombobox extends NidocaInputfield {
   constructor() {
      super();
      this.inputfieldType = InputfieldType.COMBOBOX;
         ProjectRemoteRepository.getUniqueInstance()
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
