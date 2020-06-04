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
import {ProcessRemoteRepository} from '../repo/process-repository';
import {ProcessModel} from '../model/process-model';

@customElement('process-search-list-component')
export class ProcessModelSearchNidocaList extends NidocaAbstractComponentSearchList<ProcessModel> {

    async executeSearch(search: string): Promise<ProcessModel[]> {
        let pageableContainer: PageableContainer<ProcessModel> = await ProcessRemoteRepository.getUniqueInstance().search(
            0,
            this.resultSize,
            '', ''
                .concat('&processTempPath=')
                .concat(search)
                .concat('&processParentPath=')
                .concat(search)
                .concat('&technicalDescriptor=')
                .concat(search)
        );
        return pageableContainer.content;
    }

    renderListItem(processmodel: ProcessModel) : TemplateResult {
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
            <nidoca-typography .typographyType="${TypographyType.BODY1}" text="${processmodel.id}"></nidoca-typography>
            <nidoca-typography .typographyType="${TypographyType.BODY1}" text="${processmodel.projectEntities}"></nidoca-typography>
            <nidoca-typography .typographyType="${TypographyType.BODY1}" text="${processmodel.processTempPath}"></nidoca-typography>
            <nidoca-typography .typographyType="${TypographyType.BODY1}" text="${processmodel.processParentPath}"></nidoca-typography>
            <nidoca-typography .typographyType="${TypographyType.BODY1}" text="${processmodel.technicalDescriptor}"></nidoca-typography>
        </nidoca-flex-container>
    </nidoca-spacer>
    <nidoca-spacer
    .spacerSize="${SpacerSize.MEDIUM}"
    spacerAlignment="${SpacerAlignment.VERTICAL}"
    ></nidoca-spacer>
      `;
    }

    itemClicked(processmodel: ProcessModel, index: number): void {
        console.debug("ProcessModel list item clicked, index= {0}", index);
        RouterService.getUniqueInstance().navigate('${model.getModelEditPageUrl()}',{ 'id' : processmodel.id});
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

   async deleteItems(processmodels: ProcessModel[]): Promise<void> {
        processmodels.forEach((processmodel) => {
             console.log('delete processmodel with id: {}', processmodel.id);
        ProcessRemoteRepository.getUniqueInstance().delete(processmodel.id);
        });
   }

}
