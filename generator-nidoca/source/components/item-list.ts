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
import {Item} from '../model/item-model';

@customElement('item-search-list-component')
export class ItemSearchNidocaList extends NidocaAbstractComponentSearchList<Item> {

    async executeSearch(search: string): Promise<Item[]> {
        let pageableContainer: PageableContainer<Item> = await ItemRemoteRepository.getUniqueInstance().search(
            0,
            this.resultSize,
            '', ''
        );
        return pageableContainer.content;
    }

    renderListItem(item: Item) : TemplateResult {
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
                    <nidoca-typography .typographyType="${TypographyType.BODY1}" text="${item.id}"></nidoca-typography>
                    <nidoca-typography .typographyType="${TypographyType.BODY1}" text="${item.project}"></nidoca-typography>
                    <nidoca-typography .typographyType="${TypographyType.BODY1}" text="${item.name}"></nidoca-typography>
                    <nidoca-typography .typographyType="${TypographyType.BODY1}" text="${item.idTypeEnum}"></nidoca-typography>
                    <nidoca-typography .typographyType="${TypographyType.BODY1}" text="${item.template}"></nidoca-typography>
                    <nidoca-typography .typographyType="${TypographyType.BODY1}" text="${item.properties}"></nidoca-typography>
        </nidoca-flex-container>
    </nidoca-spacer>
    <nidoca-spacer
    .spacerSize="${SpacerSize.MEDIUM}"
    spacerAlignment="${SpacerAlignment.VERTICAL}"
    ></nidoca-spacer>
      `;
    }

    itemClicked(item: Item, index: number): void {
        console.debug("Item list item clicked, index= {0}", index);
        RouterService.getUniqueInstance().navigate('itemedit',{ 'id' : item.id});
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

   async deleteItems(items: Item[]): Promise<void> {
        items.forEach((item) => {
             console.log('delete item with id: {}', item.id);
        ItemRemoteRepository.getUniqueInstance().delete(item.id);
        });
   }

}
