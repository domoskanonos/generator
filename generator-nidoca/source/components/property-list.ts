import {
NidocaAbstractComponentSearchList
} from '@domoskanonos/nidoca-app';
import { PageableContainer, RouterService, I18nService } from '@domoskanonos/frontend-basis';
import { customElement, html, TemplateResult } from 'lit-element';
import {
TypographyType,
FlexJustifyContent,
FlexAlignItems,
SpacerAlignment,
SpacerSize,
FlexContainerProperties
} from '@domoskanonos/nidoca-core';
import {PropertyRemoteRepository} from '../repo/property-repository';
import {Property} from '../model/property-model';

@customElement('property-search-list-component')
export class PropertySearchNidocaList extends NidocaAbstractComponentSearchList<Property> {

    async executeSearch(search: string): Promise<Property[]> {
        let pageableContainer: PageableContainer<Property> = await PropertyRemoteRepository.getUniqueInstance().search(
            0,
            this.resultSize,
            '', ''
        );
        return pageableContainer.content;
    }

    renderListItem(property: Property) : TemplateResult {
        return html`
    <nidoca-spacer
    .spacerSize="${SpacerSize.MEDIUM}"
    spacerAlignment="${SpacerAlignment.VERTICAL}"
    ></nidoca-spacer>
    <nidoca-spacer
    .spacerSize="${SpacerSize.MAX}"
    spacerAlignment="${SpacerAlignment.HORIZONTAL}"
    >
        <nidoca-flex-container
        .flexContainerProperties="${[FlexContainerProperties.CONTAINER_WIDTH_100]}"
        itemFlexBasisValue="100%"
        .justifyContent="${FlexJustifyContent.FLEX_START}"
        .alignItems="${FlexAlignItems.CENTER}"
        >
        </nidoca-flex-container>
    </nidoca-spacer>
    <nidoca-spacer
    .spacerSize="${SpacerSize.MEDIUM}"
    spacerAlignment="${SpacerAlignment.VERTICAL}"
    ></nidoca-spacer>
      `;
    }

    itemClicked(property: Property, index: number): void {
        console.debug("Property list item clicked, index= {0}", index);
        RouterService.getUniqueInstance().navigate('propertyedit',{ 'id' : property.id});
    }

   renderNoRecord(): TemplateResult {
      return html`
         <nidoca-flex-container
            .flexContainerProperties="${[FlexContainerProperties.CONTAINER_WIDTH_100]}"
            itemFlexBasisValue="auto"
            .flexJustifyContent="${FlexJustifyContent.SPACE_AROUND}"
            .alignItems="${FlexAlignItems.CENTER}"
         >
            <nidoca-spacer
               .spacerSize="${SpacerSize.BIG}"
               spacerAlignment="${SpacerAlignment.BOTH}"
            >
               <nidoca-typography
                  .typographyType="${TypographyType.BUTTON}"
                  text="${I18nService.getUniqueInstance().getValue(
                     'no_entry_found'
                  )}"
               ></nidoca-typography>
            </nidoca-spacer>
         </nidoca-flex-container>
      `;
   }

   async deleteItems(propertys: Property[]): Promise<void> {
        propertys.forEach((property) => {
             console.log('delete property with id: {}', property.id);
        PropertyRemoteRepository.getUniqueInstance().delete(property.id);
        });
   }

}
