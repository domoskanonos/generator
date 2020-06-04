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
import {PropertyModel} from '../model/property-model';

@customElement('property-search-list-component')
export class PropertyModelSearchNidocaList extends NidocaAbstractComponentSearchList<PropertyModel> {

    async executeSearch(search: string): Promise<PropertyModel[]> {
        let pageableContainer: PageableContainer<PropertyModel> = await PropertyRemoteRepository.getUniqueInstance().search(
            0,
            this.resultSize,
            '', ''
                .concat('&name=')
                .concat(search)
        );
        return pageableContainer.content;
    }

    renderListItem(propertymodel: PropertyModel) : TemplateResult {
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
            <nidoca-typography .typographyType="${TypographyType.BODY1}" text="${propertymodel.id}"></nidoca-typography>
            <nidoca-typography .typographyType="${TypographyType.BODY1}" text="${propertymodel.name}"></nidoca-typography>
            <nidoca-typography .typographyType="${TypographyType.BODY1}" text="${propertymodel.propertyType}"></nidoca-typography>
            <nidoca-typography .typographyType="${TypographyType.BODY1}" text="${propertymodel.idProperty}"></nidoca-typography>
            <nidoca-typography .typographyType="${TypographyType.BODY1}" text="${propertymodel.searchable}"></nidoca-typography>
            <nidoca-typography .typographyType="${TypographyType.BODY1}" text="${propertymodel.nullable}"></nidoca-typography>
            <nidoca-typography .typographyType="${TypographyType.BODY1}" text="${propertymodel.useJPAIdClazz}"></nidoca-typography>
            <nidoca-typography .typographyType="${TypographyType.BODY1}" text="${propertymodel.length}"></nidoca-typography>
        </nidoca-flex-container>
    </nidoca-spacer>
    <nidoca-spacer
    .spacerSize="${SpacerSize.MEDIUM}"
    spacerAlignment="${SpacerAlignment.VERTICAL}"
    ></nidoca-spacer>
      `;
    }

    itemClicked(propertymodel: PropertyModel, index: number): void {
        console.debug("PropertyModel list item clicked, index= {0}", index);
        RouterService.getUniqueInstance().navigate('${model.getModelEditPageUrl()}',{ 'id' : propertymodel.id});
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

   async deleteItems(propertymodels: PropertyModel[]): Promise<void> {
        propertymodels.forEach((propertymodel) => {
             console.log('delete propertymodel with id: {}', propertymodel.id);
        PropertyRemoteRepository.getUniqueInstance().delete(propertymodel.id);
        });
   }

}
