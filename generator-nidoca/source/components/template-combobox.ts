import { NidocaInputfield, InputfieldType } from '@domoskanonos/nidoca-core';
import { customElement } from 'lit-element';
import {TemplateRemoteRepository} from '../repo/template-repository';

@customElement('template-combobox-component')
export class TemplateCombobox extends NidocaInputfield {
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
            TemplateRemoteRepository.getUniqueInstance()
            .getAll()
            .then((value) => {
                this.options = value;
                this.size = this.options.length;
                console.debug("find options, size {}", this.size);
            });
        }
   }

}
