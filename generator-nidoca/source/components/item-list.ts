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
import {ItemRemoteRepository} from '../repo/item-repository';
import {ItemModel} from '../model/item-model';

@customElement('item-search-list-component')
export class ItemModelSearchNidocaList extends NidocaAbstractComponentSearchList<ItemModel> {

    async executeSearch(search: string): Promise<ItemModel[]> {
        let pageableContainer: PageableContainer<ItemModel> = await ItemRemoteRepository.getUniqueInstance().search(
            0,
            this.resultSize,
            '', ''
                .concat('&name=')
                .concat(search)
                .concat('&namespace=')
                .concat(search)
        );
        return pageableContainer.content;
    }

    renderListItem(itemmodel: ItemModel) : TemplateResult {
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
            <nidoca-typography .typographyType="${TypographyType.BODY1}" text="${itemmodel.id}"></nidoca-typography>
            <nidoca-typography .typographyType="${TypographyType.BODY1}" text="${itemmodel.projectEntity}"></nidoca-typography>
            <nidoca-typography .typographyType="${TypographyType.BODY1}" text="${itemmodel.name}"></nidoca-typography>
            <nidoca-typography .typographyType="${TypographyType.BODY1}" text="${itemmodel.idTypeEnum}"></nidoca-typography>
            <nidoca-typography .typographyType="${TypographyType.BODY1}" text="${itemmodel.namespace}"></nidoca-typography>
            <nidoca-typography .typographyType="${TypographyType.BODY1}" text="${itemmodel.template}"></nidoca-typography>
            <nidoca-typography .typographyType="${TypographyType.BODY1}" text="${itemmodel.properties}"></nidoca-typography>
        </nidoca-flex-container>
    </nidoca-spacer>
    <nidoca-spacer
    .spacerSize="${SpacerSize.MEDIUM}"
    spacerAlignment="${SpacerAlignment.VERTICAL}"
    ></nidoca-spacer>
      `;
    }

    itemClicked(itemmodel: ItemModel, index: number): void {
        console.debug("ItemModel list item clicked, index= {0}", index);
        RouterService.getUniqueInstance().navigate('${model.getModelEditPageUrl()}',{ 'id' : itemmodel.id});
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

   async deleteItems(itemmodels: ItemModel[]): Promise<void> {
        itemmodels.forEach((itemmodel) => {
             console.log('delete itemmodel with id: {}', itemmodel.id);
        ItemRemoteRepository.getUniqueInstance().delete(itemmodel.id);
        });
   }

}
