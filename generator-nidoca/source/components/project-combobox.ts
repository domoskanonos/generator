import {NidocaInputfield, InputfieldType} from '@domoskanonos/nidoca-core';
import {customElement, PropertyValues} from 'lit-element';
import {ProjectRemoteRepository} from '../repo/project-repository';

@customElement('project-combobox-component')
export class ProjectCombobox extends NidocaInputfield {

    protected update(changedProperties: Map<PropertyKey, unknown>): void {
        super.update(changedProperties);
        if (
            changedProperties.get('value') != undefined
        ) {
            this.inputfieldType = InputfieldType.COMBOBOX;
            this.multiple = true;
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

}
